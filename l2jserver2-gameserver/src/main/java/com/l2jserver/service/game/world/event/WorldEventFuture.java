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
package com.l2jserver.service.game.world.event;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This is an {@link Future} for {@link WorldEvent}. This {@link Future} can be
 * used to receive notifications once an event has been dispatched to all
 * listeners.
 * 
 * @param <E>
 *            the event type in this future
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WorldEventFuture<E extends WorldEvent> extends Future<E> {
	/**
	 * Waits until the event is dispatched to all listeners
	 * 
	 * @throws ExecutionException
	 *             if any error occur while dispatching the event
	 */
	void await() throws ExecutionException;

	/**
	 * Waits until the event is dispatched to all listeners
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
	 * Waits until the event is dispatched to all listeners
	 * 
	 * @return true if execution ended with no error, false otherwise
	 */
	boolean awaitUninterruptibly();

	/**
	 * Waits until the event is dispatched to all listeners
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
