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

import com.l2jserver.model.Model;

/**
 * This is an abstract ID for most model objects.
 * 
 * @param <T>
 *            the raw id type
 * @param <O>
 *            the model this {@link ID} provides
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AbstractModelID<T, O extends Model<? extends ID<T>>>
		extends ID<T> {
	/**
	 * @param id
	 *            the id
	 */
	protected AbstractModelID(T id) {
		super(id);
	}

	/**
	 * @return the {@link Model} object associated with this
	 *         {@link AbstractModelID}. <tt>null</tt> if {@link Model} does not
	 *         exists.
	 */
	public abstract O getObject();
}
