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

/**
 * The priority of the thread pool
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum ThreadPoolPriority {
	/**
	 * High priority.
	 * <p>
	 * Processor will block {@link ThreadPoolPriority#NORMAL} and
	 * {@link ThreadPoolPriority#LOW} priority threads in order to finish tasks
	 * in pools on this priority.
	 */
	HIGH(Thread.MAX_PRIORITY),
	/**
	 * Normal priority.
	 * <p>
	 * Processor will block {@link ThreadPoolPriority#LOW} priority threads in
	 * order to finish tasks in pools on this priority.
	 */
	NORMAL(Thread.NORM_PRIORITY),
	/**
	 * Low priority.
	 * <p>
	 * Processor will give very low priority for tasks in this level.
	 */
	LOW(Thread.MIN_PRIORITY);

	/**
	 * The priority to be used on {@link Thread}
	 */
	public final int threadPriority;

	/**
	 * @param threadPriority
	 *            the {@link Thread} priority {@link Integer} index
	 */
	ThreadPoolPriority(int threadPriority) {
		this.threadPriority = threadPriority;
	}
}
