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
package com.l2jserver.service.game;

import com.google.inject.Inject;
import com.l2jserver.db.dao.ItemDAO;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.SystemMessage;
import com.l2jserver.game.net.packet.server.ActionFailedPacket;
import com.l2jserver.game.net.packet.server.ActorChatMessagePacket;
import com.l2jserver.game.net.packet.server.ActorMovementPacket;
import com.l2jserver.game.net.packet.server.CharacterInformationPacket;
import com.l2jserver.game.net.packet.server.CharacterInventoryPacket;
import com.l2jserver.game.net.packet.server.CharacterMovementTypePacket;
import com.l2jserver.game.net.packet.server.CharacterTargetSelectedPacket;
import com.l2jserver.game.net.packet.server.GameGuardQueryPacket;
import com.l2jserver.game.net.packet.server.NPCInformationPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterMoveType;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.model.world.character.event.CharacterEnterWorldEvent;
import com.l2jserver.model.world.character.event.CharacterEvent;
import com.l2jserver.model.world.character.event.CharacterLeaveWorldEvent;
import com.l2jserver.model.world.character.event.CharacterListener;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.model.world.character.event.CharacterTargetDeselectedEvent;
import com.l2jserver.model.world.character.event.CharacterTargetSelectedEvent;
import com.l2jserver.model.world.npc.event.NPCSpawnEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.chat.ChatMessageDestination;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.chat.channel.ChatChannel;
import com.l2jserver.service.game.chat.channel.ChatChannelListener;
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
	public void enterWorld(final L2Character character) {
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		if (conn == null)
			return;
		if (!worldService.add(character))
			// character is already in the world!
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
		final WorldListener broadcastListener = new FilteredWorldListener<Positionable>(
				new KnownListFilter(character)) {
			@Override
			protected boolean dispatch(WorldEvent e, Positionable object) {
				if (e instanceof NPCSpawnEvent) {
					conn.write(new NPCInformationPacket((NPC) object));
				} else if (e instanceof CharacterEnterWorldEvent) {
					// conn.write(packet)
					// TODO char broadcast
				} else if (e instanceof CharacterMoveEvent) {
					final CharacterMoveEvent evt = (CharacterMoveEvent) e;
					conn.write(new ActorMovementPacket((L2Character) object,
							evt.getPoint().getCoordinate()));
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
		// TODO game guard enforcing
		conn.write(new GameGuardQueryPacket());
		conn.write(new CharacterInventoryPacket(character.getInventory()));

		conn.sendSystemMessage(SystemMessage.WELCOME_TO_LINEAGE);
		conn.sendMessage("This an an development version for l2jserver 2.0");
		conn.sendMessage("Please note that many of the features are not yet implemented.");

		// characters start in run mode
		run(character);

		// broadcast knownlist -- trashy implementation
		// TODO should be moved to world service
		for (final WorldObject o : worldService.iterable(new KnownListFilter(
				character))) {
			if (o instanceof NPC) {
				conn.write(new NPCInformationPacket((NPC) o));
				// conn.write(new ServerObjectPacket((NPC) o));
			}
		}

		// dispatch enter world event
		eventDispatcher.dispatch(new CharacterEnterWorldEvent(character));

		// spawn the player -- this will also dispatch a spawn event
		spawnService.spawn(character, null);
	}

	@Override
	public void move(L2Character character, Coordinate coordinate) {
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		// for now, let's just write the packet
		// aiService.walk(character, coordinate);

		final Coordinate source = character.getPosition();
		// we don't set the character coordinate yet, this will be done by
		// validate coordinate
		conn.write(new ActorMovementPacket(character, coordinate));
		// we don't dispatch events here, they will be dispatched by
		// receivedValidation at fixed time intervals.
	}

	@Override
	public void leaveWorld(L2Character character) {
		if (!worldService.remove(character))
			return;
		eventDispatcher.dispatch(new CharacterLeaveWorldEvent(character));
	}

	@Override
	public void target(L2Character character, Actor target) {
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);

		if (target == null && character.getTargetID() != null) {
			final Actor oldTarget = character.getTarget();
			character.setTargetID(null);
			eventDispatcher.dispatch(new CharacterTargetDeselectedEvent(
					character, oldTarget));
		} else if (target != null && !target.getID().equals(character.getID())) {
			if (character.getTargetID() != null) {
				eventDispatcher.dispatch(new CharacterTargetDeselectedEvent(
						character, character.getTarget()));
			}
			character.setTargetID(null);
			eventDispatcher.dispatch(new CharacterTargetSelectedEvent(
					character, target));
			conn.write(new CharacterTargetSelectedPacket(target));
		} else {
			conn.sendActionFailed();
		}
	}

	@Override
	public void attack(L2Character character, Actor target) {
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		// check if this Actor can be attacked
		if (target instanceof NPC) {
			final NPC npc = (NPC) target;
			if (!npc.getTemplate().isAttackable()) {
				conn.write(ActionFailedPacket.SHARED_INSTANCE);
				return;
			}

		}
	}

	@Override
	public void validate(L2Character character, Point point) {
		// TODO implement position validation
	}

	@Override
	public void receivedValidation(L2Character character, Point point) {
		character.setPoint(point);
		eventDispatcher.dispatch(new CharacterMoveEvent(character, point));
	}

	@Override
	public void walk(L2Character character) {
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		if (character.getMoveType() == CharacterMoveType.RUN) {
			character.setMoveType(CharacterMoveType.WALK);
			conn.broadcast(new CharacterMovementTypePacket(character));
		}
	}

	@Override
	public void run(L2Character character) {
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		if (character.getMoveType() == CharacterMoveType.WALK) {
			character.setMoveType(CharacterMoveType.RUN);
			conn.broadcast(new CharacterMovementTypePacket(character));
		}
	}
}
