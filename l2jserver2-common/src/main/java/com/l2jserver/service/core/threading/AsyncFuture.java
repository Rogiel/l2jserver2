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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This future instance extends {@link Future} but also adds some additional
 * features, such as waiting for an given task to finish.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the {@link Future} return type
 */
public interface AsyncFuture<T> extends Future<T> {
	/**
	 * Waits until the task is executed
	 * 
	 * @throws ExecutionException
	 *             if the thread has been interrupted while waiting
	 */
	void await() throws ExecutionException;

	/**
	 * Waits until the task is executed
	 * 
	 * @param timeout
	 *            the timeout
	 * @param unit
	 *            the timeout unit
	 * 
	 * @throws InterruptedException
	 *             if the thread has been interrupted while waiting
	 * @throws TimeoutException
	 *             if timeout was exceeded
	 */
	void await(long timeout, TimeUnit unit) throws InterruptedException,
			TimeoutException;

	/**
	 * Waits until the task is executed
	 * 
	 * @return true if execution ended with no error, false otherwise
	 */
	boolean awaitUninterruptibly();

	/**
	 * Waits until the task is executed
	 * 
	 * @param timeout
	 *            the timeout
	 * @param unit
	 *            the timeout unit
	 * 
	 * @return true if execution ended with no error, false otherwise. Please
	 *         note that false will be returned if the timeout has expired too!
	 */
	boolean awaitUninterruptibly(long timeout, TimeUnit unit);
}
