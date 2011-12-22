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
import com.l2jserver.service.core.threading.AbstractTask;
import com.l2jserver.service.core.threading.AsyncFuture;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.database.DatabaseService.TransactionExecutor;

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

	@Inject
	/**
	 * The ThreadService used to execute operations asynchronously.
	 */
	protected ThreadService threadService;

	/**
	 * @param database
	 *            the database service
	 */
	@Inject
	protected AbstractDAO(DatabaseService database) {
		this.database = database;
	}

	@Override
	public AsyncFuture<T> selectAsync(final I id) {
		return threadService.async(new AbstractTask<T>() {
			@Override
			public T call() throws Exception {
				return select(id);
			}
		});
	}

	@Override
	public int save(T object) {
		return save(object, false);
	}

	@Override
	public int save(T object, boolean force) {
		switch (object.getObjectDesire()) {
		case INSERT:
			return insertObjects(wrap(object));
		case UPDATE:
			return updateObjects(wrap(object));
		case DELETE:
			return deleteObjects(wrap(object));
		case NONE:
			return (force ? updateObjects(wrap(object)) : 0);
		default:
			return 0;
		}
	}

	@Override
	@SafeVarargs
	public final int saveObjects(final T... objects) {
		return database.transaction(new TransactionExecutor() {
			@Override
			public int perform() {
				int rows = 0;
				for (final T object : objects) {
					rows += save(object);
				}
				return rows;
			}
		});
	}

	@Override
	@SafeVarargs
	public final AsyncFuture<Integer> saveObjectsAsync(final T... objects) {
		return threadService.async(new AbstractTask<Integer>() {
			@Override
			public Integer call() throws Exception {
				return saveObjects(objects);
			}
		});
	}

	@Override
	public int insert(T object) {
		return insertObjects(wrap(object));
	}

	@Override
	@SafeVarargs
	public final AsyncFuture<Integer> insertObjectsAsync(final T... objects) {
		return threadService.async(new AbstractTask<Integer>() {
			@Override
			public Integer call() throws Exception {
				return insertObjects(objects);
			}
		});
	}

	@Override
	public int update(T object) {
		return updateObjects(wrap(object));
	}

	@Override
	@SafeVarargs
	public final AsyncFuture<Integer> updateObjectsAsync(final T... objects) {
		return threadService.async(new AbstractTask<Integer>() {
			@Override
			public Integer call() throws Exception {
				return updateObjects(objects);
			}
		});
	}

	@Override
	public void delete(T object) {
		deleteObjects(wrap(object));
	}

	@Override
	@SafeVarargs
	public final AsyncFuture<Integer> deleteObjectsAsync(final T... objects) {
		return threadService.async(new AbstractTask<Integer>() {
			@Override
			public Integer call() throws Exception {
				return deleteObjects(objects);
			}
		});
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

	protected abstract T[] wrap(Model<?>... objects);
}
