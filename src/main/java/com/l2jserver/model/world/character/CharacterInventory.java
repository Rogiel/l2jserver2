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
}
