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
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.Mapper;
import com.l2jserver.service.database.model.QCharacterFriend;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class CharacterFriendMapper implements
		Mapper<CharacterFriend, QCharacterFriend> {
	private final Mapper<FriendID, QCharacterFriend> idMapper = new Mapper<FriendID, QCharacterFriend>() {
		@Override
		public FriendID map(QCharacterFriend e, DatabaseRow row) {
			return idProvider.createID(
					charIdProvider.resolveID(row.get(e.characterId)),
					charIdProvider.resolveID(row.get(e.characterIdFriend)));
		}
	};

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
	public CharacterFriend map(QCharacterFriend e, DatabaseRow row) {
		return new CharacterFriend(idProvider.createID(
				charIdProvider.resolveID(row.get(e.characterId)),
				charIdProvider.resolveID(row.get(e.characterIdFriend))));
	}

	public Mapper<FriendID, QCharacterFriend> getIDMapper() {
		return idMapper;
	}
}