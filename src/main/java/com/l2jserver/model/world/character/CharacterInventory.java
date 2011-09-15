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
package com.l2jserver.model.world.character;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This class controls an {@link L2Character} inventory
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterInventory implements Iterable<Item> {
	/**
	 * The character
	 */
	private final L2Character character;

	/**
	 * The items in this character inventory
	 */
	private final Set<Item> items = CollectionFactory.newSet();

	/**
	 * Creates a new instance
	 * 
	 * @param character
	 *            the character
	 */
	public CharacterInventory(L2Character character) {
		this.character = character;
	}

	/**
	 * Get the item in the given <tt>paperdoll</tt> slot
	 * 
	 * @param paperdoll
	 *            the paperdoll slot
	 * @return the item in slot, null if emptys
	 */
	public Item getItem(InventoryPaperdoll paperdoll) {
		for (final Item item : items) {
			if (item.getPaperdoll() == paperdoll)
				return item;
		}
		return null;
	}

	/**
	 * Checks if the given paperdoll has is occupied
	 * 
	 * @param paperdoll
	 *            the paperdoll slot
	 * @return true if has an item
	 */
	public boolean has(InventoryPaperdoll paperdoll) {
		return getItem(paperdoll) != null;
	}

	/**
	 * This method will add new items to the inventory. This is normally called
	 * from the DAO object.
	 * 
	 * @param items
	 *            the items to be added
	 */
	public void load(Collection<Item> items) {
		this.items.clear();
		this.items.addAll(items);
	}

	/**
	 * @return the amount if items in inventory
	 */
	public int getItemCount() {
		return items.size();
	}

	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}

	/**
	 * Location of an item in the player's inventory
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum InventoryLocation {
		/**
		 * The item is equipped
		 */
		PAPERDOLL,
		/**
		 * The item is stored in the inventory
		 */
		INVENTORY,
		/**
		 * The item is in the warehouse
		 */
		WAREHOUSE;
	}

	/**
	 * {@link InventoryLocation#PAPERDOLL Paperdoll} slots for items
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum InventoryPaperdoll {
		UNDERWEAR(0), HEAD(1), HAIR1(2), HAIR2(3), NECK(4), RIGHT_HAND(5), CHEST(
				6), LEFT_HAND(7), RIGHT_EAR(8), LEFT_EAR(9), GLOVES(10), LEGS(
				11), FEET(12), RIGHT_FINGER(13), LEFT_FINGER(14), LEFT_BRACELET(
				15), RIGHT_BRACELET(16), DECORATION_1(17), DECORATION_2(18), DECORATION_3(
				19), DECORATION_4(20), DECORATION_5(21), DECORATION_6(22), CLOAK(
				23), BELT(24);

		/**
		 * The inventory paperdoll slot id
		 */
		public final int id;

		/**
		 * @param id
		 *            the slot id
		 */
		InventoryPaperdoll(int id) {
			this.id = id;
		}
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}
}
