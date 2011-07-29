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
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.google.common.base.Preconditions;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * This {@link Cache} service implementation uses a {@link WeakReference} to
 * store values.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class WeakCacheService extends AbstractService implements CacheService {
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
		return new WeakCache<K, V>(name);
	}

	@Override
	public <K, V> Cache<K, V> createEternalCache(String name, int size) {
		return new EternalCache<K, V>(name);
	}

	@Override
	public <K, V> Cache<K, V> createCache(String name) {
		return new WeakCache<K, V>(name);
	}

	@Override
	public <K, V> void dispose(Cache<K, V> cache) {
		cache.clear();
	}

	@Override
	protected void doStop() throws ServiceStopException {
		dispose(interfaceCache);
		interfaceCache = null;
	}

	/**
	 * This class is a simple map implementation for cache usage.<br>
	 * <br>
	 * Values from the map will be removed after the first garbage collector run
	 * if there isn't any strong reference to the value object.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class WeakCache<K, V> extends AbstractReferenceCache<K, V>
			implements Cache<K, V> {
		/**
		 * This class is a {@link WeakReference} with additional responsibility
		 * of holding key object
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 */
		private class Entry extends WeakReference<V> {
			private K key;

			Entry(K key, V referent, ReferenceQueue<? super V> q) {
				super(referent, q);
				this.key = key;
			}

			K getKey() {
				return key;
			}
		}

		WeakCache(String cacheName) {
			super(cacheName);
		}

		@Override
		@SuppressWarnings("unchecked")
		protected synchronized void cleanQueue() {
			Entry en = null;
			while ((en = (Entry) refQueue.poll()) != null) {
				K key = en.getKey();
				if (log.isDebugEnabled())
					log.debug("{}: cleaned up for key: {}", cacheName, key);
				cacheMap.remove(key);
			}
		}

		@Override
		protected Reference<V> newReference(K key, V value,
				ReferenceQueue<V> vReferenceQueue) {
			return new Entry(key, value, vReferenceQueue);
		}
	}
}
