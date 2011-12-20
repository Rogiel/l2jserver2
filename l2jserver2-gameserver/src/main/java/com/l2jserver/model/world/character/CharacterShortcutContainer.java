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
import java.util.Map;
import java.util.Map.Entry;

import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Container that controls shortcut for character instances
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterShortcutContainer implements Iterable<CharacterShortcut> {
	/**
	 * The character
	 */
	private final L2Character character;
	/**
	 * The shortcut list
	 */
	private Map<Integer, CharacterShortcut> shortcuts = CollectionFactory
			.newMap();

	/**
	 * Creates a new instance
	 * 
	 * @param character
	 *            the character
	 */
	public CharacterShortcutContainer(L2Character character) {
		this.character = character;
	}

	/**
	 * Registers a new shortcut to this container
	 * 
	 * @param shortcut
	 *            the shortcut to be added
	 */
	public void register(CharacterShortcut shortcut) {
		shortcuts.put(shortcut.getPage() * 12 + shortcut.getSlot(), shortcut);
	}

	/**
	 * Unregisters the given shortcut from this container
	 * 
	 * @param shortcut
	 *            the shortcut to be removed
	 */
	public void unregister(CharacterShortcut shortcut) {
		for (final Entry<Integer, CharacterShortcut> entry : shortcuts
				.entrySet()) {
			if (entry.getValue().getID().equals(shortcut.getID())) {
				shortcuts.remove(entry.getKey());
				return;
			}
		}
	}

	/**
	 * @param page
	 *            the page
	 * @param slot
	 *            the slot
	 * @return the given character shortcut, if registered.
	 */
	public CharacterShortcut get(int page, int slot) {
		return shortcuts.get(page * 12 + slot);
	}

	/**
	 * @return true if container is full
	 */
	public boolean isFull() {
		return shortcuts.size() >= 12 * 10;
	}

	/**
	 * @return true if there is not shortcut registered
	 */
	public boolean isEmpty() {
		return shortcuts.isEmpty();
	}

	/**
	 * Loads all shortcuts in the given {@link Collection}. This method is
	 * normally called from a DAO.
	 * 
	 * @param shortcuts
	 *            the collection of shortcuts
	 */
	public void load(Collection<CharacterShortcut> shortcuts) {
		for (final CharacterShortcut shortcut : shortcuts) {
			register(shortcut);
		}
	}

	/**
	 * @return the amount if shortcuts in the container
	 */
	public int getShortcutCount() {
		return shortcuts.size();
	}

	@Override
	public Iterator<CharacterShortcut> iterator() {
		return shortcuts.values().iterator();
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}
}
