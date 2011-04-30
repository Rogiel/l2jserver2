package com.l2jserver.db.dao.mysql5;

import com.google.inject.Inject;
import com.l2jserver.service.database.AbstractDAO;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService;

public class AbstractMySQL5DAO<T> extends AbstractDAO<T> {
	protected final MySQLDatabaseService database;

	@Inject
	protected AbstractMySQL5DAO(DatabaseService database) {
		super(database);
		this.database = (MySQLDatabaseService) database;
	}
}
