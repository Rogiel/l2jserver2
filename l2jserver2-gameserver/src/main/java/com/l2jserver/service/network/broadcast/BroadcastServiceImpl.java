/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.network.broadcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.SystemMessage;
import com.l2jserver.game.net.packet.server.SM_ACTOR_CHAT;
import com.l2jserver.game.net.packet.server.SM_ACTOR_DIE;
import com.l2jserver.game.net.packet.server.SM_ACTOR_MOVE;
import com.l2jserver.game.net.packet.server.SM_ATTACK;
import com.l2jserver.game.net.packet.server.SM_CHAR_INFO;
import com.l2jserver.game.net.packet.server.SM_CHAR_INFO_BROADCAST;
import com.l2jserver.game.net.packet.server.SM_CHAR_INFO_EXTRA;
import com.l2jserver.game.net.packet.server.SM_CHAR_INVENTORY;
import com.l2jserver.game.net.packet.server.SM_ITEM_GROUND;
import com.l2jserver.game.net.packet.server.SM_ITEM_PICK;
import com.l2jserver.game.net.packet.server.SM_MOVE_TYPE;
import com.l2jserver.game.net.packet.server.SM_NPC_INFO;
import com.l2jserver.game.net.packet.server.SM_OBJECT_REMOVE;
import com.l2jserver.game.net.packet.server.SM_TARGET;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.actor.event.ActorAttackHitEvent;
import com.l2jserver.model.world.actor.event.ActorDieEvent;
import com.l2jserver.model.world.actor.event.ActorTeleportingEvent;
import com.l2jserver.model.world.actor.event.ActorUnspawnEvent;
import com.l2jserver.model.world.character.event.CharacterEnterWorldEvent;
import com.l2jserver.model.world.character.event.CharacterEvent;
import com.l2jserver.model.world.character.event.CharacterLeaveWorldEvent;
import com.l2jserver.model.world.character.event.CharacterListener;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.model.world.character.event.CharacterRunningEvent;
import com.l2jserver.model.world.character.event.CharacterStartMovingEvent;
import com.l2jserver.model.world.character.event.CharacterTargetSelectedEvent;
import com.l2jserver.model.world.character.event.CharacterWalkingEvent;
import com.l2jserver.model.world.item.ItemDropEvent;
import com.l2jserver.model.world.item.ItemPickEvent;
import com.l2jserver.model.world.npc.event.NPCSpawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportedEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.chat.ChatChannel;
import com.l2jserver.service.game.chat.ChatChannelListener;
import com.l2jserver.service.game.chat.ChatMessageType;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.FilteredWorldListener;
import com.l2jserver.service.game.world.event.WorldEvent;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.game.world.event.WorldListener;
import com.l2jserver.service.game.world.filter.impl.KnownListFilter;
import com.l2jserver.service.game.world.filter.impl.KnownListUpdateFilter;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.geometry.Point3D;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ NetworkService.class, WorldService.class })
public class BroadcastServiceImpl extends AbstractService implements
		BroadcastService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The world service
	 */
	private final WorldService worldService;
	/**
	 * The {@link ChatService}
	 */
	private final ChatService chatService;
	/**
	 * The {@link ChatService}
	 */
	private final NetworkService networkService;

	/**
	 * The world service event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;

	/**
	 * @param worldService
	 *            the world service
	 * @param chatService
	 *            the chat service
	 * @param networkService
	 *            the network service
	 * @param eventDispatcher
	 *            the world service event disptacher
	 */
	@Inject
	public BroadcastServiceImpl(WorldService worldService,
			ChatService chatService, NetworkService networkService,
			WorldEventDispatcher eventDispatcher) {
		this.worldService = worldService;
		this.chatService = chatService;
		this.networkService = networkService;
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	public void broadcast(final L2Character character) {
		Preconditions.checkNotNull(character, "character");
		final Lineage2Client conn = networkService.discover(character.getID());
		Preconditions.checkNotNull(conn, "conn");
		final CharacterID id = character.getID();

		log.debug("Starting character broadcast");

		// broadcast everything nearby
		// broadcast(conn);

		// event broadcast listener
		// this listener will be filtered so that only interesting events are
		// dispatched, once a event arrives will be possible to check check if
		// the given event will be broadcasted or not
		final WorldListener neighborListener = new FilteredWorldListener<PositionableObject>(
				new KnownListFilter(character)) {
			@Override
			protected boolean dispatch(WorldEvent e, PositionableObject object) {
				log.debug("Broadcast event received: {}", e);
				if (e instanceof NPCSpawnEvent || e instanceof ItemDropEvent) {
					broadcast(conn, e.getObject());
				} else if (e instanceof CharacterMoveEvent) {
					final CharacterMoveEvent evt = (CharacterMoveEvent) e;
					conn.write(new SM_ACTOR_MOVE((L2Character) object, evt
							.getPoint().getCoordinate()));
				} else if (e instanceof PlayerTeleportedEvent
						|| e instanceof CharacterEnterWorldEvent) {
					broadcast(conn, e.getObject());
				} else if (e instanceof ItemPickEvent) {
					conn.write(new SM_ITEM_PICK(((ItemPickEvent) e)
							.getCharacter(), (Item) object));
					conn.write(new SM_OBJECT_REMOVE(object));
				} else if (e instanceof ActorTeleportingEvent
						|| e instanceof CharacterLeaveWorldEvent
						|| e instanceof ActorUnspawnEvent) {
					// object is now out of sight
					conn.write(new SM_OBJECT_REMOVE(object));
				} else if (e instanceof CharacterWalkingEvent) {
					conn.write(new SM_MOVE_TYPE(((CharacterWalkingEvent) e)
							.getCharacter()));
				} else if (e instanceof CharacterRunningEvent) {
					conn.write(new SM_MOVE_TYPE(((CharacterRunningEvent) e)
							.getCharacter()));
				} else if (e instanceof ActorDieEvent) {
					conn.write(new SM_ACTOR_DIE(((ActorDieEvent) e).getActor()));
				}
				// keep listener alive
				return true;
			}
		};
		// add the global listener
		eventDispatcher.addListener(neighborListener);
		// this listener is bound directly to the character, no need for filters
		// or any other test inside listener
		final WorldListener sendPacketListener = new WorldListener() {
			@Override
			public boolean dispatch(WorldEvent e) {
				log.debug("Broadcast event received: {}", e);
				if (e instanceof CharacterMoveEvent) {
					final CharacterMoveEvent evt = (CharacterMoveEvent) e;
					// process update known list
					broadcastUpdate(conn, evt.getCharacter(), evt.getPoint());
				} else if (e instanceof CharacterEnterWorldEvent) {
					final CharacterEnterWorldEvent evt = (CharacterEnterWorldEvent) e;
					final L2Character character = evt.getCharacter();
					final CharacterID id = character.getID();

					// chat listener
					final ChatChannelListener globalChatListener = new ChatChannelListener() {
						@Override
						public void onMessage(ChatChannel channel,
								ChatMessage message) {
							conn.write(new SM_ACTOR_CHAT(message.getSender()
									.getObject(), ChatMessageType.ALL, message
									.getMessage()));
						}
					};
					final ChatChannelListener tradeChatListener = new ChatChannelListener() {
						@Override
						public void onMessage(ChatChannel channel,
								ChatMessage message) {
							conn.write(new SM_ACTOR_CHAT(message.getSender()
									.getObject(), ChatMessageType.TRADE,
									message.getMessage()));
						}
					};

					// leave world event
					eventDispatcher.addListener(id, new CharacterListener() {
						@Override
						protected boolean dispatch(CharacterEvent e) {
							if (!(e instanceof CharacterLeaveWorldEvent))
								return true;

							log.debug(
									"Character {} is leaving world, removing chat listeners",
									character);

							// remove chat listeners
							chatService.getGlobalChannel()
									.removeMessageListener(globalChatListener);
							chatService.getTradeChannel()
									.removeMessageListener(tradeChatListener);

							// we can kill this listener now
							return false;
						}
					});

					// register global chat listener
					chatService.getGlobalChannel().addMessageListener(
							globalChatListener);
					chatService.getTradeChannel().addMessageListener(
							tradeChatListener);

					log.debug("Sending greeting message to client");
					conn.sendSystemMessage(SystemMessage.WELCOME_TO_LINEAGE);
					conn.sendMessage("This an an development version for l2jserver 2.0");
					conn.sendMessage("Please note that many of the features are not yet implemented.");

					// send this user information
					log.debug("Sending character information packets");
					conn.write(new SM_CHAR_INFO(evt.getCharacter()));
					conn.write(new SM_CHAR_INFO_EXTRA(evt.getCharacter()));
					conn.write(new SM_CHAR_INVENTORY(evt.getCharacter()
							.getInventory()));
					
					broadcastAll(conn, character);
				} else if (e instanceof CharacterStartMovingEvent) {
					conn.write(new SM_ACTOR_MOVE(
							((CharacterStartMovingEvent) e).getCharacter(),
							((CharacterStartMovingEvent) e).getPoint()
									.getCoordinate()));
				} else if (e instanceof CharacterTargetSelectedEvent) {
					final CharacterTargetSelectedEvent evt = (CharacterTargetSelectedEvent) e;
					conn.write(new SM_TARGET(evt.getTarget(), evt
							.getCharacter().getLevel()
							- evt.getTarget().getLevel()));
				} else if (e instanceof PlayerTeleportedEvent) {
					broadcastAll(conn, character);
				} else if (e instanceof ActorAttackHitEvent) {
					conn.write(new SM_ATTACK(((ActorAttackHitEvent) e).getHit()));
					conn.sendSystemMessage(SystemMessage.YOU_DID_S1_DMG,
							(int) ((ActorAttackHitEvent) e).getHit()
									.getDamage());
				} else if (e instanceof CharacterWalkingEvent
						|| e instanceof CharacterRunningEvent) {
					conn.write(new SM_MOVE_TYPE((L2Character) e.getObject()));
				}
				// keep listener alive
				return true;
			}
		};
		// add listener only for this character
		eventDispatcher.addListener(id, sendPacketListener);
	}

	/**
	 * Broadcast all nearby objects to this client
	 * 
	 * @param conn
	 *            the connection
	 * @param character
	 *            the character
	 */
	private void broadcastAll(Lineage2Client conn, L2Character character) {
		log.debug("Broadcasting all near objects to {}", character);
		for (final WorldObject o : worldService.iterable(new KnownListFilter(
				character))) {
			broadcast(conn, o);
		}
	}

	/**
	 * Broadcast new nearby objects to this client. Will only broadcast if the
	 * object was out-of-range before and now is in range.
	 * 
	 * @param conn
	 *            the connection
	 * @param character
	 *            the character
	 * @param point
	 *            the old point
	 */
	private void broadcastUpdate(Lineage2Client conn, L2Character character,
			Point3D point) {
		log.debug("Broadcasting only new near objects to {}", character);
		for (final WorldObject o : worldService
				.iterable(new KnownListUpdateFilter(character, point))) {
			broadcast(conn, o);
		}
	}

	/**
	 * Broadcast an object to this client
	 * 
	 * @param conn
	 *            the connection
	 * @param o
	 *            the object to be broadcasted
	 */
	private void broadcast(Lineage2Client conn, WorldObject o) {
		log.debug("Broadcasting {}  to {}", o, conn);
		if (o instanceof NPC) {
			conn.write(new SM_NPC_INFO((NPC) o));
		} else if (o instanceof L2Character) {
			conn.write(new SM_CHAR_INFO_BROADCAST((L2Character) o));
		} else if (o instanceof Item) {
			conn.write(new SM_ITEM_GROUND((Item) o));
		}
	}
}
