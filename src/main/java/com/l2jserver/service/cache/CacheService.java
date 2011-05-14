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
 * 
 */
public interface CacheService extends Service {
	/**
	 * Decores the <tt>instance</tt> with the cache
	 * 
	 * @param <T>
	 *            the <tt>instance</tt> type
	 * @param interfaceType
	 *            the interface type
	 * @param instance
	 *            the instance
	 * @return the cache-decorated object
	 */
	<T extends Cacheable> T decorate(Class<T> interfaceType, T instance);
}
