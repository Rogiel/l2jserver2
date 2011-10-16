/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.dao;

import java.util.List;

import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.cache.Cacheable;
import com.l2jserver.service.database.DataAccessObject;

/**
 * The {@link CharacterDAO} is can load and save {@link Character character
 * instances} .
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface CharacterDAO extends
		DataAccessObject<L2Character, CharacterID>, Cacheable {
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
}
