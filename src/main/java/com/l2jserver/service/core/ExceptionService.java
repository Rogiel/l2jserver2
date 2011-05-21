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
package com.l2jserver.service.core;

import com.l2jserver.service.Service;

/**
 * Service used to manage unhandled exceptions
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ExceptionService extends Service {
	/**
	 * Handles an unhandled exception
	 * 
	 * @param t
	 *            the exception
	 */
	void thrown(Throwable t);

	/**
	 * Handles an unhandled exception and rethrows it again
	 * 
	 * @param <T>
	 *            the exception type
	 * @param t
	 *            the exception
	 * @throws T
	 *             same exception in <tt>t</tt>
	 */
	<T extends Throwable> void rethrown(T t) throws T;
}
