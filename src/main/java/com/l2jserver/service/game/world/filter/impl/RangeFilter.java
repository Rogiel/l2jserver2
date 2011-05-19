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
package com.l2jserver.service.game.world.filter.impl;

import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.service.game.world.filter.WorldObjectFilter;

/**
 * Filter objects that are in the <tt>range</tt> of <tt>coordinate</tt>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RangeFilter implements WorldObjectFilter<Positionable> {
	/**
	 * The coordinate point
	 */
	private final Positionable object;
	/**
	 * The desired maximum distance of the object
	 */
	private final int range;

	/**
	 * Creates a new instance
	 * 
	 * @param objcect
	 *            the positionable object as center point for range search
	 * @param range
	 *            the desired maximum distance of the object
	 */
	public RangeFilter(final Positionable object, final int range) {
		this.object = object;
		this.range = range;
	}

	@Override
	public boolean accept(Positionable other) {
		if (other == null)
			return false;
		return other.getPosition().getDistance(object.getPosition()) <= range;
	}
}
