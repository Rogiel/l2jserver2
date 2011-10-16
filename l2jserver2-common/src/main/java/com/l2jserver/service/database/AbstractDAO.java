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

import java.util.Iterator;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.id.ID;

/**
 * Abstract DAO implementations. Store an instance of {@link DatabaseService}.
 * Default {@link Iterator} implementation in this class supports
 * {@link Iterator#remove()} and will delete the row from the database.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the dao object type
 * @param <I>
 *            the object id type
 */
public abstract class AbstractDAO<T extends Model<?>, I extends ID<?>>
		implements DataAccessObject<T, I> {
	/**
	 * The database service instance
	 */
	protected final DatabaseService database;

	/**
	 * @param database
	 *            the database service
	 */
	@Inject
	protected AbstractDAO(DatabaseService database) {
		this.database = database;
	}

	@Override
	public boolean save(T object) {
		return save(object, false);
	}

	@Override
	public boolean save(T object, boolean force) {
		switch (object.getObjectDesire()) {
		case INSERT:
			return insert(object);
		case UPDATE:
			return update(object);
		case DELETE:
			return delete(object);
		case NONE:
			return (force ? update(object) : false);
		default:
			return false;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			/**
			 * The Iterator that will return the ID objects
			 */
			private final Iterator<I> iterator = AbstractDAO.this.selectIDs()
					.iterator();
			/**
			 * The last used ID (will be used to remove the last element)
			 */
			private I lastID;

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public T next() {
				lastID = iterator.next();
				if (lastID == null)
					return null;
				return select(lastID);
			}

			@Override
			public void remove() {
				AbstractDAO.this.delete(select(lastID));
			}
		};
	}

	/**
	 * @return the database service
	 */
	public DatabaseService getDatabase() {
		return database;
	}
}
