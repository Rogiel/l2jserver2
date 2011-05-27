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
package com.l2jserver.service.game.spawn;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterState;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportedEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportingEvent;
import com.l2jserver.service.Service;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.geometry.Point3D;

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
	 * @param object
	 *            the spawnable object
	 * @param point
	 *            the spawning point. If null, will try to use
	 *            {@link Spawnable#getPoint()}.
	 * @throws SpawnPointNotFoundServiceException
	 *             if could not find an spawn point (i.e <tt>point</tt> and
	 *             {@link Spawnable#getPoint()} are null)
	 * @throws AlreadySpawnedServiceException
	 *             if the object is already spawned in the world
	 */
	void spawn(PositionableObject object, Point3D point)
			throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException;

	/**
	 * Teleports the object to the given <tt>point</tt>.
	 * <p>
	 * An {@link PlayerTeleportingEvent} will be dispatched and the new position
	 * will be broadcast to all clients.
	 * 
	 * @param player
	 *            the player object
	 * @param coordinate
	 *            the teleportation coordinate
	 * @throws NotSpawnedServiceException
	 *             if the object to be teleported is not spawned
	 * @throws CharacterAlreadyTeleportingServiceException
	 *             if this player is already being teleported. This will only be
	 *             thrown for {@link L2Character} instances.
	 */
	void teleport(Player player, Coordinate coordinate)
			throws NotSpawnedServiceException,
			CharacterAlreadyTeleportingServiceException;

	/**
	 * Finishes teleporting the character. This is only used for
	 * {@link L2Character} instances.
	 * <p>
	 * An {@link PlayerTeleportedEvent} will be dispatched and the new position
	 * will be broadcast to all clients.
	 * 
	 * @param character
	 *            the character object
	 * @throws CharacterNotTeleportingServiceException
	 *             if the character state is not
	 *             {@link CharacterState#TELEPORTING}
	 */
	void finishTeleport(L2Character character)
			throws CharacterNotTeleportingServiceException;

	/**
	 * Schedules an {@link Spawnable} object to be respawn in a certain time.
	 * <p>
	 * TODO this is not complete
	 * 
	 * @param spawnable
	 *            the spawnable object
	 */
	void scheduleRespawn(PositionableObject spawnable);

	/**
	 * Unspawns an object from the world
	 * 
	 * @param spawnable
	 *            the spawnable object
	 * @throws NotSpawnedServiceException
	 *             if the object is not spawned
	 */
	void unspawn(PositionableObject spawnable)
			throws NotSpawnedServiceException;
}
