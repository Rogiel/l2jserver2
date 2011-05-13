package com.l2jserver.db.dao;

import java.util.List;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterFriendList;
import com.l2jserver.service.cache.Cacheable;
import com.l2jserver.service.cache.IgnoreCaching;
import com.l2jserver.service.database.DataAccessObject;

/**
 * The {@link CharacterFriendDAO} is can load and save
 * {@link CharacterFriendList character friend list}.
 * 
 * @author Rogiel
 */
public interface CharacterFriendDAO extends DataAccessObject<CharacterID>,
		Cacheable {
	/**
	 * Load the friend list for character represented by <tt>id</tt> from the
	 * database
	 * 
	 * @param id
	 *            the id
	 */
	List<CharacterID> load(CharacterID id);

	/**
	 * Load the friend list for character represented by <tt>character</tt> from
	 * the database
	 * 
	 * @param character
	 *            the character
	 */
	void load(L2Character character);

	/**
	 * Save the instance to the database. If a new database entry was created
	 * returns true.
	 * 
	 * @param friends
	 *            the friend list
	 * @return true if created a new entry in database (i.e. insert), false if
	 *         not created (i.e. update)
	 */
	@IgnoreCaching
	boolean save(CharacterFriendList friends);

	/**
	 * Delete an entire friend list
	 * 
	 * @param friends
	 *            the friend list
	 * @return true if at least 1 item was removed
	 */
	boolean delete(CharacterFriendList friends);

	/**
	 * Delete an <tt>friend</tt> from an <tt>character</tt>
	 * 
	 * @param character
	 *            the character id
	 * @param friend
	 *            the friend id
	 * @return true if the item was removed
	 */
	boolean delete(CharacterID character, CharacterID friend);
}
