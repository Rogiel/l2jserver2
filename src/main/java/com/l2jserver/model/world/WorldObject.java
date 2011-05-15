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
package com.l2jserver.model.world;

import com.l2jserver.model.id.ObjectID;

/**
 * This is an base interface that every object in the Lineage II World need to
 * implement. The only methods defines by this class are getters and setters for
 * {@link ObjectID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WorldObject {
	/**
	 * Get the object's ID
	 * 
	 * @return the object id
	 */
	ObjectID<?> getID();

	/**
	 * Set this object ID. Note that the ID can only be set once. Truing to
	 * change an ID will thrown an {@link IllegalStateException}.
	 * 
	 * @param id
	 *            the id
	 * @throws IllegalStateException
	 *             if ID is already set
	 */
	void setID(ObjectID<?> id) throws IllegalStateException;
}
