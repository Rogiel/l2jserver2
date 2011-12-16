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
 * Utility class for common filter types
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public final class WorldFilters {
	/**
	 * Performs an <tt>AND</tt> operation
	 * 
	 * @param <O>
	 *            the object type
	 * @param filters
	 *            the filters
	 * @return the {@link AndFilter}
	 */
	@SafeVarargs
	public static final <O extends WorldObject> WorldObjectFilter<O> and(
			WorldObjectFilter<O>... filters) {
		return new AndFilter<O>(filters);
	}

	/**
	 * Performs an <tt>OR</tt> operation
	 * 
	 * @param <O>
	 *            the object type
	 * @param filters
	 *            the filters
	 * @return the {@link OrFilter}
	 */
	@SafeVarargs
	public static final <O extends WorldObject> WorldObjectFilter<O> or(
			WorldObjectFilter<O>... filters) {
		return new OrFilter<O>(filters);
	}

	/**
	 * Performs an <tt>NOT</tt> operation
	 * 
	 * @param <O>
	 *            the object type
	 * @param filter
	 *            the filter
	 * @return the {@link NotFilter}
	 */
	public static final <O extends WorldObject> WorldObjectFilter<O> not(
			WorldObjectFilter<O> filter) {
		return new NotFilter<O>(filter);
	}
}
