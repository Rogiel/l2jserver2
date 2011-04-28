package com.l2jserver.db.dao;

import com.l2jserver.model.world.Player;
import com.l2jserver.service.database.DataAccessObject;

/**
 * The {@link PlayerDAO} is can load and save {@link Player player instances}.
 * 
 * @author Rogiel
 */
public interface PlayerDAO extends DataAccessObject {
	void load(Player player);
}
