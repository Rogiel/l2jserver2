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
package com.l2jserver.service.game.spawn;

import java.util.concurrent.TimeUnit;

import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Actor.ActorState;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.actor.event.ActorTeleportingEvent;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportedEvent;
import com.l2jserver.service.Service;
import com.l2jserver.service.core.threading.AsyncFuture;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.item.ItemService;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.geometry.Point3D;

/**
 * This service is responsible for spawning, unspawning and teleporting
 * {@link Actor} and Item objects. If you spawn an {@link Item} object it will
 * be the same as dropping it on the ground.
 * <p>
 * This service allows to be used synchronously or asynchronously if you wish to
 * schedule an spawn/unspawn to happen at another time. However, teleporting
 * must be done synchronously. Asynchronous usage is assisted by
 * {@link ThreadService}.
 * <p>
 * Although the service supports teleporting Item objects, it has the same
 * effect of an unspawn and spawn.
 * 
 * <h1>Usage of SpawnService with NPC/Monsters</h1> Although is possible to use
 * this service to unspawn {@link NPC} objects, it is not recommended. If you
 * do, the {@link NPC} will not be respawned. The only possible way to respawn
 * it is through an forced spawn (manual) or server restart. See
 * {@link NPCService} if you wish to correctly unspawn an {@link NPC}.
 * <p>
 * Also note that this service is a low level implementation. In most cases, it
 * might be more suitable the usage of high-level services, like
 * {@link ItemService}, {@link NPCService} or {@link CharacterService}, since
 * they can provide more security and dispatch more events to the
 * {@link WorldService}.
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
	 *            the PositionableObject object
	 * @param point
	 *            the spawning point. If null, will try to use
	 *            {@link PositionableObject#getPoint()}.
	 * @throws SpawnPointNotFoundServiceException
	 *             if could not find an spawn point (i.e <tt>point</tt> and
	 *             {@link PositionableObject#getPoint()} are null)
	 * @throws AlreadySpawnedServiceException
	 *             if the object is already spawned in the world
	 */
	void spawn(PositionableObject object, Point3D point)
			throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException;

	/**
	 * Schedules an {@link PositionableObject} object to be spawned in a certain
	 * time.
	 * 
	 * @param <T>
	 *            the {@link PositionableObject} to be spawned
	 * 
	 * @param object
	 *            the PositionableObject object
	 * @param point
	 *            the spawning point. If null, will try to use
	 *            {@link PositionableObject#getPoint()}.
	 * @param time
	 *            the amount of time to wait before spawn
	 * @param unit
	 *            the unit of <tt>time</tt>
	 * @return an future that can be used to obtain spawn exceptions
	 */
	<T extends PositionableObject> AsyncFuture<T> spawn(T object,
			Point3D point, long time, TimeUnit unit);

	/**
	 * Unspawns an {@link PositionableObject} object from the world
	 * 
	 * @param object
	 *            the PositionableObject object
	 * @throws NotSpawnedServiceException
	 *             if the object is not spawned
	 */
	void unspawn(PositionableObject object) throws NotSpawnedServiceException;

	/**
	 * Schedules an {@link PositionableObject} object to be spawned in a certain
	 * time.
	 * 
	 * @param <T>
	 *            the {@link PositionableObject} to be unspawned
	 * 
	 * @param object
	 *            the PositionableObject object
	 * @param time
	 *            the amount of time to wait before respawn
	 * @param unit
	 *            the unit of <tt>time</tt>
	 * @return an future that can be used to obtain spawn exceptions
	 */
	<T extends PositionableObject> AsyncFuture<T> unspawn(T object, long time,
			TimeUnit unit);

	/**
	 * Teleports the object to the given <tt>point</tt>.
	 * <p>
	 * An {@link ActorTeleportingEvent} will be dispatched and the new position
	 * will be broadcast to all clients.
	 * 
	 * @param actor
	 *            the actor object
	 * @param coordinate
	 *            the teleportation coordinate
	 * @throws NotSpawnedServiceException
	 *             if the object to be teleported is not spawned
	 * @throws CharacterAlreadyTeleportingServiceException
	 *             if <tt>actor</tt> is already being teleported. This will only
	 *             be thrown for {@link L2Character} instances.
	 */
	void teleport(Actor actor, Coordinate coordinate)
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
	 *             if the character state is not {@link ActorState#TELEPORTING}
	 */
	void finishTeleport(L2Character character)
			throws CharacterNotTeleportingServiceException;
}
