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
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.server.CharacterTeleportPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.player.event.PlayerTeleportEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * Default implementation for {@link SpawnService}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class })
public class SpawnServiceImpl extends AbstractService implements SpawnService {
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;
	/**
	 * The {@link NetworkService}
	 */
	private final NetworkService networkService;

	@Inject
	public SpawnServiceImpl(WorldEventDispatcher eventDispatcher,
			NetworkService networkService) {
		this.eventDispatcher = eventDispatcher;
		this.networkService = networkService;
	}

	@Override
	public void spawn(Spawnable spawnable) {
		// TODO Auto-generated method stub
	}

	@Override
	public void teleport(Player player, Coordinate coordinate) {
		player.setPosition(coordinate);
		if (player instanceof L2Character) {
			final Lineage2Connection conn = networkService
					.discover((CharacterID) player.getID());
			if (conn == null)
				return;
			conn.write(new CharacterTeleportPacket(conn.getCharacter()));
		}
		// dispatch events
		eventDispatcher.dispatch(new PlayerTeleportEvent(player, coordinate));
		// TODO broadcast this player new position
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
