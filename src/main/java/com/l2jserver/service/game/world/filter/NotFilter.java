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
package com.l2jserver.service.game.world.filter;

import com.l2jserver.model.world.WorldObject;

/**
 * And filter that accepts all values in which the other <tt>filter</tt> return
 * false.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <O>
 *            the item type
 */
public class NotFilter<O extends WorldObject> implements WorldObjectFilter<O> {
	/**
	 * The filter
	 */
	private WorldObjectFilter<O> filter;

	/**
	 * Creates a new instance
	 * 
	 * @param filter
	 *            the filter
	 */
	public NotFilter(WorldObjectFilter<O> filter) {
		this.filter = filter;
	}

	@Override
	public boolean accept(O object) {
		return !filter.accept(object);
	}
}
