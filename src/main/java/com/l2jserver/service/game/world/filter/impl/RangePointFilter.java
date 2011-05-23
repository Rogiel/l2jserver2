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

import com.google.common.base.Preconditions;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.service.game.world.filter.WorldObjectFilter;
import com.l2jserver.util.dimensional.Point;

/**
 * Filter objects that are in the <tt>range</tt> of <tt>coordinate</tt>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RangePointFilter implements WorldObjectFilter<PositionableObject> {
	/**
	 * The coordinate point
	 */
	private final Point point;
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
	public RangePointFilter(final Point point, final int range) {
		Preconditions.checkNotNull(point, "point");
		Preconditions.checkState(range >= 0, "range < 0");
		this.point = point;
		this.range = range;
	}

	@Override
	public boolean accept(PositionableObject other) {
		if (other == null)
			return false;
		return other.getPosition().getDistance(point.getCoordinate()) <= range;
	}
}
