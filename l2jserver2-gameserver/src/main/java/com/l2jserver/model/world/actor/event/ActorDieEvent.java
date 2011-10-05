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

/**
 * Event dispatcher once an actor has died.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorDieEvent implements ActorEvent {
	/**
	 * The actor that died
	 */
	private final Actor actor;
	/**
	 * The actor who killed the <tt>actor</tt>
	 */
	private final Actor killer;

	/**
	 * Creates a new instance
	 * 
	 * @param actor
	 *            the actor that died
	 * @param killer
	 *            the actor who killed the <tt>actor</tt>
	 */
	public ActorDieEvent(Actor actor, Actor killer) {
		this.actor = actor;
		this.killer = killer;
	}

	/**
	 * @return the actor who killed the <tt>actor</tt>
	 */
	public Actor getKiller() {
		return killer;
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
	public ObjectID<?>[] getDispatchableObjects() {
		return new ObjectID<?>[] { actor.getID() };
	}
}
