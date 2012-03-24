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
package com.l2jserver.service.game.world.filter;

import com.l2jserver.model.world.WorldObject;

/**
 * <tt>AND</tt> filter that accepts all values in which all other
 * <tt>filters</tt> return true.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <O>
 *            the item type
 */
public class AndFilter<O extends WorldObject> implements WorldObjectFilter<O> {
	/**
	 * The filters
	 */
	private WorldObjectFilter<O>[] filters;

	/**
	 * Creates a new instance
	 * 
	 * @param filters
	 *            filters to be used with <tt>AND</tt> operator
	 */
	@SafeVarargs
	public AndFilter(WorldObjectFilter<O>... filters) {
		this.filters = filters;
	}

	@Override
	public boolean accept(O object) {
		for (final WorldObjectFilter<O> filter : filters) {
			if (!filter.accept(object))
				return false;
		}
		return true;
	}
}
