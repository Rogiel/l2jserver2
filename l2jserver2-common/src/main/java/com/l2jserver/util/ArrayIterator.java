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
package com.l2jserver.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This {@link Iterator} takes an array as input and iterate over it.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the array type
 */
public class ArrayIterator<T> implements Iterator<T> {
	/**
	 * The objects
	 */
	private final T[] objects;
	/**
	 * the iterator index
	 */
	private int i = 0;

	/**
	 * Creates a new iterator instance
	 * 
	 * @param objects
	 *            the objects
	 */
	public ArrayIterator(T... objects) {
		this.objects = objects;
	}

	@Override
	public boolean hasNext() {
		return i < objects.length;
	}

	@Override
	public T next() {
		try {
			return objects[i++];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void remove() {
	}
}
