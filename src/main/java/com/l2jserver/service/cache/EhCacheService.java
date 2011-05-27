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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import com.google.common.base.Preconditions;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * Simple cache that stores invocation results in a EhCache {@link Cache}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class EhCacheService extends AbstractService implements CacheService {
	/**
	 * The cache manager
	 */
	private CacheManager manager;
	/**
	 * The interface cache
	 */
	private Cache<MethodInvocation, Object> interfaceCache;

	@Override
	protected void doStart() throws ServiceStartException {
		manager = new CacheManager(new Configuration().updateCheck(false)
				.diskStore(new DiskStoreConfiguration().path("data/cache")));
		interfaceCache = createCache("interface-cache");
	}

	@Override
	public <T extends Cacheable> T decorate(final Class<T> interfaceType,
			final T instance) {
		Preconditions.checkNotNull(interfaceType, "interfaceType");
		Preconditions.checkNotNull(instance, "instance");

		if (!interfaceType.isInterface())
			return null;
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

	// @Override
	// public Cache createCache(String name, int size) {
	// Preconditions.checkNotNull(name, "name");
	// Preconditions.checkArgument(size > 0, "size <= 0");
	//
	// Cache cache = new Cache(new CacheConfiguration(name, size)
	// .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
	// .overflowToDisk(true).eternal(false).timeToLiveSeconds(60)
	// .timeToIdleSeconds(30).diskPersistent(false)
	// .diskExpiryThreadIntervalSeconds(0));
	// register(cache);
	// return cache;
	// }
	//
	// @Override
	// public Cache createCache(String name) {
	// Preconditions.checkNotNull(name, "name");
	// return createCache(name, 200);
	// }
	//
	// @Override
	// public void register(Cache cache) {
	// Preconditions.checkNotNull(cache, "cache");
	// manager.addCache(cache);
	// }
	//
	// @Override
	// public void unregister(Cache cache) {
	// Preconditions.checkNotNull(cache, "cache");
	// manager.removeCache(cache.getName());
	// }

	@Override
	public <K, V> Cache<K, V> createCache(String name, int size) {
		Preconditions.checkNotNull(name, "name");
		Preconditions.checkArgument(size > 0, "size <= 0");

		net.sf.ehcache.Cache cache = new net.sf.ehcache.Cache(
				new CacheConfiguration(name, size)
						.memoryStoreEvictionPolicy(
								MemoryStoreEvictionPolicy.LRU)
						.overflowToDisk(true).eternal(false)
						.timeToLiveSeconds(60).timeToIdleSeconds(30)
						.diskPersistent(false)
						.diskExpiryThreadIntervalSeconds(0));
		manager.addCache(cache);
		return new EhCacheFacade<K, V>(cache);
	}

	@Override
	public <K, V> Cache<K, V> createEternalCache(String name, int size) {
		Preconditions.checkNotNull(name, "name");
		Preconditions.checkArgument(size > 0, "size <= 0");

		net.sf.ehcache.Cache cache = new net.sf.ehcache.Cache(
				new CacheConfiguration(name, size)
						.memoryStoreEvictionPolicy(
								MemoryStoreEvictionPolicy.LRU)
						.overflowToDisk(true).eternal(true)
						.diskExpiryThreadIntervalSeconds(0));
		manager.addCache(cache);
		return new EhCacheFacade<K, V>(cache);
	}

	@Override
	public <K, V> Cache<K, V> createCache(String name) {
		net.sf.ehcache.Cache cache = new net.sf.ehcache.Cache(
				new CacheConfiguration(name, 200)
						.memoryStoreEvictionPolicy(
								MemoryStoreEvictionPolicy.LRU)
						.overflowToDisk(true).eternal(true)
						.diskExpiryThreadIntervalSeconds(0));
		manager.addCache(cache);
		return new EhCacheFacade<K, V>(cache);
	}

	@Override
	public <K, V> void dispose(Cache<K, V> cache) {
		if (cache instanceof EhCacheFacade) {
			manager.removeCache(((EhCacheFacade<K, V>) cache).cache.getName());
		}
	}

	@Override
	protected void doStop() throws ServiceStopException {
		manager.removalAll();
		manager.shutdown();
		interfaceCache = null;
	}

	private class EhCacheFacade<K, V> implements Cache<K, V> {
		private final net.sf.ehcache.Cache cache;

		public EhCacheFacade(net.sf.ehcache.Cache cache) {
			this.cache = cache;
		}

		@Override
		public void put(K key, V value) {
			cache.put(new Element(key, value));
		}

		@Override
		@SuppressWarnings("unchecked")
		public V get(K key) {
			final Element element = cache.get(key);
			if (element == null)
				return null;
			return (V) element.getObjectValue();
		}

		@Override
		public boolean contains(K key) {
			return cache.get(key) != null;
		}

		@Override
		public void remove(K key) {
			cache.remove(key);
		}

		@Override
		public void clear() {
			cache.removeAll();
		}
	}
}
