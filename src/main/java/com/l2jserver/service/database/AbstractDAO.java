package com.l2jserver.service.database;

import com.google.inject.Inject;

public abstract class AbstractDAO<T> implements DataAccessObject<T> {
	protected final DatabaseService database;

	@Inject
	protected AbstractDAO(DatabaseService database) {
		this.database = database;
	}

	public DatabaseService getDatabase() {
		return database;
	}
}
