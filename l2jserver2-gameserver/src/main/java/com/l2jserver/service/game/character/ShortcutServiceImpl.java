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

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.model.dao.CharacterShortcutDAO;
import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.game.CharacterShortcut.ShortcutType;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.event.CharacterCreateShortcutEvent;
import com.l2jserver.model.world.character.event.CharacterDeleteShortcutEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcherService;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ShortcutServiceImpl extends AbstractService implements
		ShortcutService {
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcherService eventDispatcher;
	/**
	 * The {@link CharacterShortcut} DAO
	 */
	private final CharacterShortcutDAO shortcutDao;

	/**
	 * @param eventDispatcher
	 *            the event dispatcher
	 * @param shortcutDao
	 *            the shortcut DAO
	 */
	@Inject
	private ShortcutServiceImpl(WorldEventDispatcherService eventDispatcher,
			CharacterShortcutDAO shortcutDao) {
		this.eventDispatcher = eventDispatcher;
		this.shortcutDao = shortcutDao;
	}

	@Override
	public CharacterShortcut create(L2Character character, Item item, int page,
			int slot) throws ShortcutSlotNotFreeServiceException {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(item, "item");
		Preconditions.checkArgument(page >= 0 && page < 10, "0 <= page < 10");
		Preconditions.checkArgument(page >= 0 && slot < 12, "0 <= slot < 12");

		if (character.getShortcuts().get(page, slot) != null)
			throw new ShortcutSlotNotFreeServiceException();

		final CharacterShortcut shortcut = new CharacterShortcut();

		shortcut.setType(ShortcutType.ITEM);
		shortcut.setCharacterID(character.getID());
		shortcut.setItemID(item.getID());
		shortcut.setPage(page);
		shortcut.setSlot(slot);

		// synchronous save here
		shortcutDao.save(shortcut);
		character.getShortcuts().register(shortcut);

		eventDispatcher.dispatch(new CharacterCreateShortcutEvent(character,
				shortcut));

		return shortcut;
	}

	@Override
	public void remove(L2Character character, int page, int slot)
			throws ShortcutSlotEmptyServiceException {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkArgument(page >= 0 && page < 10, "0 <= page < 10");
		Preconditions.checkArgument(page >= 0 && slot < 12, "0 <= slot < 12");
		final CharacterShortcut shortcut = character.getShortcuts().get(page,
				slot);
		if (shortcut == null)
			throw new ShortcutSlotEmptyServiceException();

		// asynchronous delete here
		shortcutDao.deleteObjectsAsync(shortcut);
		character.getShortcuts().unregister(shortcut);

		eventDispatcher.dispatch(new CharacterDeleteShortcutEvent(character,
				shortcut));
	}
}
