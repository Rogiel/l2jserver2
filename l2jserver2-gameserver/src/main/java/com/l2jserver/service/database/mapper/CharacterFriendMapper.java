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
import com.l2jserver.model.game.CharacterFriend;
import com.l2jserver.model.id.FriendID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.provider.FriendIDProvider;
import com.l2jserver.service.database.dao.AbstractMapper;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.PrimaryKeyMapper;
import com.l2jserver.service.database.dao.WritableDatabaseRow;
import com.l2jserver.service.database.model.QCharacterFriend;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class CharacterFriendMapper extends
		AbstractMapper<CharacterFriend, FriendID, FriendID, QCharacterFriend> {
	// private final CompoundPrimaryKeyMapper<FriendID, CharacterID,
	// CharacterID, QCharacterFriend> idMapper = new
	// CompoundPrimaryKeyMapper<FriendID, CharacterID, CharacterID,
	// QCharacterFriend>() {
	// @Override
	// public AbstractCompoundID<CharacterID, CharacterID> raw(
	// QCharacterFriend entity, DatabaseRow row) {
	// return createID(entity, row);
	// }
	//
	// @Override
	// public FriendID createID(QCharacterFriend entity, DatabaseRow row) {
	// return idProvider.createID(
	// charIdProvider.resolveID(row.get(e.characterId)),
	// charIdProvider.resolveID(row.get(e.characterIdFriend)));
	// }
	//
	// @Override
	// public FriendID generated(
	// AbstractCompoundID<CharacterID, CharacterID> raw) {
	// return null;
	// }
	// };

	/**
	 * The {@link CharacterID} provider
	 */
	private final FriendIDProvider idProvider;
	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider charIdProvider;

	/**
	 * @param idProvider
	 *            the {@link FriendID} provider
	 * @param charIdProvider
	 *            the character id provider
	 */
	@Inject
	public CharacterFriendMapper(FriendIDProvider idProvider,
			final CharacterIDProvider charIdProvider) {
		this.idProvider = idProvider;
		this.charIdProvider = charIdProvider;
	}

	@Override
	public CharacterFriend select(QCharacterFriend e, DatabaseRow row) {
		return new CharacterFriend(idProvider.createID(
				charIdProvider.resolveID(row.get(e.characterId)),
				charIdProvider.resolveID(row.get(e.characterIdFriend))));
	}

	@Override
	public void insert(QCharacterFriend e, CharacterFriend object,
			WritableDatabaseRow row) {
		row.set(e.characterId, object.getCharacterID().getID()).set(
				e.characterIdFriend, object.getFriendID().getID());
	}

	@Override
	public void update(QCharacterFriend e, CharacterFriend object,
			WritableDatabaseRow row) {
	}

	@Override
	public PrimaryKeyMapper<FriendID, FriendID> getPrimaryKeyMapper() {
		return null;
	}
}