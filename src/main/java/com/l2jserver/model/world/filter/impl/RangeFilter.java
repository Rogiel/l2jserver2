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
package com.l2jserver.model.world.filter.impl;

import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.model.world.filter.WorldObjectFilter;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * Filter objects that are in the <tt>range</tt> of <tt>coordinate</tt>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RangeFilter implements WorldObjectFilter<Positionable> {
	/**
	 * The coordinate point
	 */
	private final Coordinate coordinate;
	/**
	 * The desired maximum distance of the object
	 */
	private final int range;

	/**
	 * Creates a new instance
	 * 
	 * @param coordinate
	 *            the coordinate as base for range search
	 * @param range
	 *            the desired maximum distance of the object
	 */
	public RangeFilter(final Coordinate coordinate, final int range) {
		this.coordinate = coordinate;
		this.range = range;
	}

	/**
	 * Creates a new instance
	 * 
	 * @param positionable
	 *            the base object
	 * @param range
	 *            the desired maximum distance of the object
	 */
	public RangeFilter(final Positionable positionable, final int range) {
		this(positionable.getPosition(), range);
	}

	@Override
	public boolean accept(Positionable other) {
		if (other == null)
			return false;
		return other.getPosition().getDistance(coordinate) <= range;
	}
}
