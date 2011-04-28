package com.l2jserver.db.dao.db4o;

import com.google.inject.Inject;
import com.l2jserver.db.dao.PlayerDAO;
import com.l2jserver.model.world.Player;
import com.l2jserver.service.database.DB4ODatabaseService;

public class DB4OPlayerDAO extends AbstractDB4ODAO implements PlayerDAO {
	@Inject
	protected DB4OPlayerDAO(DB4ODatabaseService database) {
		super(database);
	}

	@Override
	public void load(Player player) {
		
	}
}
