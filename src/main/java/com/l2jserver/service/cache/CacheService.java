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

import com.l2jserver.service.Service;

/**
 * This is an transparent Cache system. It proxies an interface implementing
 * {@link Cacheable}. Once the first call is done through the proxy, the result
 * is cached in the underlying cache engine. When the second and sucedind calls
 * are made the cache is looked up, if a match (method and arguments pair) is
 * found, this result is returned.
 * <p>
 * If you do not desire to cache an method, annotate it with
 * {@link IgnoreCaching}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface CacheService extends Service {
	/**
	 * Decorates the <tt>instance</tt> with the cache. Note that
	 * <tt>interfaceType</tt> must be an interface!
	 * 
	 * @param <T>
	 *            the <tt>instance</tt> type
	 * @param interfaceType
	 *            the interface type. Remember, this must be an interface!
	 * @param instance
	 *            the instance implementing the interface
	 * @return the cache-decorated object
	 */
	<T extends Cacheable> T decorate(Class<T> interfaceType, T instance);

	/**
	 * Creates a new cache with default configurations. Eviction mode is LRU
	 * (Last Recently Used). The size is only a guarantee that you can store
	 * <b>at least</b> <tt>n</tt> items.
	 * 
	 * @param <K>
	 *            the cache key type
	 * @param <V>
	 *            the cache value type
	 * @param name
	 *            the cache name
	 * @param size the maximum cache size
	 * @size the maximum cache size
	 * @return the created cache
	 */
	<K, V> Cache<K, V> createCache(String name, int size);

	/**
	 * Creates a new eternal cache with default configurations. An eternal cache
	 * is guaranteed to never automatically expire items. The size is only a
	 * guarantee that you can store <b>at least</b> <tt>n</tt> items.
	 * 
	 * @param <K>
	 *            the cache key type
	 * @param <V>
	 *            the cache value type
	 * @param name
	 *            the cache name
	 * @param size the maximum cache size
	 * @size the maximum cache size
	 * @return the created cache
	 */
	<K, V> Cache<K, V> createEternalCache(String name, int size);

	/**
	 * Creates a new cache with default configurations. The default cache size
	 * is 200. The size is only a guarantee that you can store <b>at least</b>
	 * 200 items.
	 * 
	 * @param <K>
	 *            the cache key type
	 * @param <V>
	 *            the cache value type
	 * @param name
	 *            the cache name
	 * @return the created cache
	 */
	<K, V> Cache<K, V> createCache(String name);

	/**
	 * Disposes the cache. Once the cache is disposed it cannot be used anymore.
	 * 
	 * @param <K>
	 *            the cache key type
	 * @param <V>
	 *            the cache value type
	 * @param cache
	 *            the cache
	 */
	<K, V> void dispose(Cache<K, V> cache);
}
