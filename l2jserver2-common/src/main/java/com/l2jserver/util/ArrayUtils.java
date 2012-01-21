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
package com.l2jserver.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.l2jserver.util.factory.CollectionFactory;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class ArrayUtils {
	/**
	 * Copy an entire array except objects in <code>except</code> array.
	 * 
	 * @param <T>
	 *            the array component type
	 * 
	 * @param array
	 *            the source array
	 * @param except
	 *            the objects to not be copied
	 * @return the copied array
	 */
	@SafeVarargs
	@SuppressWarnings("unchecked")
	public final static <T> T[] copyArrayExcept(T[] array, T... except) {
		final List<T> values = CollectionFactory.newList();
		for (final T item : array) {
			if (Arrays.binarySearch(except, item, new Comparator<T>() {
				@Override
				public int compare(Object o1, Object o2) {
					return (o1 == o2 ? 1 : 0);
				}
			}) >= 0) {
				values.add(item);
			}
		}
		return (T[]) Arrays.copyOf(values.toArray(), values.size(),
				array.getClass());
	}

	/**
	 * Searches for the <code>expected</code> item to be in the
	 * <code>array</code>.
	 * 
	 * @param <T>
	 *            the array component type
	 * 
	 * @param array
	 *            the array to search in
	 * @param expected
	 *            the item to be looked in the array
	 * @return <code>true</code> if the item exists, <code>false</code>
	 *         otherwise
	 */
	public final static <T> boolean contains(T[] array, T expected) {
		return Arrays.binarySearch(array, expected) >= 0;
	}
}
