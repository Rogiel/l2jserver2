package com.l2jserver.model.world.character;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

public class CharacterInventory implements Iterable<Item> {
	private final L2Character character;

	/**
	 * The items in this character inventory
	 */
	private final Set<Item> items = CollectionFactory.newSet(Item.class);

	public CharacterInventory(L2Character character) {
		this.character = character;
	}

	/**
	 * This method will add new items to the inventory. This is normally called
	 * from the DAO object.
	 * 
	 * @param items
	 *            the items to be added
	 */
	public void load(List<Item> items) {
		items.addAll(items);
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}

	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}

	public enum InventoryLocation {
		PAPERDOLL, INVENTORY;
	}

	public enum InventoryPaperdoll {
		UNDERWEAR, HEAD, HAIR1, HAIR2, NECK, RIGHT_HAND, LEFT_HAND, RIGHT_EAR, LEFT_EAR, GLOVES, LEGS, LEFT_FEET, RIGHT_FEET, RIGHT_FINGER, LEFT_FINGER, LEFT_BRACELET, RIGHT_BRACELET, DECORATION_1, DECOREATION_2, DECORATION_3, DECORATION_4, DECORATION_5, DECORATION_6, CLOAK, BELT;
	}
}
