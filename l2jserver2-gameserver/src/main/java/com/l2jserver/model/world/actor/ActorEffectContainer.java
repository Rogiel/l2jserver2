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
package com.l2jserver.model.world.actor;

import java.util.Iterator;
import java.util.Set;

import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.effect.Effect;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Class controlling active effects on an {@link Actor}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorEffectContainer implements Iterable<Effect> {
	/**
	 * The actor being affected by the effects
	 */
	private final Actor actor;
	/**
	 * The list of all active effects
	 */
	private Set<Effect> effects = CollectionFactory.newSet();

	/**
	 * Creates a new instance
	 * 
	 * @param actor
	 *            the actor
	 */
	public ActorEffectContainer(Actor actor) {
		this.actor = actor;
	}

	public void addEffect(Effect effect) {
		effects.add(effect);
	}

	public void removeEffect(Effect effect) {
		effects.remove(effect);
	}

	@Override
	public Iterator<Effect> iterator() {
		return effects.iterator();
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return actor;
	}
}
