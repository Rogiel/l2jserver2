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
package com.l2jserver.service.database.sql;

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
import com.l2jserver.service.database.sql.AbstractSQLDAO;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.DeleteQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.InsertQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectSingleQuery;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;

/**
 * {@link CharacterFriendDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SQLCharacterFriendDAO extends
		AbstractSQLDAO<CharacterFriend, FriendID> implements
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
	public SQLCharacterFriendDAO(DatabaseService database,
			CharacterFriendMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public CharacterFriend select(final FriendID id) {
		return database
				.query(new SelectSingleQuery<CharacterFriend, QCharacterFriend>(
						QCharacterFriend.characterFriend, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q,
							QCharacterFriend e) {
						q.where(e.characterId.eq(id.getCharacterID().getID())
								.and(e.characterIdFriend.eq(id.getFriendID()
										.getID())));
					}
				});
	}

	@Override
	public void load(final L2Character character) {
		final List<CharacterFriend> list = database
				.query(new SelectListQuery<CharacterFriend, QCharacterFriend>(
						QCharacterFriend.characterFriend, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q,
							QCharacterFriend e) {
						q.where(e.characterId.eq(character.getID().getID()));
					}
				});
		character.getFriendList().load(list);
	}

	@Override
	public List<FriendID> selectIDs() {
		return database.query(new SelectListQuery<FriendID, QCharacterFriend>(
				QCharacterFriend.characterFriend, mapper.getIDMapper()) {
			@Override
			protected void query(AbstractSQLQuery<?> q, QCharacterFriend e) {
			}
		});
	}

	@Override
	public int insertObjects(CharacterFriend... friends) {
		return database
				.query(new InsertQuery<CharacterFriend, QCharacterFriend, Object>(
						QCharacterFriend.characterFriend, friends) {
					@Override
					protected void map(SQLInsertClause q, CharacterFriend o) {
						q.set(e.characterId, o.getCharacterID().getID()).set(
								e.characterIdFriend, o.getFriendID().getID());
					}
				});
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
					protected void query(SQLDeleteClause q, CharacterFriend o) {
						q.where(e.characterId.eq(
								o.getID().getCharacterID().getID()).and(
								e.characterIdFriend.eq(o.getID().getFriendID()
										.getID())));
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
