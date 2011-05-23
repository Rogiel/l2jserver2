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
import com.l2jserver.util.dimensional.Point;

/**
 * This filter will only accept {@link WorldObject} which are in vision of
 * another object.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class KnownListUpdateFilter extends AndFilter<PositionableObject> {
	public static final int KNOWNLIST_RANGE = 2000;

	@SuppressWarnings("unchecked")
	public KnownListUpdateFilter(PositionableObject object, Point old) {
		super(new KnownListFilter(object), new NotFilter<PositionableObject>(
				new RangePointFilter(old, KNOWNLIST_RANGE)));
	}
}
