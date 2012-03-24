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

import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;

/**
 * This services handles {@link CharacterShortcut}s
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ShortcutService extends Service {
	/**
	 * Creates a new {@link Item} {@link CharacterShortcut shortcut}.
	 * 
	 * @param character
	 *            the character that will be using the shortcut
	 * @param item
	 *            the item
	 * @param page
	 *            the page in which the shortcut should be created
	 * @param slot
	 *            the slot in which the shortcut should be created
	 * @return the nerly created shortcut
	 * @throws ShortcutSlotNotFreeServiceException
	 *             if there is no free shortcut slot
	 */
	CharacterShortcut create(L2Character character, Item item, int page,
			int slot) throws ShortcutSlotNotFreeServiceException;

	/**
	 * Deletes an existing {@link CharacterShortcut}.
	 * 
	 * @param character
	 *            the character
	 * @param page
	 *            the page in which the shortcut should be removed
	 * @param slot
	 *            the slot in which the shortcut should be removed
	 * @throws ShortcutSlotEmptyServiceException
	 *             if the shortcut slot was empty
	 */
	void remove(L2Character character, int page, int slot)
			throws ShortcutSlotEmptyServiceException;
}
