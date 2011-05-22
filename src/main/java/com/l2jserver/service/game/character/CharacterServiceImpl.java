/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.character;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.db.dao.ItemDAO;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.SystemMessage;
import com.l2jserver.game.net.packet.server.ActorChatMessagePacket;
import com.l2jserver.game.net.packet.server.ActorMovementPacket;
import com.l2jserver.game.net.packet.server.CharacterInformationExtraPacket;
import com.l2jserver.game.net.packet.server.CharacterInformationPacket;
import com.l2jserver.game.net.packet.server.CharacterInventoryPacket;
import com.l2jserver.game.net.packet.server.CharacterMovementTypePacket;
import com.l2jserver.game.net.packet.server.CharacterTargetSelectedPacket;
import com.l2jserver.game.net.packet.server.GameGuardQueryPacket;
import com.l2jserver.game.net.packet.server.NPCInformationPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterMoveType;
import com.l2jserver.model.world.L2Character.CharacterState;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.model.world.character.event.CharacterEnterWorldEvent;
import com.l2jserver.model.world.character.event.CharacterEvent;
import com.l2jserver.model.world.character.event.CharacterLeaveWorldEvent;
import com.l2jserver.model.world.character.event.CharacterListener;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.model.world.character.event.CharacterRunningEvent;
import com.l2jserver.model.world.character.event.CharacterTargetDeselectedEvent;
import com.l2jserver.model.world.character.event.CharacterTargetSelectedEvent;
import com.l2jserver.model.world.character.event.CharacterWalkingEvent;
import com.l2jserver.model.world.npc.event.NPCSpawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportedEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.chat.ChatMessageDestination;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.chat.channel.ChatChannel;
import com.l2jserver.service.game.chat.channel.ChatChannelListener;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.FilteredWorldListener;
import com.l2jserver.service.game.world.event.WorldEvent;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.game.world.event.WorldListener;
import com.l2jserver.service.game.world.filter.impl.KnownListFilter;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.dimensional.Point;

/**
 * Default implementation for {@link CharacterService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class, ChatService.class, NetworkService.class,
		SpawnService.class })
public class CharacterServiceImpl extends AbstractService implements
		CharacterService {
	/**
	 * The {@link WorldService}
	 */
	private final WorldService worldService;
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;
	/**
	 * The {@link ChatService}
	 */
	private final ChatService chatService;
	/**
	 * The {@link NetworkService}
	 */
	private final NetworkService networkService;
	/**
	 * The {@link SpawnService}
	 */
	private final SpawnService spawnService;
	/**
	 * The {@link ItemDAO}
	 */
	private final ItemDAO itemDao;

	// /**
	// * The {@link AIService}
	// */
	// private final AIService aiService;

	@Inject
	public CharacterServiceImpl(WorldService worldService,
			WorldEventDispatcher eventDispatcher, ChatService chatService,
			NetworkService networkService, SpawnService spawnService,
			ItemDAO itemDao) {
		this.worldService = worldService;
		this.eventDispatcher = eventDispatcher;
		this.chatService = chatService;
		this.networkService = networkService;
		this.spawnService = spawnService;
		this.itemDao = itemDao;
	}

	@Override
	public void enterWorld(final L2Character character)
			throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException {
		Preconditions.checkNotNull(character, "character");
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		if (conn == null)
			return;

		itemDao.loadInventory(character);

		// chat listener
		final ChatChannelListener globalChatListener = new ChatChannelListener() {
			@Override
			public void onMessage(ChatChannel channel, CharacterID source,
					String message) {
				conn.write(new ActorChatMessagePacket(source.getObject(),
						ChatMessageDestination.ALL, message));
			}
		};
		final ChatChannelListener tradeChatListener = new ChatChannelListener() {
			@Override
			public void onMessage(ChatChannel channel, CharacterID source,
					String message) {
				conn.write(new ActorChatMessagePacket(source.getObject(),
						ChatMessageDestination.TRADE, message));
			}
		};

		// event broadcast listener
		// this listener will be filtered so that only interesting events are
		// dispatched, once a event arrives will be possible to check check if
		// the given event will be broadcasted or not
		// TODO this should not be here, it should be i world service or a newly
		// created broadcast service.
		final WorldListener broadcastListener = new FilteredWorldListener<Positionable>(
				new KnownListFilter(character)) {
			@Override
			protected boolean dispatch(WorldEvent e, Positionable object) {
				if (e instanceof NPCSpawnEvent) {
					conn.write(new NPCInformationPacket((NPC) object));
				} else if (e instanceof CharacterMoveEvent) {
					final CharacterMoveEvent evt = (CharacterMoveEvent) e;
					if (object.equals(character))
						return true;
					conn.write(new ActorMovementPacket((L2Character) object,
							evt.getPoint().getCoordinate()));
				} else if (e instanceof PlayerTeleportedEvent
						|| e instanceof CharacterEnterWorldEvent) {
					// TODO this should not be here!
					for (final WorldObject o : worldService
							.iterable(new KnownListFilter(character))) {
						if (o instanceof NPC) {
							conn.write(new NPCInformationPacket((NPC) o));
						}
					}
				} else if (e instanceof CharacterWalkingEvent
						|| e instanceof CharacterRunningEvent) {
					conn.write(new CharacterMovementTypePacket(
							((CharacterWalkingEvent) e).getCharacter()));
				}
				// keep listener alive
				return true;
			}
		};
		eventDispatcher.addListener(broadcastListener);

		// leave world event
		eventDispatcher.addListener(id, new CharacterListener() {
			@Override
			protected boolean dispatch(CharacterEvent e) {
				if (!(e instanceof CharacterLeaveWorldEvent))
					return true;

				// remove chat listeners
				chatService.getGlobalChannel().removeChatChannelListener(
						globalChatListener);
				chatService.getTradeChannel().removeChatChannelListener(
						tradeChatListener);

				// remove broadcast listener
				eventDispatcher.removeListener(broadcastListener);

				// we can kill this listener now
				return false;
			}
		});

		// register global chat listener
		chatService.getGlobalChannel().addChatChannelListener(
				globalChatListener);
		chatService.getTradeChannel().addChatChannelListener(tradeChatListener);

		// send this user information
		conn.write(new CharacterInformationPacket(character));
		conn.write(new CharacterInformationExtraPacket(character));
		// TODO game guard enforcing
		conn.write(new GameGuardQueryPacket());
		conn.write(new CharacterInventoryPacket(character.getInventory()));

		conn.sendSystemMessage(SystemMessage.WELCOME_TO_LINEAGE);
		conn.sendMessage("This an an development version for l2jserver 2.0");
		conn.sendMessage("Please note that many of the features are not yet implemented.");

		// characters start in run mode
		try {
			run(character);
		} catch (CharacterAlreadyRunningServiceException e1) {
			// we can ignore this one
		}

		// broadcast knownlist -- trashy implementation
		// TODO should be moved to world service or a newly created broadcast
		// service, whichever fits the purpose
		for (final WorldObject o : worldService.iterable(new KnownListFilter(
				character))) {
			if (o instanceof NPC) {
				conn.write(new NPCInformationPacket((NPC) o));
			}
		}

		// dispatch enter world event
		eventDispatcher.dispatch(new CharacterEnterWorldEvent(character));

		// spawn the player -- this will also dispatch a spawn event
		// here the object is registered in the world
		spawnService.spawn(character, null);
	}

	@Override
	public void leaveWorld(L2Character character)
			throws NotSpawnedServiceException {
		Preconditions.checkNotNull(character, "character");
		spawnService.unspawn(character);
		eventDispatcher.dispatch(new CharacterLeaveWorldEvent(character));
	}

	@Override
	public void target(L2Character character, Actor target)
			throws CannotSetTargetServiceException {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(target, "target");
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);

		if (target == null && character.getTargetID() != null) {
			// if is trying to select null (remove target) and the character has
			// an target, trigger an deselect
			final Actor oldTarget = character.getTarget();
			character.setTargetID(null);
			eventDispatcher.dispatch(new CharacterTargetDeselectedEvent(
					character, oldTarget));
			// TODO we need to send an packet here to inform of deselection
		} else if (target != null && !target.getID().equals(character.getID())) {
			// if new target is not null and the current character target is
			// null or different, trigger the selection.
			if (character.getTargetID() != null) {
				// first deselect old target
				eventDispatcher.dispatch(new CharacterTargetDeselectedEvent(
						character, character.getTarget()));
			}
			// now select the new target
			character.setTargetID(target.getID());
			eventDispatcher.dispatch(new CharacterTargetSelectedEvent(
					character, target));
			conn.write(new CharacterTargetSelectedPacket(target));
		} else {
			// this indicates an inconsistency: reset target and throws an
			// exception
			// this happens if tried deselect with no target
			character.setTargetID(null);
			throw new CannotSetTargetServiceException();
		}
	}

	@Override
	public void attack(L2Character character, Actor target)
			throws CannotSetTargetServiceException, ActorIsNotAttackableServiceException {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(target, "target");
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		// check if this Actor can be attacked
		if (target instanceof NPC) {
			final NPC npc = (NPC) target;
			final NPCTemplate template = npc.getTemplate();
			if (!template.isAttackable()) {
				throw new ActorIsNotAttackableServiceException();
			}
			// first try to target this, if it is not already
			target(character, target);

			// TODO issue attack
		}
	}

	@Override
	public void appearing(L2Character character) {
		Preconditions.checkNotNull(character, "character");
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);

		character.setState(null);

		conn.write(new CharacterInformationPacket(character));
		conn.write(new CharacterInformationExtraPacket(character));
		eventDispatcher.dispatch(new PlayerTeleportedEvent(character, character
				.getPoint()));
	}

	@Override
	public void jail(L2Character character, long time, String reason)
			throws CharacterInJailServiceException {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkArgument(time > 0, "time <= 0");
		Preconditions.checkNotNull(reason, "reason");
		// TODO implement jailing
		throw new CharacterInJailServiceException();
	}

	@Override
	public void unjail(L2Character character)
			throws CharacterNotInJailServiceException {
		Preconditions.checkNotNull(character, "character");
		// TODO implement jailing
		throw new CharacterNotInJailServiceException();
	}

	@Override
	public void move(L2Character character, Coordinate coordinate) {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(coordinate, "coordinate");
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		// we don't set the character coordinate here, this will be done by
		// validation packets, sent by client

		character.setState(CharacterState.MOVING);

		// for now, let's just write the packet, we don't have much validation
		// to be done yet. With character validation packet, another packet of
		// these will be broadcasted.
		conn.write(new ActorMovementPacket(character, coordinate));
		// we don't dispatch events here, they will be dispatched by
		// with the same packet referred up here.
	}

	@Override
	public void validate(L2Character character, Point point) {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(point, "point");
		// TODO implement position validation
	}

	@Override
	public void receivedValidation(L2Character character, Point point) {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(point, "point");
		if (character.isTeleporting())
			// ignore while teleporting, for some reason the client sends a
			// validation just before teleport packet
			return;
		character.setPoint(point);
		character.setState(CharacterState.MOVING);
		eventDispatcher.dispatch(new CharacterMoveEvent(character, point));
	}

	@Override
	public void walk(L2Character character)
			throws CharacterAlreadyWalkingServiceException {
		Preconditions.checkNotNull(character, "character");
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		// test if character is running
		if (character.getMoveType() == CharacterMoveType.WALK)
			throw new CharacterAlreadyWalkingServiceException();
		// if running set mode to walk and broadcast packet
		character.setMoveType(CharacterMoveType.WALK);

		eventDispatcher.dispatch(new CharacterWalkingEvent(character));
		conn.write(new CharacterMovementTypePacket(character));
	}

	@Override
	public void run(L2Character character)
			throws CharacterAlreadyRunningServiceException {
		Preconditions.checkNotNull(character, "character");
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		// test if character is walking
		if (character.getMoveType() == CharacterMoveType.RUN)
			throw new CharacterAlreadyRunningServiceException();
		// if running walking mode to run and broadcast packet
		character.setMoveType(CharacterMoveType.RUN);

		eventDispatcher.dispatch(new CharacterRunningEvent(character));
		conn.write(new CharacterMovementTypePacket(character));
	}
}
