package com.l2jserver.db.dao.db4o;

import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.model.id.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.DB4ODatabaseService;

public class DB4OCharacterDAO extends AbstractDB4ODAO<L2Character> implements CharacterDAO {
	@Inject
	protected DB4OCharacterDAO(DB4ODatabaseService database) {
		super(database);
	}

	@Override
	public L2Character load(CharacterID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(L2Character character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CharacterID> listIDs() {
		// TODO Auto-generated method stub
		return null;
	}
}
