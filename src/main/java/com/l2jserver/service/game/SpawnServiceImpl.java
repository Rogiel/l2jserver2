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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.server.CharacterTeleportPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.model.world.npc.event.NPCSpawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.dimensional.Point;

/**
 * Default implementation for {@link SpawnService}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class })
public class SpawnServiceImpl extends AbstractService implements SpawnService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link WorldService}
	 */
	private final WorldService worldService;
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;
	/**
	 * The {@link NetworkService}
	 */
	private final NetworkService networkService;

	@Inject
	public SpawnServiceImpl(WorldService worldService,
			WorldEventDispatcher eventDispatcher, NetworkService networkService) {
		this.worldService = worldService;
		this.eventDispatcher = eventDispatcher;
		this.networkService = networkService;
	}

	@Override
	public void spawn(Spawnable spawnable, Point point) {
		// sanitize
		if (point == null)
			// retrieving stored point
			point = spawnable.getPoint();
		if (point == null) {
			// not point send and no point stored, aborting
			// TODO this should throw an exception
			log.warn("Trying to spawn {} to a null point", spawnable);
			return;
		}

		// set the spawning point
		spawnable.setPoint(point);
		// register object in the world
		if (!worldService.add(spawnable))
			// TODO this should throw an exception
			// object was already in world
			return;

		// create the SpawnEvent
		SpawnEvent event = null;
		if (spawnable instanceof NPC) {
			final NPC npc = (NPC) spawnable;
			event = new NPCSpawnEvent(npc, point);
		} else if (spawnable instanceof L2Character) {
			event = null;
		}

		// TODO throw an exception if event is null
		if (event != null)
			// dispatch spawn event
			eventDispatcher.dispatch(event);
		// remember: broadcasting is done through events!
	}

	@Override
	public void teleport(Player player, Coordinate coordinate) {
		player.setPosition(coordinate);
		if (player instanceof L2Character) {
			final Lineage2Connection conn = networkService
					.discover((CharacterID) player.getID());
			if (conn == null)
				// TODO throw an exception here
				return;
			conn.write(new CharacterTeleportPacket(conn.getCharacter()));
		}
		// dispatch teleport event
		eventDispatcher.dispatch(new PlayerTeleportEvent(player, coordinate
				.toPoint()));
		// remember: broadcasting is done through events!
	}

	@Override
	public void scheduleRespawn(Spawnable spawnable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unspawn(Spawnable spawnable) {
		// TODO Auto-generated method stub

	}
}
