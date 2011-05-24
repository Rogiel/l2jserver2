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

import java.util.Iterator;
import java.util.List;

import com.l2jserver.model.Model;
import com.l2jserver.model.id.ID;
import com.l2jserver.service.cache.IgnoreCaching;

/**
 * The Data Access Object interface used used to retrieve, save and remove
 * objects from the database. The underlying storage engine can be an plain text
 * file, SQL Database or an serialized version of the object. This layer will
 * abstract the translation of the data and ease the transition from one engine
 * to another.
 * <p>
 * Every DAO is also an {@link Iterable}. If you wish you can iterate through
 * all objects in the database very abstractly. But please note that the default
 * {@link Iterator} implementation in {@link AbstractDAO} will load all the
 * {@link ID} objects and for every call {@link Iterator#next()}, a new database
 * query will be made requesting the given object. In a large dataset, this
 * could be a huge performance issue. DAO implementations are encouraged to
 * override the iterator implementation with a more specific implementation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface DataAccessObject<O extends Model<?>, I extends ID<?>> extends
		Iterable<O> {
	/**
	 * Load the instance represented by <tt>id</tt> from the database
	 * 
	 * @param id
	 *            the id
	 */
	O select(I id);

	/**
	 * Loads an List of all {@link ID}s in the database
	 * 
	 * @return the list containing all {@link ID} objects
	 */
	@IgnoreCaching
	List<I> selectIDs();

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
