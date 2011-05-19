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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javolution.util.FastList;
import javolution.util.FastMap;
import javolution.util.FastSet;

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
	 * @return the created list
	 */
	public static final <T> List<T> newList() {
		return new FastList<T>();
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
	public static final <T> Set<T> newSet() {
		return new FastSet<T>();
	}

	/**
	 * Creates a new concurrent queue of type <tt>T</tt>
	 * 
	 * @param <T>
	 *            the type
	 * @return the created queue
	 */
	public static final <T> Queue<T> newConcurrentQueue() {
		return new ConcurrentLinkedQueue<T>();
	}

	/**
	 * Creates a new map.
	 * 
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 * @return the new map
	 */
	public static final <K, V> Map<K, V> newMap() {
		return new FastMap<K, V>();
	}

	/**
	 * Creates a new weak map.
	 * 
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 * @return the new map
	 */
	public static final <K, V> Map<K, V> newWeakMap() {
		return new WeakHashMap<K, V>();
	}
}
