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
package com.l2jserver.service.database.mapper;

import com.google.inject.Inject;
import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.game.CharacterShortcut.ShortcutType;
import com.l2jserver.model.id.CharacterShortcutID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.id.provider.CharacterShortcutIDProvider;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.Mapper;
import com.l2jserver.service.database.model.QCharacterShortcut;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class CharacterShortcutMapper implements
		Mapper<CharacterShortcut, QCharacterShortcut> {
	private final Mapper<CharacterShortcutID, QCharacterShortcut> idMapper = new Mapper<CharacterShortcutID, QCharacterShortcut>() {
		@Override
		public CharacterShortcutID map(QCharacterShortcut e, DatabaseRow row) {
			return idProvider.resolveID(row.get(e.shortcutId));
		}
	};

	/**
	 * The {@link CharacterShortcutID} provider
	 */
	private final CharacterShortcutIDProvider idProvider;

	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider charIdProvider;
	/**
	 * The {@link ItemID} provider
	 */
	private final ItemIDProvider itemIdProvider;

	/**
	 * @param idProvider
	 *            the {@link CharacterID} provider
	 * @param charIdProvider
	 *            the character ID provider
	 * @param itemIdProvider
	 *            the item ID provider
	 * 
	 */
	@Inject
	public CharacterShortcutMapper(
			final CharacterShortcutIDProvider idProvider,
			CharacterIDProvider charIdProvider, ItemIDProvider itemIdProvider) {
		this.idProvider = idProvider;
		this.charIdProvider = charIdProvider;
		this.itemIdProvider = itemIdProvider;
	}

	@Override
	public CharacterShortcut map(QCharacterShortcut e, DatabaseRow row) {
		final CharacterShortcut shortcut = new CharacterShortcut();
		shortcut.setID(idProvider.resolveID(row.get(e.shortcutId)));
		final CharacterID charId = charIdProvider.resolveID(row
				.get(e.characterId));
		shortcut.setCharacterID(charId);

		// resolve type
		final ShortcutType type = row.get(e.type);
		shortcut.setType(type);
		switch (type) {
		case ITEM:
			final ItemID itemId = itemIdProvider.resolveID(row.get(e.objectId));
			shortcut.setItemID(itemId);
			break;
		}

		shortcut.setPage(row.get(e.page));
		shortcut.setSlot(row.get(e.slot));

		return shortcut;
	}

	public Mapper<CharacterShortcutID, QCharacterShortcut> getIDMapper() {
		return idMapper;
	}
}