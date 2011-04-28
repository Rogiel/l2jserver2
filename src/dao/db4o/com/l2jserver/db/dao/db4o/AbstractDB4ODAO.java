package com.l2jserver.db.dao.db4o;

import com.google.inject.Inject;
import com.l2jserver.service.database.AbstractDAO;
import com.l2jserver.service.database.DB4ODatabaseService;

public class AbstractDB4ODAO extends AbstractDAO implements BD4ODAO {
	protected final DB4ODatabaseService database;

	@Inject
	protected AbstractDB4ODAO(DB4ODatabaseService database) {
		super(database);
		this.database = database;
	}
}
