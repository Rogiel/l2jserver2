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

import net.sf.ehcache.Cache;

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
 * 
 */
public interface CacheService extends Service {
	/**
	 * Decorates the <tt>instance</tt> with the cache. Note that
	 * <tt>interfaceType</tt> must be an interface!
	 * 
	 * @param <T>
	 *            the <tt>instance</tt> type
	 * @param interfaceType
	 *            the interface type
	 * @param instance
	 *            the instance implementing the interface
	 * @return the cache-decorated object
	 */
	<T extends Cacheable> T decorate(Class<T> interfaceType, T instance);

	/**
	 * Creates a new cache with default configurations. Eviction mode is LRU
	 * (Last Recently Used). If you wish more customization, you should manually
	 * create the cache and register it using {@link #register(Cache)}.
	 * 
	 * @param name
	 *            the cache name
	 * @size the maximum cache size
	 * @return the created cache
	 */
	Cache createCache(String name, int size);

	/**
	 * Creates a new cache with default configurations. The default cache size
	 * is 200.
	 * 
	 * @param name
	 *            the cache name
	 * @return the created cache
	 */
	Cache createCache(String name);

	/**
	 * Registers a new cache
	 * 
	 * @param cache
	 *            the cache
	 */
	void register(Cache cache);

	/**
	 * Unregisters an already registered cache
	 * 
	 * @param cache
	 *            the cache
	 */
	void unregister(Cache cache);
}
