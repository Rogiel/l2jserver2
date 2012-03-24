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
package com.l2jserver.service.game.ai;

/**
 * The AI Script interface. Scripts are not continuous executed. The service
 * will call multiple times {@link #run(double)}. Each time will represent a
 * tick and a bit of the job should be done in there.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface AIScript {
	/**
	 * Start the AIScript. Will register required listeners and start tasks.
	 */
	void start();

	/**
	 * Executes an AI operation.
	 * <p>
	 * <b>Please note</b> that <tt>time</tt> will almost never reach 1 second
	 * its usage is to determine how much time has been spent idle and to deal
	 * with it.
	 * <p>
	 * Instead of having a fixed "tick" time this method will be called at a
	 * random interval. This will make possible to use load balancers that can
	 * slow the "tick" once the server is in high load and reduce when the load
	 * is low.
	 * 
	 * @param time
	 *            the time elapsed since last call. In seconds
	 */
	void run(double time);

	/**
	 * Stop the AIScript. Will unregister the listeners that were registered in
	 * {@link #start()} and during life time.
	 */
	void stop();
}
