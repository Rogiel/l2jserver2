package com.l2jserver.model.id;

import com.google.inject.Inject;
import com.l2jserver.db.dao.ClanDAO;
import com.l2jserver.model.world.Clan;

public final class ClanID extends ObjectID<Clan> {
	/**
	 * Data Access Object (DAO) for clans
	 */
	private final ClanDAO clanDao;

	@Inject
	protected ClanID(int id, ClanDAO clanDao) {
		super(id);
		this.clanDao = clanDao;
	}

	@Override
	public Clan getObject() {
		return clanDao.load(this);
	}
}
