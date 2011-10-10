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

import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.game.world.filter.AndFilter;
import com.l2jserver.service.game.world.filter.NotFilter;
import com.l2jserver.util.geometry.Point3D;

/**
 * This filter will only accept {@link WorldObject} which were not in vision of
 * <tt>object</tt> when it was positioned at <tt>old</tt> and are visible now.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class KnownListUpdateFilter extends AndFilter<PositionableObject> {
	/**
	 * Constant declaring the range in which knownlist will be scanned
	 */
	public static final int KNOWNLIST_RANGE = 2000;

	/**
	 * Creates a new instance.
	 * <p>
	 * This filter will only accept {@link WorldObject} which were not in vision
	 * of <tt>object</tt> when it was positioned at <tt>old</tt> and are visible
	 * now.
	 * 
	 * @param object
	 *            the object in the center of the range
	 * @param old
	 *            the old position
	 */
	public KnownListUpdateFilter(PositionableObject object, Point3D old) {
		super(new KnownListFilter(object), new NotFilter<PositionableObject>(
				new RangePointFilter(old, KNOWNLIST_RANGE)));
	}
}
