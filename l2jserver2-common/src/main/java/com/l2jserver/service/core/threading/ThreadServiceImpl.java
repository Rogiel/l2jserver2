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
package com.l2jserver.service.core.threading;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.util.factory.CollectionFactory;

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

	/**
	 * The public shared thread pool
	 */
	private ThreadPool pool;

	/**
	 * The list of active thread pools
	 */
	private Map<String, ThreadPoolImpl> threadPools;

	@Override
	protected void doStart() throws ServiceStartException {
		threadPools = CollectionFactory.newMap();
		pool = createThreadPool("shared", 1);

		pool.async(50, TimeUnit.MILLISECONDS, 50, new Runnable() {
			@Override
			public void run() {
				for (final Entry<String, ThreadPoolImpl> entry : threadPools
						.entrySet()) {
					entry.getValue().notifyListeners();
				}
			}
		});
		// scheduler = Executors.newScheduledThreadPool(10);
		// async = Executors.newCachedThreadPool();
	}

	@Override
	public <T> AsyncFuture<T> async(Task<T> callable) {
		Preconditions.checkNotNull(callable, "callable");

		log.debug("Scheduling async task: {}", callable);
		return pool.async(callable);
	}

	@Override
	public <T> AsyncFuture<T> async(long delay, TimeUnit unit, Task<T> callable) {
		Preconditions.checkArgument(delay >= 0, "delay < 0");
		Preconditions.checkNotNull(unit, "unit");
		Preconditions.checkNotNull(callable, "callable");

		log.debug("Scheduling async task in {}ms: {}", unit.toMillis(delay),
				callable);
		return pool.async(delay, unit, callable);
	}

	@Override
	public ScheduledAsyncFuture async(long delay, TimeUnit unit, long repeat,
			Runnable task) {
		Preconditions.checkArgument(delay >= 0, "delay < 0");
		Preconditions.checkArgument(repeat >= 0, "repeat < 0");
		Preconditions.checkNotNull(unit, "unit");
		Preconditions.checkNotNull(task, "task");

		log.debug("Scheduling repeating async task in {}ms each {}ms: {}",
				new Object[] { unit.toMillis(delay), unit.toMillis(repeat),
						task });
		return pool.async(delay, unit, repeat, task);
	}

	@Override
	public ThreadPool createThreadPool(final String name, final int threads,
			final long threadTimeout, final TimeUnit threadTimeoutUnit,
			final ThreadPoolPriority priority) {
		log.debug(
				"Creating new {} priority ThreadPool {}; threads: {}, timeout:{}",
				new Object[] { priority, name, threads, threadTimeout });
		final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
				threads);
		if (threadTimeout >= 1) {
			executor.setKeepAliveTime(threadTimeout, threadTimeoutUnit);
			executor.allowCoreThreadTimeOut(true);
		}
		executor.setThreadFactory(new ThreadFactory() {
			private final AtomicInteger threadNumber = new AtomicInteger(1);

			@Override
			public Thread newThread(Runnable r) {
				final Thread thread = new Thread(r, name + "-"
						+ threadNumber.getAndIncrement());
				thread.setPriority(priority.threadPriority);
				return thread;
			}
		});

		final ThreadPoolImpl pool = new ThreadPoolImpl(name, executor);
		threadPools.put(name, pool);
		return pool;
	}

	@Override
	public ThreadPool createThreadPool(String name, int threads) {
		return createThreadPool(name, threads, -1, null,
				ThreadPoolPriority.NORMAL);
	}

	@Override
	public ThreadPool createThreadPool(String name, int threads,
			ThreadPoolPriority priority) {
		return createThreadPool(name, threads, -1, null, priority);
	}

	@Override
	public ThreadPool createThreadPool(String name, int threads,
			long threadTimeout, TimeUnit threadTimeoutUnit) {
		return createThreadPool(name, threads, threadTimeout,
				threadTimeoutUnit, ThreadPoolPriority.NORMAL);
	}

	@Override
	public void dispose(ThreadPool pool) {
		log.debug("Disposing ThreadPool {}", pool);
		if (pool instanceof ThreadPoolImpl) {
			((ThreadPoolImpl) pool).executor.shutdown();
			threadPools.remove(((ThreadPoolImpl) pool).name);
			return;
		}
		throw new UnsupportedOperationException(
				"The given ThreadPool is not supported by this service");
	}

	@Override
	protected void doStop() throws ServiceStopException {
		dispose(pool);
		pool = null;
		threadPools = null;
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
		 * List of all active listeners
		 */
		private List<AsyncListener<T>> listeners = CollectionFactory.newList();

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

		@Override
		public void addListener(AsyncListener<T> listener) {
			listeners.add(listener);
		}

		@Override
		public void removeListener(AsyncListener<T> listener) {
			listeners.remove(listener);
		}

		/**
		 * Notify all listeners that the task has been completed
		 */
		private void notifyListeners() {
			for (final AsyncListener<T> listener : listeners) {
				T object = null;
				try {
					object = this.get(0, TimeUnit.MILLISECONDS);
				} catch (InterruptedException | ExecutionException
						| TimeoutException e) {
				}
				listener.onComplete(this, object);
			}
		}
	}

	/**
	 * Future implementation for asynchronous tasks
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class ScheduledAsyncFutureImpl implements ScheduledAsyncFuture {
		/**
		 * The {@link ExecutorService} {@link ScheduledFuture}
		 */
		private final ScheduledFuture<?> future;

		/**
		 * @param future
		 *            the {@link ExecutorService} {@link ScheduledFuture}
		 */
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

	/**
	 * Thread pool implementation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class ThreadPoolImpl implements ThreadPool {
		/**
		 * This thread pool name
		 */
		private final String name;
		/**
		 * The backing executor
		 */
		private final ScheduledThreadPoolExecutor executor;
		/**
		 * The list of active and pending futures
		 */
		private final List<AsyncFutureImpl<?>> activeFutures = CollectionFactory
				.newList();

		/**
		 * @param name
		 *            the pool name
		 * @param executor
		 *            the backing {@link ScheduledThreadPoolExecutor}
		 */
		public ThreadPoolImpl(String name, ScheduledThreadPoolExecutor executor) {
			this.name = name;
			this.executor = executor;
		}

		@Override
		public <T> AsyncFuture<T> async(Task<T> callable) {
			log.debug("Task {} submited to {}", callable, name);
			return new AsyncFutureImpl<T>(executor.submit(callable));
		}

		@Override
		public <T> AsyncFuture<T> async(long delay, TimeUnit unit,
				Task<T> callable) {
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
				log.debug(
						"Task {} scheduled every {} {} to {}, starting in {}",
						new Object[] { task, repeat, unit, name, delay });
			return new ScheduledAsyncFutureImpl(executor.scheduleAtFixedRate(
					task, delay, repeat, unit));
		}

		@Override
		public void dispose() {
			ThreadServiceImpl.this.dispose(this);
		}

		/**
		 * Notify all future listeners when the task is complete.
		 */
		private void notifyListeners() {
			for (final AsyncFutureImpl<?> future : activeFutures) {
				if (future.isDone()) {
					future.notifyListeners();
					activeFutures.remove(future);
				}
			}
		}

		@Override
		public boolean isDisposed() {
			return executor.isShutdown();
		}
	}
}
