package com.l2jserver.service.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

import com.l2jserver.service.AbstractService;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Simple cache that stores invocation results in a {@link WeakHashMap}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SimpleCacheService extends AbstractService implements CacheService {
	private final Map<MethodInvocation, Object> cache = CollectionFactory
			.newWeakMap(MethodInvocation.class, Object.class);

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
						Object result = cache.get(invocation);
						if (result == null) {
							result = method.invoke(instance, args);
							cache.put(invocation, result);
						}
						return result;
					}
				});
		return proxy;
	}

	private class MethodInvocation {
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
