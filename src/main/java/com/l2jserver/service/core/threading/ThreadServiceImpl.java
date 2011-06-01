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
package com.l2jserver.service.core.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * The default implementation for {@link ThreadService}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ThreadServiceImpl extends AbstractService implements ThreadService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ThreadPool pool;

	@Override
	protected void doStart() throws ServiceStartException {
		pool = createThreadPool("shared", 20);
		// scheduler = Executors.newScheduledThreadPool(10);
		// async = Executors.newCachedThreadPool();
	}

	@Override
	public <T> AsyncFuture<T> async(Callable<T> callable) {
		Preconditions.checkNotNull(callable, "callable");
		return pool.async(callable);
	}

	@Override
	public <T> AsyncFuture<T> async(long delay, TimeUnit unit,
			Callable<T> callable) {
		Preconditions.checkArgument(delay >= 0, "delay < 0");
		Preconditions.checkNotNull(unit, "unit");
		Preconditions.checkNotNull(callable, "callable");
		return pool.async(delay, unit, callable);
	}

	@Override
	public ScheduledAsyncFuture async(long delay, TimeUnit unit, long repeat,
			Runnable task) {
		Preconditions.checkArgument(delay >= 0, "delay < 0");
		Preconditions.checkArgument(repeat >= 0, "repeat < 0");
		Preconditions.checkNotNull(unit, "unit");
		Preconditions.checkNotNull(task, "task");
		return pool.async(delay, unit, repeat, task);
	}

	@Override
	public ThreadPool createThreadPool(String name, int maxThreads) {
		return new ThreadPoolImpl(name,
				Executors.newScheduledThreadPool(maxThreads));
	}

	@Override
	public void dispose(ThreadPool pool) {
		if (pool instanceof ThreadPoolImpl)
			((ThreadPoolImpl) pool).executor.shutdown();
		throw new UnsupportedOperationException(
				"The given ThreadPool is not supported by this service");
	}

	@Override
	protected void doStop() throws ServiceStopException {
		dispose(pool);
		pool = null;
	}

	/**
	 * Simple delegated implementation for {@link AsyncFuture}
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * @param <T>
	 *            the return type
	 */
	private class AsyncFutureImpl<T> implements AsyncFuture<T> {
		/**
		 * The future that is delegated in this implementation
		 */
		private final Future<T> future;

		/**
		 * Creates a new instance
		 * 
		 * @param future
		 *            the future
		 */
		private AsyncFutureImpl(Future<T> future) {
			this.future = future;
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return future.cancel(mayInterruptIfRunning);
		}

		@Override
		public boolean isCancelled() {
			return future.isCancelled();
		}

		@Override
		public boolean isDone() {
			return future.isDone();
		}

		@Override
		public T get() throws InterruptedException, ExecutionException {
			return future.get();
		}

		@Override
		public T get(long timeout, TimeUnit unit) throws InterruptedException,
				ExecutionException, TimeoutException {
			return future.get(timeout, unit);
		}

		@Override
		public void await() throws ExecutionException {
			try {
				this.get();
			} catch (InterruptedException e) {
			}
		}

		@Override
		public void await(long timeout, TimeUnit unit)
				throws InterruptedException, TimeoutException {
			try {
				this.get(timeout, unit);
			} catch (ExecutionException e) {
			}
		}

		@Override
		public boolean awaitUninterruptibly() {
			try {
				this.get();
				return true;
			} catch (InterruptedException e) {
				return false;
			} catch (ExecutionException e) {
				return false;
			}
		}

		@Override
		public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
			try {
				this.get(timeout, unit);
				return true;
			} catch (InterruptedException e) {
				return false;
			} catch (ExecutionException e) {
				return false;
			} catch (TimeoutException e) {
				return false;
			}
		}
	}

	private class ScheduledAsyncFutureImpl implements ScheduledAsyncFuture {
		private final ScheduledFuture<?> future;

		public ScheduledAsyncFutureImpl(ScheduledFuture<?> future) {
			this.future = future;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return future.getDelay(unit);
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return future.cancel(mayInterruptIfRunning);
		}

		@Override
		public int compareTo(Delayed o) {
			return future.compareTo(o);
		}

		@Override
		public boolean isCancelled() {
			return future.isCancelled();
		}

		@Override
		public boolean isDone() {
			return future.isDone();
		}

		@Override
		public Object get() throws InterruptedException, ExecutionException {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object get(long timeout, TimeUnit unit)
				throws InterruptedException, ExecutionException,
				TimeoutException {
			throw new UnsupportedOperationException();
		}

	}

	private class ThreadPoolImpl implements ThreadPool {
		/**
		 * This thread pool name
		 */
		private final String name;
		/**
		 * The backing executor
		 */
		private final ScheduledExecutorService executor;

		public ThreadPoolImpl(String name, ScheduledExecutorService executor) {
			this.name = name;
			this.executor = executor;
		}

		@Override
		public <T> AsyncFuture<T> async(Callable<T> callable) {
			log.debug("Task {} submited to {}", callable, name);
			return new AsyncFutureImpl<T>(executor.submit(callable));
		}

		@Override
		public <T> AsyncFuture<T> async(long delay, TimeUnit unit,
				Callable<T> callable) {
			if (log.isDebugEnabled())
				log.debug("Task {} scheduled in {} {} to {}", new Object[] {
						callable, delay, unit, name });
			return new AsyncFutureImpl<T>(executor.schedule(callable, delay,
					unit));
		}

		@Override
		public ScheduledAsyncFuture async(long delay, TimeUnit unit,
				long repeat, Runnable task) {
			if (log.isDebugEnabled())
				log.debug("Task {} scheduled every {} {} to {}", new Object[] {
						task, repeat, unit, name });
			return new ScheduledAsyncFutureImpl(executor.scheduleAtFixedRate(
					task, delay, repeat, unit));
		}
	}
}
