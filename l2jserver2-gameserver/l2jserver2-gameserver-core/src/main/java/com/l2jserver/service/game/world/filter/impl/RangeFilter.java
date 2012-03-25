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
package com.l2jserver.service.game.world.filter.impl;

import org.apache.commons.math.util.FastMath;

import com.google.common.base.Preconditions;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.service.game.world.filter.WorldObjectFilter;

/**
 * Filter objects that are in the <tt>range</tt> of <tt>coordinate</tt>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RangeFilter implements WorldObjectFilter<PositionableObject> {
	/**
	 * The coordinate point
	 */
	private final PositionableObject object;
	/**
	 * The desired maximum distance of the object
	 */
	private final double range;

	/**
	 * Creates a new instance
	 * 
	 * @param object
	 *            the positionable object as center point for range search
	 * @param range
	 *            the desired maximum distance of the object
	 */
	public RangeFilter(final PositionableObject object, final int range) {
		Preconditions.checkNotNull(object, "object");
		Preconditions.checkState(range >= 0, "negative range");
		this.object = object;
		this.range = range;
	}

	@Override
	public boolean accept(PositionableObject other) {
		if (other == null)
			return false;
		if (other.getPoint() == null)
			return false;

		final double dx = FastMath.abs(object.getPoint().getX()
				- other.getPoint().getX());
		final double dy = FastMath.abs(object.getPoint().getY()
				- other.getPoint().getY());
		final double dz = FastMath.abs(object.getPoint().getZ()
				- other.getPoint().getZ());

		if (dx > range)
			return false;
		if (dy > range)
			return false;
		if (dz > range)
			return false;

		return true;
	}
}
