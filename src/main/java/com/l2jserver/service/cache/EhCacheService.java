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
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.WeakHashMap;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * Simple cache that stores invocation results in a {@link WeakHashMap}.
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
	private Cache interfaceCache;

	@Override
	protected void doStart() throws ServiceStartException {
		manager = new CacheManager();
		interfaceCache = createCache("interface-cache");
	}

	@Override
	public <T extends Cacheable> T decorate(final Class<T> interfaceType,
			final T instance) {
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
						Object result = interfaceCache.get(invocation)
								.getObjectValue();
						if (result == null) {
							result = method.invoke(instance, args);
							interfaceCache.put(new Element(invocation, result));
						}
						return result;
					}
				});
		return proxy;
	}

	@Override
	public Cache createCache(String name, int size) {
		Cache cache = new Cache(new CacheConfiguration(name, size)
				.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
				.overflowToDisk(true).eternal(false).timeToLiveSeconds(60)
				.timeToIdleSeconds(30).diskPersistent(false)
				.diskExpiryThreadIntervalSeconds(0));
		register(cache);
		return cache;
	}

	@Override
	public Cache createCache(String name) {
		return createCache(name, 200);
	}

	@Override
	public void register(Cache cache) {
		manager.addCache(cache);
	}

	@Override
	public void unregister(Cache cache) {
		manager.removeCache(cache.getName());
	}

	@Override
	protected void doStop() throws ServiceStopException {
		manager.removalAll();
		manager.shutdown();
		interfaceCache = null;
	}

	private static class MethodInvocation {
		private final Method method;
		private final Object[] args;

		public MethodInvocation(Method method, Object[] args) {
			this.method = method;
			this.args = args;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(args);
			result = prime * result
					+ ((method == null) ? 0 : method.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MethodInvocation other = (MethodInvocation) obj;
			if (!Arrays.equals(args, other.args))
				return false;
			if (method == null) {
				if (other.method != null)
					return false;
			} else if (!method.equals(other.method))
				return false;
			return true;
		}
	}
}
