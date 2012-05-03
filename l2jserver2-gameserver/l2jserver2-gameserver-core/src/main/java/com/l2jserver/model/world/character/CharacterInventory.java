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
package com.l2jserver.model.world.character;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.template.ItemTemplateID;
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
	 * Adds an item to the player inventory.
	 * 
	 * @param item
	 *            the item
	 */
	public void add(Item item) {
		items.add(item);
	}

	/**
	 * Removes the item from the players inventory.
	 * 
	 * @param item
	 *            the item to be removed
	 * @return the item if it was successfully removed or <code>null</code> if
	 *         the item was not removed.
	 */
	public Item remove(Item item) {
		if (items.remove(item))
			return item;
		return null;
	}

	/**
	 * Removes several items from the players inventory.
	 * 
	 * @param items
	 *            the items to be removed
	 * @return the items that were effectivelly removed
	 */
	public Item[] remove(Item... items) {
		final List<Item> removedItems = CollectionFactory.newList();
		for (final Item item : items) {
			if (this.items.remove(item)) {
				removedItems.add(item);
			}
		}
		return removedItems.toArray(new Item[removedItems.size()]);
	}

	/**
	 * Removes all items from the given {@link ItemID}
	 * 
	 * @param itemID
	 *            the {@link ItemID}
	 * @return an array of all items removed. Can never be <code>null</code>.
	 */
	public Item[] remove(ItemID itemID) {
		final List<Item> removedItems = CollectionFactory.newList();
		for (final Item item : items) {
			if (item.getID().equals(itemID)) {
				items.remove(item);
				removedItems.add(item);
			}
		}
		return removedItems.toArray(new Item[removedItems.size()]);
	}

	/**
	 * Returns all items from the given {@link ItemTemplateID}
	 * 
	 * @param itemTemplateID
	 *            the {@link ItemTemplateID}
	 * @return an array of all items with the given ID
	 */
	public Item[] getItems(ItemTemplateID itemTemplateID) {
		final List<Item> allItems = CollectionFactory.newList();
		for (final Item item : items) {
			if (item.getTemplateID().equals(itemTemplateID)) {
				allItems.add(item);
			}
		}
		return allItems.toArray(new Item[allItems.size()]);
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
	 * Checks if the given item is already on the character's inventory
	 * 
	 * @param item
	 *            the item
	 * @return true if has the item
	 */
	public boolean has(Item item) {
		for(final Item checkItem : items) {
			if(checkItem.getID().equals(item.getID()))
				return true;
		}
		return false;
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
	public enum ItemLocation {
		/**
		 * The item is dropped on the ground
		 */
		GROUND,
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
	 * {@link ItemLocation#PAPERDOLL Paperdoll} slots for items
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum InventoryPaperdoll {
		/**
		 * Underwear
		 */
		UNDERWEAR(0),

		/**
		 * Head
		 */
		HEAD(1),

		/**
		 * Hair, slot 1
		 */
		HAIR1(2),

		/**
		 * Hair slot 2
		 */
		HAIR2(3),

		/**
		 * Neck
		 */
		NECK(4),

		/**
		 * Right hant
		 */
		RIGHT_HAND(5),

		/**
		 * Chest
		 */
		CHEST(6),

		/**
		 * Left hand
		 */
		LEFT_HAND(7),

		/**
		 * Right ear
		 */
		RIGHT_EAR(8),

		/**
		 * Left ear
		 */
		LEFT_EAR(9),

		/**
		 * Gloves
		 */
		GLOVES(10),

		/**
		 * Legs
		 */
		LEGS(11),

		/**
		 * Feet
		 */
		FEET(12),

		/**
		 * Right finger
		 */
		RIGHT_FINGER(13),

		/**
		 * Left finger
		 */
		LEFT_FINGER(14),

		/**
		 * Left bracelet
		 */
		LEFT_BRACELET(15),

		/**
		 * Right bracelet
		 */
		RIGHT_BRACELET(16),

		/**
		 * Decoration, slot 1
		 */
		DECORATION_1(17),

		/**
		 * Decoration slot 2
		 */
		DECORATION_2(18),
		/**
		 * Decoration slot 3
		 */
		DECORATION_3(19),
		/**
		 * Decoration slot 4
		 */
		DECORATION_4(20),
		/**
		 * Decoration slot 5
		 */
		DECORATION_5(21),
		/**
		 * Decoration slot 6
		 */
		DECORATION_6(22),
		/**
		 * Cloak
		 */
		CLOAK(23),
		/**
		 * Belt
		 */
		BELT(24);

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
