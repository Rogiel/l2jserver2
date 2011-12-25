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
package com.l2jserver.service.database.dao.orientdb;

import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.CharacterFriendDAO;
import com.l2jserver.model.game.CharacterFriend;
import com.l2jserver.model.id.FriendID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterFriendList;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.mapper.CharacterFriendMapper;
import com.l2jserver.service.database.model.QCharacterFriend;
import com.l2jserver.service.database.orientdb.AbstractOrientDBDAO;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.DeleteQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.InsertQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.SelectListQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.SelectSingleQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNative;

/**
 * {@link CharacterFriendDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class OrientDBCharacterFriendDAO extends
		AbstractOrientDBDAO<CharacterFriend, FriendID> implements
		CharacterFriendDAO {
	/**
	 * The {@link CharacterFriend} mapper
	 */
	private final CharacterFriendMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the character friend mapper
	 */
	@Inject
	public OrientDBCharacterFriendDAO(DatabaseService database,
			CharacterFriendMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public CharacterFriend select(final FriendID id) {
		return database
				.query(new SelectSingleQuery<CharacterFriend, FriendID, FriendID, QCharacterFriend>(
						QCharacterFriend.characterFriend, mapper) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacterFriend e) {
						return record.field(name(e.characterId))
								.eq(id.getCharacterID().getID())
								.field(name(e.characterIdFriend))
								.eq(id.getFriendID());
					}
				});
	}

	@Override
	public void load(final L2Character character) {
		final List<CharacterFriend> list = database
				.query(new SelectListQuery<CharacterFriend, FriendID, FriendID, QCharacterFriend>(
						QCharacterFriend.characterFriend, mapper) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacterFriend e) {
						return record.field(name(e.characterId)).eq(
								character.getID().getID());
					}
				});
		character.getFriendList().load(list);
	}

	@Override
	public List<FriendID> selectIDs() {
		return database
				.query(new SelectListQuery<FriendID, FriendID, FriendID, QCharacterFriend>(
						QCharacterFriend.characterFriend, mapper
								.getIDMapper(QCharacterFriend.characterFriend)) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacterFriend e) {
						return record;
					}
				});
	}

	@Override
	public int insertObjects(CharacterFriend... friends) {
		return database
				.query(new InsertQuery<CharacterFriend, FriendID, FriendID, QCharacterFriend>(
						QCharacterFriend.characterFriend, mapper, friends));
	}

	@Override
	public int updateObjects(CharacterFriend... friends) {
		// it is not possible update friend objects, because they are only a ID
		// pair and IDs are immutable
		return 0;
	}

	@Override
	public int deleteObjects(CharacterFriend... friends) {
		return database
				.query(new DeleteQuery<CharacterFriend, QCharacterFriend>(
						QCharacterFriend.characterFriend, friends) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, CharacterFriend friend) {
						return record.field(name(e.characterId))
								.eq(friend.getID().getCharacterID().getID())
								.field(name(e.characterIdFriend))
								.eq(friend.getID().getFriendID());
					}
				});
	}

	@Override
	public boolean save(final CharacterFriendList friends) {
		for (final CharacterFriend friend : friends) {
			if (save(friend) == 0)
				return false;
		}
		return true;
	}

	@Override
	public boolean delete(final CharacterFriendList friends) {
		for (final CharacterFriend friend : friends) {
			if (deleteObjects(friend) == 0)
				return false;
		}
		return true;
	}

	@Override
	protected CharacterFriend[] wrap(Model<?>... objects) {
		final CharacterFriend[] array = new CharacterFriend[objects.length];
		int i = 0;
		for (final Model<?> object : objects) {
			array[i++] = (CharacterFriend) object;
		}
		return array;
	}
}
