package com.l2jserver.model.id.object;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.L2Character;

public final class CharacterID extends ObjectID<L2Character> {
	/**
	 * Data Access Object (DAO) for characters
	 */
	private final CharacterDAO characterDao;

	@Inject
	public CharacterID(@Assisted int id, CharacterDAO characterDao) {
		super(id);
		this.characterDao = characterDao;
	}

	@Override
	public L2Character getObject() {
		return characterDao.load(this);
	}
}
