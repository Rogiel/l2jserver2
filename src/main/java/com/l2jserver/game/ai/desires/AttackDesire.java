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
package com.l2jserver.game.ai.desires;

import com.l2jserver.game.ai.AI;
import com.l2jserver.model.world.Actor;

/**
 * This class indicates that character wants to attack somebody
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public final class AttackDesire extends AbstractDesire {
	/**
	 * Target of this desire
	 */
	protected final Actor target;

	/**
	 * Creates new attack desire, target can't be changed
	 * 
	 * @param target
	 *            whom to attack
	 * @param desirePower
	 *            initial attack power
	 */
	protected AttackDesire(Actor target, int desirePower) {
		super(desirePower);
		this.target = target;
	}

	@Override
	public void handleDesire(AI<?> ai) {
		// TODO: Implement
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AttackDesire))
			return false;

		AttackDesire that = (AttackDesire) o;

		return target.equals(that.target);
	}

	@Override
	public int hashCode() {
		return target.hashCode();
	}

	/**
	 * Returns target of this desire
	 * 
	 * @return target of this desire
	 */
	public Actor getTarget() {
		return target;
	}
}
