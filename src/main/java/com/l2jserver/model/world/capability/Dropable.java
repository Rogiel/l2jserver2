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
package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * Defines an {@link AbstractObject} that can be dropped on the ground.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Dropable extends Positionable {
	/**
	 * When an item is dropped its position will be set as <tt>position</tt>
	 * 
	 * @param position
	 *            the position
	 */
	void drop(Coordinate position);
}
