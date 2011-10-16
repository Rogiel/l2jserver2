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
package com.l2jserver.model.id.provider;

import com.l2jserver.model.id.ID;

/**
 * ID objects should never be directly instantiated and an provider
 * implementation must be used to create and generate them.
 * <p>
 * 
 * The ID provider is used to create instances of IDs. It will automatically
 * make sure the ID is free before allocating it.
 * <p>
 * The provider will also make sure only a single instance for each raw ID
 * exits, that is for any given ID instance for raw value <b>1</b> only a single
 * object will be created and following calls will always return the same
 * object.
 * 
 * @param <I>
 *            the raw id type
 * @param <T>
 *            the {@link ID} implementation
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface IDProvider<I, T extends ID<I>> {
	/**
	 * Creates the ID object for an <b>EXISTING</b> ID.
	 * 
	 * @param id
	 *            the raw id value
	 * @return the resolved {@link ID} object
	 */
	T resolveID(I id);
}
