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
package com.l2jserver.model.id;

import com.l2jserver.model.id.factory.IDFactory;
import com.l2jserver.model.world.WorldObject;

/**
 * {@link ObjectID}s cannot be instantiated directly. This must be done through
 * an {@link IDFactory}. The {@link ObjectID} provides a facility
 * {@link #getObject() method} that allows easily fetch this object from
 * database without the need to directly use DAOs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the {@link WorldObject} type
 */
public abstract class ObjectID<T extends WorldObject> extends ID<Integer> {
	/**
	 * Creates a new instance
	 * 
	 * @param id
	 *            the raw id
	 */
	protected ObjectID(int id) {
		super(id);
	}

	/**
	 * Returns the {@link WorldObject} associated with this {@link ID}
	 * 
	 * @return the {@link WorldObject} if existent, <tt>null</tt> otherwise
	 */
	public abstract T getObject();
}
