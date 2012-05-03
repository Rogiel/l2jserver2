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
package com.l2jserver.service.game.character;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;

/**
 * This services handles an {@link L2Character} inventory
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface CharacterInventoryService extends Service {
	/**
	 * Adds an item to the player inventory
	 * 
	 * @param character
	 *            the character's whose inventory should be changed
	 * @param item
	 *            the item to be added to the inventory
	 * @throws CharacterInventoryItemExistsException
	 *             if the item already exists on the player's inventory
	 */
	void add(L2Character character, Item item)
			throws CharacterInventoryItemExistsException;

	/**
	 * Removes an item from the player inventory
	 * 
	 * @param character
	 *            the character's whose inventory should be changed
	 * @param item
	 *            the item to be removed from the inventory
	 * @throws CharacterInventoryItemDoesNotExistException
	 *             if the <code>item</code> is not present in the player's
	 *             inventory
	 */
	void remove(L2Character character, Item item)
			throws CharacterInventoryItemDoesNotExistException;

	/**
	 * Changes the order of an item into the player's inventory
	 * 
	 * @param character
	 *            character the character's whose inventory should be changed
	 * @param item
	 *            the item that will be changed the order
	 * @param order
	 *            the new order
	 * @throws CharacterInventoryItemDoesNotExistException
	 *             if the <code>item</code> is not present in the player's
	 *             inventory
	 */
	void reorder(L2Character character, Item item, int order)
			throws CharacterInventoryItemDoesNotExistException;
}
