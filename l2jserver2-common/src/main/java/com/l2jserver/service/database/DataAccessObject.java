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
package com.l2jserver.service.database;

import java.util.Collection;
import java.util.Iterator;

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
 * @param <O>
 *            the {@link Model} supported by this DAO
 * @param <I>
 *            the ID used to represent the {@link Model} in this DAO
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public interface DataAccessObject<O extends Model<?>, I extends ID<?>> extends
		Iterable<O> {
	/**
	 * Load the instance represented by <tt>id</tt> from the database
	 * 
	 * @param id
	 *            the id
	 * @return the selected object. <tt>null</tt> if could not be found in the
	 *         database.
	 */
	O select(I id);

	/**
	 * Loads an List of all {@link ID}s in the database
	 * 
	 * @return the list containing all {@link ID} objects
	 */
	@IgnoreCaching
	Collection<I> selectIDs();

	/**
	 * Save the instance to the database. If a new database entry was created
	 * returns true. This method will only save if the object has changed.
	 * 
	 * @param object
	 *            the object
	 * @return true if the row was inserted or updated
	 * @see DataAccessObject#save(Model, boolean)
	 */
	@IgnoreCaching
	boolean save(O object);

	/**
	 * Save the instance to the database. If a new database entry was created
	 * returns true.
	 * 
	 * @param object
	 *            the object
	 * @param force
	 *            will force an save, even if the object has not changed
	 * @return true if the row was inserted or updated
	 */
	@IgnoreCaching
	boolean save(O object, boolean force);

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
