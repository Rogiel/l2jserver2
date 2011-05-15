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
package com.l2jserver.util.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Factory class to create {@link Collection} instances.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CollectionFactory {
	/**
	 * Creates a new list of type <tt>T</tt>
	 * 
	 * @param <T>
	 *            the type
	 * @param type
	 *            the type
	 * @return the created list
	 */
	public static final <T> List<T> newList(Class<T> type) {
		return new ArrayList<T>();
	}

	/**
	 * Creates a new set of type <tt>T</tt>
	 * 
	 * @param <T>
	 *            the type
	 * @param type
	 *            the type
	 * @return the created set
	 */
	public static final <T> Set<T> newSet(Class<T> type) {
		return new HashSet<T>();
	}

	/**
	 * Creates a new concurrent queue of type <tt>T</tt>
	 * 
	 * @param <T>
	 *            the type
	 * @param type
	 *            the type
	 * @return the created queue
	 */
	public static final <T> Queue<T> newConcurrentQueue(Class<T> type) {
		return new ConcurrentLinkedQueue<T>();
	}

	/**
	 * Creates a new map.
	 * 
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 * @param key
	 *            the key type class
	 * @param value
	 *            the value type class
	 * @return the new map
	 */
	public static final <K, V> Map<K, V> newMap(Class<K> key, Class<V> value) {
		return new HashMap<K, V>();
	}

	/**
	 * Creates a new weak map.
	 * 
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 * @param key
	 *            the key type class
	 * @param value
	 *            the value type class
	 * @return the new map
	 */
	public static final <K, V> Map<K, V> newWeakMap(Class<K> key, Class<V> value) {
		return new WeakHashMap<K, V>();
	}
}
