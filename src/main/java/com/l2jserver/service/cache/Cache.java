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
package com.l2jserver.service.cache;

/**
 * This interface represents a Map structure for cache usage.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <K> the key type
 * @param <V> the value type
 */
public interface Cache<K, V> extends Iterable<V> {
	/**
	 * Adds a pair <key,value> to cache.<br>
	 * <br>
	 * 
	 * <font color='red'><b>NOTICE:</b> </font> if there is already a value with
	 * given id in the map, {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param key
	 *            the key name
	 * @param value
	 *            the cache content value
	 */
	void put(K key, V value);

	/**
	 * Returns cached value correlated to given key.
	 * 
	 * @param key
	 *            the key
	 * @return the cached value for this key
	 */
	V get(K key);

	/**
	 * Checks whether this map contains a value related to given key.
	 * 
	 * @param key
	 *            the key
	 * @return true if key has an value
	 */
	boolean contains(K key);

	/**
	 * Removes an entry from the map, that has given key.
	 * 
	 * @param key
	 *            the key
	 */
	void remove(K key);

	/**
	 * Clears this cache
	 */
	void clear();
}
