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
package com.l2jserver.service.database;

import com.l2jserver.model.Model;
import com.l2jserver.model.id.ID;
import com.l2jserver.service.cache.IgnoreCaching;

/**
 * The DAO interface
 * <p>
 * TODO make DAO an {@link Iterable}. So if we want to select all objects we can
 * do like this: <code><pre>
 * for(final Object o : daoInstance) {
 * 	...
 * }
 * </pre></code>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface DataAccessObject<O extends Model<?>, I extends ID<?>> {
	/**
	 * Load the instance represented by <tt>id</tt> from the database
	 * 
	 * @param id
	 *            the id
	 */
	O select(I id);

	/**
	 * Save the instance to the database. If a new database entry was created
	 * returns true.
	 * 
	 * @param object
	 *            the object
	 * @return true if the row was inserted or updated
	 */
	@IgnoreCaching
	boolean save(O object);

	/**
	 * Inserts the instance in the database.
	 * 
	 * @param object
	 *            the object
	 * @return true if the row was inserted
	 */
	@IgnoreCaching
	boolean insert(O object);

	/**
	 * Updates the instance in the database.
	 * 
	 * @param object
	 *            the object
	 * @return true if the row was updated
	 */
	@IgnoreCaching
	boolean update(O object);

	/**
	 * Deletes the instance in the database.
	 * 
	 * @param object
	 *            the object
	 * @return true if the row was deleted
	 */
	@IgnoreCaching
	boolean delete(O object);
}
