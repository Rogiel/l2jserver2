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

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.util.factory.CollectionFactory;

/**
 * Cache class for an eternal cache. Entries in this cache instance won't ever
 * be automatically removed, even if the JVM is running out of memory.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
public class EternalCache<K, V> implements Cache<K, V> {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The cache name
	 */
	protected final String cacheName;

	/**
	 * Map storing references to cached objects
	 */
	protected final Map<K, V> cacheMap = CollectionFactory.newMap();

	/**
	 * @param cacheName
	 *            the cache name
	 */
	protected EternalCache(String cacheName) {
		this.cacheName = cacheName;
	}

	@Override
	public void put(K key, V value) {
		cacheMap.put(key, value);
		log.debug("{}: added for key: {}", cacheName, key);
	}

	@Override
	public V get(K key) {
		V obj = cacheMap.get(key);
		if (obj != null)
			log.debug("{}: obtained for key: {}", cacheName, key);
		return obj;
	}

	@Override
	public boolean contains(K key) {
		return cacheMap.containsKey(key);
	}

	@Override
	public void remove(K key) {
		cacheMap.remove(key);
		log.debug("{}: removed for key: {}", cacheName, key);
	}

	@Override
	public void clear() {
		cacheMap.clear();
		log.debug("{}: cleared", cacheName);
	}

	@Override
	public Iterator<V> iterator() {
		return cacheMap.values().iterator();
	}
}
