package com.l2jserver.service.database;

import com.google.inject.Inject;

/**
 * Abstract DAO implementations. Store an instance of {@link DatabaseService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the dao object type
 */
public abstract class AbstractDAO<T> implements DataAccessObject<T> {
	/**
	 * The database service instance
	 */
	protected final DatabaseService database;

	@Inject
	protected AbstractDAO(DatabaseService database) {
		this.database = database;
	}

	/**
	 * @return the database service
	 */
	public DatabaseService getDatabase() {
		return database;
	}
}
