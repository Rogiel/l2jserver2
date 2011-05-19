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

import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportEvent;
import com.l2jserver.service.Service;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.dimensional.Point;

/**
 * This service is responsible for spawning monsters, npcs and players.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface SpawnService extends Service {
	/**
	 * Spawns an object in the world
	 * <p>
	 * An {@link SpawnEvent} will be dispatched and the object will be
	 * registered in the world (if it isn't already)
	 * 
	 * @param spawnable
	 *            the spawnable object
	 * @param point
	 *            the spawning point. If null, will try to use
	 *            {@link Spawnable#getPoint()}.
	 */
	void spawn(Spawnable spawnable, Point point);

	/**
	 * Teleports the object to the given <tt>point</tt>.
	 * <p>
	 * An {@link PlayerTeleportEvent} will be dispatched and the new position
	 * will be broadcast to all clients.
	 * 
	 * @param player
	 *            the player object
	 * @param coordinate
	 *            the teleportation coordinate
	 */
	void teleport(Player player, Coordinate coordinate);

	/**
	 * Schedules an {@link Spawnable} object to be respawn in a certain time.
	 * 
	 * @param spawnable
	 *            the spawnable object
	 */
	void scheduleRespawn(Spawnable spawnable);

	/**
	 * Unspawns an object from the world
	 * 
	 * @param spawnable
	 *            the spawnable object
	 */
	void unspawn(Spawnable spawnable);
}
