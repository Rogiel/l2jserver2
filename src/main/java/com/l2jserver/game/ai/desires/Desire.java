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
 * This interface represents basic desire functions.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see com.l2jserver.game.ai.AI
 * @see com.l2jserver.game.ai.AI#handleDesire(Desire)
 * @see com.l2jserver.game.ai.desires.AbstractDesire
 */
public interface Desire extends Comparable<Desire> {
	/**
	 * Returns hashcode for this object, must be overrided by child
	 * 
	 * @return hashcode for this object
	 */
	int hashCode();

	/**
	 * Compares this Desire with another object, must overriden by child
	 * 
	 * @param obj
	 *            another object to compare with
	 * @return result of object comparation
	 */
	boolean equals(Object obj);

	/**
	 * Returns desire power of this object
	 * 
	 * @return desire power of the object
	 */
	int getDesirePower();

	/**
	 * Adds desire power to this desire, this call is synchronized.<br>
	 * <br>
	 * <b>WARNING!!! Changing desire power after adding it to queue will not
	 * affect it's position, you have to call
	 * {@link com.l2jserver.game.ai.desires.DesireQueue#addDesire(Desire)}
	 * passing this instance as argument</b>
	 * 
	 * @param desirePower
	 *            amount of desirePower to add
	 * @see DesireQueue#addDesire(Desire)
	 */
	void increaseDesirePower(int desirePower);

	/**
	 * Reduces desire power by give amount.<br>
	 * <br>
	 * <b>WARNING!!! Changing desire power after adding it to queue will not
	 * affect it's position, you have to call
	 * {@link com.l2jserver.game.ai.desires.DesireQueue#addDesire(Desire)}
	 * passing this instance as argument</b>
	 * 
	 * @param desirePower
	 *            amount of desirePower to substract
	 * @see DesireQueue#addDesire(Desire)
	 */
	void reduceDesirePower(int desirePower);
}
