package com.l2jserver.db.dao;

import com.l2jserver.model.id.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.Player;
import com.l2jserver.service.database.DataAccessObject;

/**
 * The {@link CharacterDAO} is can load and save {@link Player player instances}.
 * 
 * @author Rogiel
 */
public interface CharacterDAO extends DataAccessObject {
	L2Character load(CharacterID id);
	void save(L2Character character);
}
