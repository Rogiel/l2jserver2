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

/**
 * This class implements basic functionality common for each desire
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see com.l2jserver.game.ai.desires.Desire
 * @see com.l2jserver.game.ai.desires.DesireQueue
 * @see com.l2jserver.game.ai.AI
 * @see com.l2jserver.game.ai.AI#handleDesire(Desire)
 */
public abstract class AbstractDesire implements Desire {

	/**
	 * Desire power. It's used to calculate what npc whants to do most of all.
	 */
	protected int desirePower;

	/**
	 * Creates new desire. By design any desire should have desire power. So
	 * constructor accepts basic amout.
	 * 
	 * @param desirePower
	 *            basic amount of desirePower
	 */
	protected AbstractDesire(int desirePower) {
		this.desirePower = desirePower;
	}

	/**
	 * Compares this desire with another, used by
	 * {@link com.l2jserver.game.ai.desires.DesireQueue} to keep track of desire
	 * priorities.
	 * 
	 * @param o
	 *            desire to compare with
	 * @return result of desire comparation
	 */
	@Override
	public int compareTo(Desire o) {
		return o.getDesirePower() - getDesirePower();
	}

	@Override
	public int getDesirePower() {
		return desirePower;
	}

	@Override
	public synchronized void increaseDesirePower(int desirePower) {
		this.desirePower = this.desirePower + desirePower;
	}

	@Override
	public synchronized void reduceDesirePower(int desirePower) {
		this.desirePower = this.desirePower - desirePower;
	}
}
