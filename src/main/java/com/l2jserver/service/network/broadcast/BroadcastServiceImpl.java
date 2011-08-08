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
package com.l2jserver.service.network.broadcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.SystemMessage;
import com.l2jserver.game.net.packet.server.SM_ATTACK;
import com.l2jserver.game.net.packet.server.SM_CHAR_INFO_BROADCAST;
import com.l2jserver.game.net.packet.server.SM_DIE;
import com.l2jserver.game.net.packet.server.SM_MOVE;
import com.l2jserver.game.net.packet.server.SM_MOVE_TYPE;
import com.l2jserver.game.net.packet.server.SM_NPC_INFO;
import com.l2jserver.game.net.packet.server.SM_OBJECT_REMOVE;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.actor.event.ActorAttackHitEvent;
import com.l2jserver.model.world.actor.event.ActorDieEvent;
import com.l2jserver.model.world.actor.event.ActorUnspawnEvent;
import com.l2jserver.model.world.character.event.CharacterEnterWorldEvent;
import com.l2jserver.model.world.character.event.CharacterLeaveWorldEvent;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.model.world.character.event.CharacterRunningEvent;
import com.l2jserver.model.world.character.event.CharacterWalkingEvent;
import com.l2jserver.model.world.npc.event.NPCSpawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportedEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportingEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
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

	private final WorldService worldService;
	private final WorldEventDispatcher eventDispatcher;

	@Inject
	public BroadcastServiceImpl(WorldService worldService,
			WorldEventDispatcher eventDispatcher) {
		this.worldService = worldService;
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	public void broadcast(final Lineage2Client conn) {
		Preconditions.checkNotNull(conn, "conn");
		final L2Character character = conn.getCharacter();
		Preconditions.checkNotNull(character, "character");
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
				if (e instanceof NPCSpawnEvent) {
					broadcast(conn, e.getObject());
				} else if (e instanceof CharacterMoveEvent) {
					final CharacterMoveEvent evt = (CharacterMoveEvent) e;
					conn.write(new SM_MOVE((L2Character) object, evt.getPoint()
							.getCoordinate()));
				} else if (e instanceof PlayerTeleportedEvent
						|| e instanceof CharacterEnterWorldEvent) {
					broadcast(conn, e.getObject());
				} else if (e instanceof PlayerTeleportingEvent
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
					conn.write(new SM_DIE(((ActorDieEvent) e).getActor()));
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
					broadcastUpdate(conn, character, evt.getPoint());
				} else if (e instanceof PlayerTeleportedEvent
						|| e instanceof CharacterEnterWorldEvent) {
					broadcastAll(conn, character);
				} else if (e instanceof ActorAttackHitEvent) {
					conn.write(new SM_ATTACK(((ActorAttackHitEvent) e).getHit()));
					conn.sendSystemMessage(SystemMessage.YOU_DID_S1_DMG,
							(int) ((ActorAttackHitEvent) e).getHit()
									.getDamage());
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
	 * @param character
	 *            the character
	 */
	private void broadcast(Lineage2Client conn, WorldObject o) {
		log.debug("Broadcasting {}  to {}", o, conn);
		if (o instanceof NPC) {
			conn.write(new SM_NPC_INFO((NPC) o));
		} else if (o instanceof L2Character) {
			conn.write(new SM_CHAR_INFO_BROADCAST((L2Character) o));
		}
	}
}
