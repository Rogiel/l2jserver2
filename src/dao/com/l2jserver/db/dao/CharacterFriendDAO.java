/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
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
