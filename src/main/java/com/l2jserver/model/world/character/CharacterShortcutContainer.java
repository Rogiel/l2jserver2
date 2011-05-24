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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.l2jserver.model.game.Shortcut;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Container that controls shortcut for character instances
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterShortcutContainer implements Iterable<Shortcut> {
	/**
	 * The character
	 */
	private final L2Character character;
	/**
	 * The shortcut list
	 */
	private List<Shortcut> shortcuts = CollectionFactory.newList();

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
	public void register(Shortcut shortcut) {
		shortcuts.add(shortcut);
		Collections.sort(shortcuts, new ShortcutSlotComparator());
	}

	/**
	 * Unregisters the given shortcut from this container
	 * 
	 * @param shortcut
	 *            the shortcut to be removed
	 */
	public void unregister(Shortcut shortcut) {
		shortcuts.remove(shortcut);
		Collections.sort(shortcuts, new ShortcutSlotComparator());
	}

	/**
	 * Swap two shortcuts between them. Once swap is complete,
	 * <tt>shortcut1</tt> will be in the place of <tt>shortcut2</tt>, and
	 * <tt>shortcut2</tt> in <tt>shortcut1</tt>.
	 * 
	 * @param shortcut1
	 *            the first shortcut
	 * @param shortcut2
	 *            the second shortcut
	 */
	public void swap(Shortcut shortcut1, Shortcut shortcut2) {
		// only swap if is registered already
		if (!shortcuts.contains(shortcut1) || !shortcuts.contains(shortcut2))
			return;

		final int slot1 = shortcut1.getSlot();
		final int page1 = shortcut1.getPage();
		shortcut1.setSlot(shortcut2.getSlot());
		shortcut1.setPage(shortcut2.getPage());
		shortcut2.setSlot(slot1);
		shortcut2.setPage(page1);
		
		Collections.sort(shortcuts, new ShortcutSlotComparator());
	}

	/**
	 * @return true if container is full
	 */
	public boolean isFull() {
		return shortcuts.size() >= 12 * 4;
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
	public void load(Collection<Shortcut> shortcuts) {
		this.shortcuts.addAll(shortcuts);
		Collections.sort(this.shortcuts, new ShortcutSlotComparator());
	}

	@Override
	public Iterator<Shortcut> iterator() {
		return shortcuts.iterator();
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}

	public static class ShortcutSlotComparator implements Comparator<Shortcut> {
		@Override
		public int compare(Shortcut o1, Shortcut o2) {
			return ((o1.getPage() * o1.getSlot()) - (o2.getPage() * o2
					.getSlot()));
		}
	}
}
