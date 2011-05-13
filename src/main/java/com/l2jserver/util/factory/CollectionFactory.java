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
