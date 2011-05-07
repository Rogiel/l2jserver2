package com.l2jserver.db.dao;

import java.util.List;

import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.cache.Cacheable;
import com.l2jserver.service.cache.IgnoreCaching;
import com.l2jserver.service.database.DataAccessObject;

/**
 * The {@link CharacterDAO} is can load and save {@link Character character
 * instances} .
 * 
 * @author Rogiel
 */
public interface CharacterDAO extends DataAccessObject<L2Character>, Cacheable {
	/**
	 * Load the instance represented by <tt>id</tt> from the database
	 * 
	 * @param id
	 *            the id
	 */
	L2Character load(CharacterID id);

	/**
	 * Select an character by its name.
	 * 
	 * @param name
	 *            the character name
	 * @return the found character. Null if does not exists.
	 */
	L2Character selectByName(String name);

	/**
	 * Select an character by its name.
	 * 
	 * @param name
	 *            the character name
	 * @return the found character. Null if does not exists.
	 */
	List<L2Character> selectByAccount(String username);

	/**
	 * Loads an List of all {@link ID}s in the database
	 * 
	 * @return the list containing all ids
	 */
	@IgnoreCaching
	List<CharacterID> listIDs();

	/**
	 * Save the instance to the database. If a new database entry was created
	 * returns true.
	 * 
	 * @param option
	 *            the id
	 * @return true if created a new entry in database (i.e. insert), false if
	 *         not created (i.e. update)
	 */
	@IgnoreCaching
	boolean save(L2Character character);
}
