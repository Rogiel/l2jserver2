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
package com.l2jserver.service;

/**
 * Thrown when an service failed to restart. It's <tt>cause</tt> can be an
 * {@link ServiceStartException} or {@link ServiceStopException}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ServiceRestartException extends ServiceException {
	/**
	 * The Java Serialization API serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of this exception
	 */
	public ServiceRestartException() {
		super();
	}

	/**
	 * Creates a new instance of this exception
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the root cause
	 */
	public ServiceRestartException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates a new instance of this exception
	 * 
	 * @param message
	 *            the message
	 */
	public ServiceRestartException(String message) {
		super(message);
	}

	/**
	 * Creates a new instance of this exception
	 * 
	 * @param cause
	 *            the root cause
	 */
	public ServiceRestartException(Throwable cause) {
		super(cause);
	}
}
