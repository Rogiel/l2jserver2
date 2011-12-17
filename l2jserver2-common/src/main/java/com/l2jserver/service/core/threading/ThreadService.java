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

import java.util.concurrent.TimeUnit;

import com.l2jserver.service.Service;

/**
 * This service is responsible for scheduling tasks and executing them in
 * parallel.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ThreadService extends Service {
	/**
	 * Executes an asynchronous tasks.
	 * <p>
	 * Tasks scheduled here will go to an default shared thread pool.
	 * 
	 * @param <T>
	 *            the task return type
	 * @param callable
	 *            the callable instance
	 * @return the {@link AsyncFuture} notified once the task has completed
	 */
	<T> AsyncFuture<T> async(Task<T> callable);

	/**
	 * Executes an asynchronous tasks at an scheduled time. <b>Please note that
	 * resources in scheduled thread pool are limited and tasks should be
	 * performed fast.</b>
	 * <p>
	 * Tasks scheduled here will go to an default shared thread pool.
	 * 
	 * @param <T>
	 *            the task return type
	 * @param callable
	 *            the callable instance
	 * @param delay
	 *            the initial delay to wait before the task is executed
	 * @param unit
	 *            the time unit of delay
	 * @return the {@link AsyncFuture} notified once the task has completed
	 */
	<T> AsyncFuture<T> async(long delay, TimeUnit unit, Task<T> callable);

	/**
	 * Executes an asynchronous tasks at an scheduled time. <b>Please note that
	 * resources in scheduled thread pool are limited and tasks should be
	 * performed fast.</b>
	 * <p>
	 * Tasks scheduled here will go to an default shared thread pool.
	 * 
	 * @param delay
	 *            the initial delay to wait before the task is executed
	 * @param unit
	 *            the time unit of delay
	 * @param repeat
	 *            the repeating interval for this task
	 * @param task
	 *            the task to be executed
	 * @return the {@link AsyncFuture} notified once the task has completed
	 */
	ScheduledAsyncFuture async(long delay, TimeUnit unit, long repeat,
			Runnable task);

	/**
	 * Creates a new thread pool.
	 * 
	 * @param name
	 *            the pool name
	 * @param maxThreads
	 *            the maximum amount of threads.
	 * @return the new thread pool
	 */
	ThreadPool createThreadPool(String name, int maxThreads);

	/**
	 * Disposes an given thread pool. After disposing the thread pool will no
	 * longer be usable.
	 * 
	 * @param pool
	 *            the thread pool to be disposed
	 */
	void dispose(ThreadPool pool);
}
