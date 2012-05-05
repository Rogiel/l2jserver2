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
package com.l2jserver.service.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * Simple cache that stores invocation results in a Google Guava
 * {@link com.google.common.cache.Cache Cache}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class GuavaCacheService extends AbstractService implements CacheService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The interface cache
	 */
	private Cache<MethodInvocation, Object> interfaceCache;

	@Override
	protected void doStart() throws ServiceStartException {
		interfaceCache = createCache("interface-cache");
	}

	@Override
	public <T extends Cacheable> T decorate(final Class<T> interfaceType,
			final T instance) {
		Preconditions.checkNotNull(interfaceType, "interfaceType");
		Preconditions.checkNotNull(instance, "instance");
		Preconditions.checkArgument(interfaceType.isInterface(),
				"interfaceType is not an interface");

		log.debug("Decorating {} with cache", interfaceType);

		@SuppressWarnings("unchecked")
		final T proxy = (T) Proxy.newProxyInstance(this.getClass()
				.getClassLoader(), new Class[] { interfaceType },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						if (method.isAnnotationPresent(IgnoreCaching.class))
							return method.invoke(instance, args);
						final MethodInvocation invocation = new MethodInvocation(
								method, args);
						Object result = interfaceCache.get(invocation);
						if (result == null)
							return doInvoke(invocation, proxy, method, args);
						return result;
					}

					private Object doInvoke(MethodInvocation invocation,
							Object proxy, Method method, Object[] args)
							throws IllegalArgumentException,
							IllegalAccessException, InvocationTargetException {
						Object result = method.invoke(instance, args);
						interfaceCache.put(invocation, result);
						return result;
					}
				});
		return proxy;
	}

	@Override
	public <K, V> Cache<K, V> createCache(String name, int size) {
		Preconditions.checkNotNull(name, "name");
		Preconditions.checkArgument(size > 0, "size <= 0");

		log.debug("Creating cache {} with minimum size of {}", name, size);

		com.google.common.cache.LoadingCache<K, V> cache = CacheBuilder
				.newBuilder().maximumSize(size).build(new CacheLoader<K, V>() {
					@Override
					public V load(K key) throws Exception {
						throw new Exception("Key is not bounded to any value");
					}
				});
		return new GuavaCacheFacade<K, V>(cache);
	}

	@Override
	public <K, V> Cache<K, V> createEternalCache(String name, int size) {
		Preconditions.checkNotNull(name, "name");
		Preconditions.checkArgument(size > 0, "size <= 0");

		log.debug("Creating eternal cache {} with minimum size of {}", name,
				size);

		com.google.common.cache.LoadingCache<K, V> cache = CacheBuilder
				.newBuilder().maximumSize(Integer.MAX_VALUE)
				.build(new CacheLoader<K, V>() {
					@Override
					public V load(K key) throws Exception {
						throw new Exception("Key is not bounded to any value");
					}
				});
		return new GuavaCacheFacade<K, V>(cache);
	}

	@Override
	public <K, V> Cache<K, V> createCache(String name) {
		return createCache(name, 200);
	}

	@Override
	public <K, V> void dispose(Cache<K, V> cache) {
		if (cache instanceof GuavaCacheFacade) {
			log.debug("Disposing cache {}", cache);
			((GuavaCacheFacade<K, V>) cache).cache.invalidateAll();
		} else {
			log.warn("Trying to dispose {} cache when it is not EhCacheFacade type");
		}
	}

	@Override
	protected void doStop() throws ServiceStopException {
		interfaceCache = null;
	}

	/**
	 * {@link net.sf.ehcache.Cache EhCache} facade
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 */
	private class GuavaCacheFacade<K, V> implements Cache<K, V> {
		/**
		 * The backing cache
		 */
		private final com.google.common.cache.LoadingCache<K, V> cache;

		/**
		 * @param cache
		 *            the backing cache
		 */
		public GuavaCacheFacade(com.google.common.cache.LoadingCache<K, V> cache) {
			this.cache = cache;
		}

		@Override
		public void put(K key, V value) {
			cache.put(key, value);
		}

		@Override
		public V get(K key) {
			return cache.getIfPresent(key);
		}

		@Override
		public boolean contains(K key) {
			return cache.getIfPresent(key) != null;
		}

		@Override
		public void remove(K key) {
			cache.invalidate(key);
		}

		@Override
		public void clear() {
			cache.invalidateAll();
		}

		@Override
		public Iterator<V> iterator() {
			return null;
		}
	}
}
