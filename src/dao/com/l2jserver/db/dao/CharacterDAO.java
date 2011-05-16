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

import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.Clan;
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
	 * Load the members of the given <tt>clan</tt>
	 * 
	 * @param clan
	 *            the clan
	 */
	void load(Clan clan);

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
	 * @param account
	 *            the account id
	 * @return the found characters. An empty list if this account has no
	 *         characters.
	 */
	List<L2Character> selectByAccount(AccountID account);

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
