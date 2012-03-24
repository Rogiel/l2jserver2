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

import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.game.world.filter.AndFilter;
import com.l2jserver.service.game.world.filter.ExcludeFilter;

/**
 * This filter will only accept {@link WorldObject} which are in vision of
 * another object.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class KnownListFilter extends AndFilter<PositionableObject> {
	/**
	 * Constant declaring the range in which knownlist will be scanned
	 */
	public static final int KNOWNLIST_RANGE = 2000;

	/**
	 * @param object
	 *            the object to locate known objects
	 */
	public KnownListFilter(PositionableObject object) {
		super(new InstanceFilter<PositionableObject>(PositionableObject.class),
				new RangeFilter(object, KNOWNLIST_RANGE),
				new ExcludeFilter<PositionableObject>(object));
	}
}
