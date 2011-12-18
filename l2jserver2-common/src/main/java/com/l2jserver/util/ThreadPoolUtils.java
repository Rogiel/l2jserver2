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
package com.l2jserver.util;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.l2jserver.service.core.threading.Task;
import com.l2jserver.service.core.threading.ThreadPool;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ThreadPoolUtils {
	/**
	 * Wraps an {@link ThreadPool} into an {@link Executor}
	 * 
	 * @param pool
	 *            the pool the to be wrapped
	 * @return the wrapped {@link Executor}
	 */
	public static ExecutorService wrap(final ThreadPool pool) {
		return new ExecutorService() {
			@Override
			public void execute(final Runnable command) {
				pool.async(wrap(command));
			}

			@Override
			public void shutdown() {
				pool.dispose();
			}

			@Override
			public List<Runnable> shutdownNow() {
				pool.dispose();
				return null;
			}

			@Override
			public boolean isShutdown() {
				return pool.isDisposed();
			}

			@Override
			public boolean isTerminated() {
				return pool.isDisposed();
			}

			@Override
			public boolean awaitTermination(long timeout, TimeUnit unit)
					throws InterruptedException {
				throw new UnsupportedOperationException();
			}

			@Override
			public <T> Future<T> submit(Callable<T> task) {
				return pool.async(wrap(task));
			}

			@Override
			public <T> Future<T> submit(Runnable task, T result) {
				return pool.async(wrap(Executors.callable(task, result)));
			}

			@Override
			public Future<?> submit(Runnable task) {
				return pool.async(wrap(task));
			}

			@Override
			public <T> List<Future<T>> invokeAll(
					Collection<? extends Callable<T>> tasks)
					throws InterruptedException {
				throw new UnsupportedOperationException();
			}

			@Override
			public <T> List<Future<T>> invokeAll(
					Collection<? extends Callable<T>> tasks, long timeout,
					TimeUnit unit) throws InterruptedException {
				throw new UnsupportedOperationException();
			}

			@Override
			public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
					throws InterruptedException, ExecutionException {
				throw new UnsupportedOperationException();
			}

			@Override
			public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
					long timeout, TimeUnit unit) throws InterruptedException,
					ExecutionException, TimeoutException {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * Wraps an {@link Runnable} into an {@link Task}
	 * 
	 * @param command
	 *            the {@link Runnable} to be wrapped
	 * @return the wrapped {@link Runnable}
	 */
	public static Task<Runnable> wrap(final Runnable command) {
		return new Task<Runnable>() {
			@Override
			public Runnable call() throws Exception {
				command.run();
				return command;
			}
		};
	}

	/**
	 * Wraps an {@link Runnable} into an {@link Task}
	 * 
	 * @param <T>
	 *            the {@link Task} return type
	 * @param command
	 *            the {@link Runnable} to be wrapped
	 * @return the wrapped {@link Runnable}
	 */
	public static <T> Task<T> wrap(final Callable<T> command) {
		return new Task<T>() {
			@Override
			public T call() throws Exception {
				return command.call();
			}
		};
	}
}
