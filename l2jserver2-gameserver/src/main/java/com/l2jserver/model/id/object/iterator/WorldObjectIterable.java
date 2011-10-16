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
package com.l2jserver.model.id.object.iterator;

import java.util.Iterator;

import com.l2jserver.model.world.WorldObject;

/**
 * This is a simple {@link Iterable} implementation that always return the same
 * {@link Iterator}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the return object type
 */
public class WorldObjectIterable<T extends WorldObject> implements Iterable<T> {
	/**
	 * The {@link Iterator}
	 */
	private final Iterator<T> iterator;

	/**
	 * Creates a new instance
	 * 
	 * @param iterator
	 *            the iterator
	 */
	public WorldObjectIterable(Iterator<T> iterator) {
		this.iterator = iterator;
	}

	@Override
	public Iterator<T> iterator() {
		return iterator;
	}
}
