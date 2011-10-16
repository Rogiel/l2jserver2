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
package com.l2jserver.util.exception;

/**
 * Base exception for Lineage 2
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class L2Exception extends Exception {
	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see Exception#Exception()
	 */
	public L2Exception() {
		super();
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public L2Exception(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public L2Exception(String message) {
		super(message);
	}

	/**
	 * @see Exception#Exception(Throwable)
	 */
	public L2Exception(Throwable cause) {
		super(cause);
	}

	// /**
	// * Each {@link L2Exception} has an {@link SystemMessage} attacked to it.
	// It
	// * is an <b><u>recommendation</u></b> of which message should be sent to
	// the
	// * client in case the exception is thrown.
	// *
	// * @return the recommended system message
	// */
	// public abstract SystemMessage getSystemMessage();
}
