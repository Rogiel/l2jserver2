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
package com.l2jserver.game.ai;

import com.google.inject.Inject;
import com.l2jserver.game.ai.desires.Desire;
import com.l2jserver.game.ai.desires.DesireQueue;
import com.l2jserver.model.world.Actor;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the {@link Actor} type for this {@link AI}
 */
public abstract class AI<T extends Actor> {
	/**
	 * The desire queue for this AI
	 */
	protected DesireQueue desireQueue = new DesireQueue();
	/**
	 * The actor controlled by this AI
	 */
	protected final T actor;

	@Inject
	protected WorldEventDispatcher eventDispatcher;

	protected AI(T actor) {
		this.actor = actor;
	}

	/**
	 * Executes an AI tick
	 */
	protected void tick() {
		Desire desire = desireQueue.poll();
		handleDesire(desire);
	}

	/**
	 * Handles the given desire
	 * 
	 * @param desire
	 *            the desire
	 */
	protected abstract void handleDesire(Desire desire);
}
