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
package com.l2jserver.model.world.actor.event;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.util.dimensional.Point;

/**
 * Event dispatcher once an actor has spawned in the world
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorSpawnEvent implements ActorEvent, SpawnEvent {
	/**
	 * The spawned player
	 */
	private final Actor actor;
	/**
	 * The spawning point
	 */
	private final Point point;

	/**
	 * Creates a new instance
	 * 
	 * @param actor
	 *            the spawned actor
	 * @param point
	 *            the spawn point
	 */
	public ActorSpawnEvent(Actor actor, Point point) {
		this.actor = actor;
		this.point = point;
	}

	@Override
	public WorldObject getObject() {
		return actor;
	}

	@Override
	public Actor getActor() {
		return actor;
	}

	@Override
	public Point getPoint() {
		return point;
	}

	@Override
	public ObjectID<?>[] getDispatchableObjects() {
		return new ObjectID<?>[] { actor.getID() };
	}
}
