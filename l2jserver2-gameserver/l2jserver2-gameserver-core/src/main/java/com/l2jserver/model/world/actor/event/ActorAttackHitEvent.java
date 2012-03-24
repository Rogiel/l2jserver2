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
import com.l2jserver.model.server.AttackHit;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.WorldObject;

/**
 * Event dispatcher once an actor has received/dealt an attack hit.
 * <p>
 * Please note that the same event is dispatched for both attacker and attackee.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorAttackHitEvent implements ActorEvent {
	/**
	 * The spawned player
	 */
	private final AttackHit hit;

	/**
	 * Creates a new instance
	 * 
	 * @param hit
	 *            the attack hit
	 */
	public ActorAttackHitEvent(AttackHit hit) {
		this.hit = hit;
	}

	@Override
	public WorldObject getObject() {
		return hit.getAttacker();
	}

	@Override
	public Actor getActor() {
		return hit.getAttacker();
	}

	/**
	 * @return the attack hit
	 */
	public AttackHit getHit() {
		return hit;
	}

	@Override
	public ObjectID<?>[] getDispatchableObjects() {
		return new ObjectID<?>[] { hit.getAttacker().getID() };
	}
}
