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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
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

	/**
	 * The scheduler Thread pool
	 */
	private ScheduledExecutorService scheduler;
	/**
	 * The asynchronous Thread pool
	 */
	private ExecutorService async;

	@Override
	protected void doStart() throws ServiceStartException {
		scheduler = Executors.newScheduledThreadPool(10);
		async = Executors.newCachedThreadPool();
	}

	@Override
	public <T> AsyncFuture<T> async(Callable<T> callable) {
		Preconditions.checkNotNull(callable, "callable");
		return new AsyncFutureImpl<T>(async.submit(callable));
	}

	@Override
	public <T> AsyncFuture<T> async(long delay, TimeUnit unit,
			Callable<T> callable) {
		Preconditions.checkArgument(delay >= 0, "delay < 0");
		Preconditions.checkNotNull(unit, "unit");
		Preconditions.checkNotNull(callable, "callable");
		return new AsyncFutureImpl<T>(scheduler.schedule(callable, delay, unit));
	}

	@Override
	protected void doStop() throws ServiceStopException {
		scheduler.shutdown();
		async.shutdown();
		try {
			scheduler.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			log.warn("Scheduler thread did not stop in 60 seconds", e);
		}
		try {
			async.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			log.warn("Asynchronous thread did not stop in 60 seconds", e);
		}
		scheduler = null;
		async = null;
	}

	/**
	 * Simple delegated implementation for {@link AsyncFuture}
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * @param <T>
	 *            the return type
	 */
	public static class AsyncFutureImpl<T> implements AsyncFuture<T> {
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
		public void await() throws InterruptedException {
			try {
				this.get();
			} catch (ExecutionException e) {
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
}
