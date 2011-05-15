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
package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that other objects can join to.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Joinable<T> extends ObjectCapability, Iterable<T> {
	/**
	 * Join an <tt>member</tt> to this object.
	 * 
	 * @param member
	 *            the entering member
	 */
	void join(T member);

	/**
	 * Removes the joined <tt>member</tt> from this object
	 * 
	 * @param member
	 *            the exiting member
	 */
	void leave(T member);
}
