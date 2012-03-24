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
package com.l2jserver.model.id.object.provider;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.provider.IDProvider;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the ID type provided
 */
public interface ObjectIDProvider<T extends ObjectID<?>> extends
		IDProvider<Integer, T> {
	/**
	 * Generates a new ID
	 * 
	 * @return the new ID
	 */
	T createID();

	/**
	 * Destroy this ID. Releases this value to be used once again.
	 * 
	 * @param id
	 *            the id to be destroyed.
	 */
	void destroy(T id);
}
