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

import com.l2jserver.service.Service;

/**
 * The database service manages connection between the {@link DataAccessObject}
 * implementation and the database.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface DatabaseService extends Service {
	/**
	 * Get the database object cached. Will return null if the object is not in
	 * cache
	 * 
	 * @param id
	 *            the object id
	 * @return the cached object
	 */
	Object getCachedObject(Object id);

	/**
	 * Tests if the object is cached
	 * 
	 * @param id
	 *            the object id
	 * @return true if object is in cache
	 */
	boolean hasCachedObject(Object id);

	/**
	 * Adds or update the cache object
	 * 
	 * @param key
	 *            the cache key
	 * @param value
	 *            the object
	 */
	void updateCache(Object key, Object value);

	/**
	 * Removes an object from the cache
	 * 
	 * @param key
	 *            the cache key
	 */
	void removeCache(Object key);
}
