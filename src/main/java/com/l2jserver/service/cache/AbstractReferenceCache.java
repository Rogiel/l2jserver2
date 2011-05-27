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

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.service.cache.SoftCacheService.SoftCache;
import com.l2jserver.util.factory.CollectionFactory;
import com.sun.beans.WeakCache;

/**
 * Base class for {@link WeakCache} and {@link SoftCache}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
abstract class AbstractReferenceCache<K, V> implements Cache<K, V> {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The cache name
	 */
	protected final String cacheName;

	/**
	 * Map storing references to cached objects
	 */
	protected final Map<K, Reference<V>> cacheMap = CollectionFactory.newMap();
	/**
	 * The reference queue
	 */
	protected final ReferenceQueue<V> refQueue = CollectionFactory
			.newReferenceQueue();

	/**
	 * @param cacheName
	 *            the cache name
	 */
	protected AbstractReferenceCache(String cacheName) {
		this.cacheName = cacheName;
	}

	@Override
	public void put(K key, V value) {
		cleanQueue();

		Reference<V> entry = newReference(key, value, refQueue);
		cacheMap.put(key, entry);

		log.debug("{}: added for key: {}", cacheName, key);
	}

	@Override
	public V get(K key) {
		cleanQueue();

		Reference<V> reference = cacheMap.get(key);
		if (reference == null)
			return null;

		V res = reference.get();
		if (res != null)
			log.debug("{}: obtained for key: {}", cacheName, key);

		return res;
	}

	@Override
	public boolean contains(K key) {
		cleanQueue();
		return cacheMap.containsKey(key);
	}

	protected abstract void cleanQueue();

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

	protected abstract Reference<V> newReference(K key, V value,
			ReferenceQueue<V> queue);
}
