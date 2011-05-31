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
import com.l2jserver.game.net.packet.server.SM_CHAR_INFO;
import com.l2jserver.game.net.packet.server.SM_CHAR_INFO_EXTRA;
import com.l2jserver.game.net.packet.server.SM_CHAR_INVENTORY;
import com.l2jserver.game.net.packet.server.SM_CHAT;
import com.l2jserver.game.net.packet.server.SM_MOVE;
import com.l2jserver.game.net.packet.server.SM_MOVE_TYPE;
import com.l2jserver.game.net.packet.server.SM_TARGET;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterMoveType;
import com.l2jserver.model.world.L2Character.CharacterState;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.character.event.CharacterEnterWorldEvent;
import com.l2jserver.model.world.character.event.CharacterEvent;
import com.l2jserver.model.world.character.event.CharacterLeaveWorldEvent;
import com.l2jserver.model.world.character.event.CharacterListener;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.model.world.character.event.CharacterRunningEvent;
import com.l2jserver.model.world.character.event.CharacterTargetDeselectedEvent;
import com.l2jserver.model.world.character.event.CharacterTargetSelectedEvent;
import com.l2jserver.model.world.character.event.CharacterWalkingEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.AttackService;
import com.l2jserver.service.game.chat.ChatChannel;
import com.l2jserver.service.game.chat.ChatChannelListener;
import com.l2jserver.service.game.chat.ChatMessageDestination;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.service.game.npc.NotAttackableNPCServiceException;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.service.network.broadcast.BroadcastService;
import com.l2jserver.service.network.gameguard.GameGuardService;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.geometry.Point3D;

/**
 * Default implementation for {@link CharacterService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class, ChatService.class, NetworkService.class,
		SpawnService.class, AttackService.class, GameGuardService.class,
		BroadcastService.class })
public class CharacterServiceImpl extends AbstractService implements
		CharacterService {
	/**
	 * The {@link BroadcastService}
	 */
	private final BroadcastService broadcastService;
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
	 * The {@link NPCService}
	 */
	private final NPCService npcService;
	/**
	 * The {@link GameGuardService}
	 */
	private final GameGuardService ggService;
	/**
	 * The {@link ItemDAO}
	 */
	private final ItemDAO itemDao;

	// /**
	// * The {@link AIService}
	// */
	// private final AIService aiService;

	@Inject
	public CharacterServiceImpl(BroadcastService broadcastService,
			WorldEventDispatcher eventDispatcher, ChatService chatService,
			NetworkService networkService, SpawnService spawnService,
			NPCService npcService, GameGuardService ggService, ItemDAO itemDao) {
		this.broadcastService = broadcastService;
		this.eventDispatcher = eventDispatcher;
		this.chatService = chatService;
		this.networkService = networkService;
		this.spawnService = spawnService;
		this.npcService = npcService;
		this.ggService = ggService;
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

		character.setOnline(true);

		// inventory interfere on calculators
		character.getStats().updateCalculator();

		// chat listener
		final ChatChannelListener globalChatListener = new ChatChannelListener() {
			@Override
			public void onMessage(ChatChannel channel, CharacterID source,
					String message) {
				conn.write(new SM_CHAT(source.getObject(),
						ChatMessageDestination.ALL, message));
			}
		};
		final ChatChannelListener tradeChatListener = new ChatChannelListener() {
			@Override
			public void onMessage(ChatChannel channel, CharacterID source,
					String message) {
				conn.write(new SM_CHAT(source.getObject(),
						ChatMessageDestination.TRADE, message));
			}
		};

		// start broadcasting -- will broadcast all nearby objects
		broadcastService.broadcast(conn);

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

				// // remove broadcast listener
				// eventDispatcher.removeListener(neighboorListener);
				// eventDispatcher.removeListener(id, sendPacketListener);s

				// we can kill this listener now
				return false;
			}
		});

		// register global chat listener
		chatService.getGlobalChannel().addChatChannelListener(
				globalChatListener);
		chatService.getTradeChannel().addChatChannelListener(tradeChatListener);

		// query client game guard -- if key is invalid, the connection will be
		// closed as soon as possible
		ggService.query(conn);

		// send this user information
		conn.write(new SM_CHAR_INFO(character));
		conn.write(new SM_CHAR_INFO_EXTRA(character));
		conn.write(new SM_CHAR_INVENTORY(character.getInventory()));

		conn.sendSystemMessage(SystemMessage.WELCOME_TO_LINEAGE);
		conn.sendMessage("This an an development version for l2jserver 2.0");
		conn.sendMessage("Please note that many of the features are not yet implemented.");

		// characters start in run mode
		try {
			run(character);
		} catch (CharacterAlreadyRunningServiceException e1) {
			// we can ignore this one
		}

		// broadcast(conn, character);

		// spawn the player -- this will also dispatch a spawn event
		// here the object is registered in the world
		spawnService.spawn(character, null);

		// dispatch enter world event
		eventDispatcher.dispatch(new CharacterEnterWorldEvent(character));
	}

	@Override
	public void leaveWorld(L2Character character)
			throws NotSpawnedServiceException {
		Preconditions.checkNotNull(character, "character");
		spawnService.unspawn(character);
		eventDispatcher.dispatch(new CharacterLeaveWorldEvent(character));
		character.setOnline(false);
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
			conn.write(new SM_TARGET(target, character.getLevel()
					- target.getLevel()));
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
			throws CannotSetTargetServiceException,
			ActorIsNotAttackableServiceException,
			NotAttackableNPCServiceException {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(target, "target");
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		// check if this Actor can be attacked
		if (target instanceof NPC) {
			final NPC npc = (NPC) target;
			// first try to target this, if it is not already
			target(character, target);

			npcService.attack(npc, conn, character);
		} else {
			// TODO throw an exception
			conn.sendActionFailed();
		}
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
		character.setTargetLocation(coordinate.toPoint());

		// for now, let's just write the packet, we don't have much validation
		// to be done yet. With character validation packet, another packet of
		// these will be broadcasted.
		conn.write(new SM_MOVE(character, coordinate));
		// we don't dispatch events here, they will be dispatched by
		// with the same packet referred up here.
	}

	@Override
	public void validate(L2Character character, Point3D point) {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(point, "point");
		// TODO implement position validation
	}

	@Override
	public void receivedValidation(L2Character character, Point3D point) {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(point, "point");
		if (character.isTeleporting())
			// ignore while teleporting, for some reason the client sends a
			// validation just before teleport packet
			return;
		final Point3D old = character.getPoint();
		character.setPoint(point);
		eventDispatcher.dispatch(new CharacterMoveEvent(character, old));

		if (point.getCoordinate().equals(
				character.getTargetLocation().getCoordinate())) {
			character.setState(null);
			character.setTargetLocation(null);
			// TODO dispatch stop event
		}
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
		conn.write(new SM_MOVE_TYPE(character));
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
		conn.write(new SM_MOVE_TYPE(character));
	}
}
