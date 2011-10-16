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
package com.l2jserver.model.world.actor.event;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.util.geometry.Point3D;

/**
 * Event dispatched once an actor has started his teleported to another location
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorTeleportingEvent implements ActorEvent {
	/**
	 * The actor object
	 */
	private final Actor actor;
	/**
	 * The teleportation point
	 */
	private final Point3D point;

	/**
	 * Creates a new instance
	 * 
	 * @param actor
	 *            the teleported actor
	 * @param point
	 *            the teleport point
	 */
	public ActorTeleportingEvent(Actor actor, Point3D point) {
		this.actor = actor;
		this.point = point;
	}

	@Override
	public Actor getActor() {
		return actor;
	}

	@Override
	public WorldObject getObject() {
		return actor;
	}

	/**
	 * The point to which this player was teleported.
	 * 
	 * @return the teleported point
	 */
	public Point3D getPoint() {
		return point;
	}

	@Override
	public ObjectID<?>[] getDispatchableObjects() {
		return new ObjectID<?>[] { actor.getID() };
	}
}
