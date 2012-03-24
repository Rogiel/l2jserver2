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

import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.DataAccessObject;

/**
 * The {@link ItemDAO} is can load and save {@link Character character
 * instances} .
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ItemDAO extends DataAccessObject<Item, ItemID> {
	/**
	 * Load the inventory for an {@link L2Character character}.
	 * 
	 * @param character
	 *            the character
	 * @return amount list of character items
	 */
	List<Item> selectByCharacter(L2Character character);

	/**
	 * Select from the database the items dropped on the ground
	 * 
	 * @return an {@link List} of all items on the ground
	 */
	List<Item> selectDroppedItems();
}
