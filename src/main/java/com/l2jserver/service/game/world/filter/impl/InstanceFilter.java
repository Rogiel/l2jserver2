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

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.game.world.filter.WorldObjectFilter;

/**
 * Filter object based on their types
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the instance type
 */
public class InstanceFilter<T extends WorldObject> implements
		WorldObjectFilter<T> {
	/**
	 * The object's type
	 */
	private final Class<?> type;

	/**
	 * Creates a new instance
	 * 
	 * @param instance
	 *            the instance type
	 */
	public InstanceFilter(Class<?> instance) {
		this.type = instance;
	}

	@Override
	public boolean accept(T other) {
		if (other == null)
			return false;
		return type.isInstance(other);
	}
}
