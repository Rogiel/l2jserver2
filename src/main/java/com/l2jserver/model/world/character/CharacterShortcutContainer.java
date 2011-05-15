package com.l2jserver.model.world.character;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
	private List<CharacterShortcut> shortcuts = CollectionFactory
			.newList(CharacterShortcut.class);

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
		shortcuts.add(shortcut);
	}

	/**
	 * Unregisters the given shortcut from this container
	 * 
	 * @param shortcut
	 *            the shortcut to be removed
	 */
	public void unregister(CharacterShortcut shortcut) {
		shortcuts.remove(shortcut);
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
	public void swap(CharacterShortcut shortcut1, CharacterShortcut shortcut2) {
		// only swap if is registered already
		if (!shortcuts.contains(shortcut1) || !shortcuts.contains(shortcut2))
			return;

		final int slot1 = shortcut1.getSlot();
		final int page1 = shortcut1.getPage();
		shortcut1.setSlot(shortcut2.getSlot());
		shortcut1.setPage(shortcut2.getPage());
		shortcut2.setSlot(slot1);
		shortcut2.setPage(page1);
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
	public void load(Collection<CharacterShortcut> shortcuts) {
		this.shortcuts.addAll(shortcuts);
	}

	@Override
	public Iterator<CharacterShortcut> iterator() {
		return shortcuts.iterator();
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}
}
