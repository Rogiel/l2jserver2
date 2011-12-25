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
import com.l2jserver.model.dao.CharacterShortcutDAO;
import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.id.CharacterShortcutID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.mapper.CharacterShortcutMapper;
import com.l2jserver.service.database.model.QCharacterShortcut;
import com.l2jserver.service.database.sql.AbstractSQLDAO;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.DeleteQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.InsertQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.UpdateQuery;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

/**
 * {@link CharacterFriendDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SQLCharacterShortcutDAO extends
		AbstractSQLDAO<CharacterShortcut, CharacterShortcutID> implements
		CharacterShortcutDAO {
	private final CharacterShortcutMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the {@link CharacterShortcut} mapper
	 */
	@Inject
	public SQLCharacterShortcutDAO(DatabaseService database,
			CharacterShortcutMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public CharacterShortcut select(final CharacterShortcutID id) {
		return database
				.query(new SelectSingleQuery<CharacterShortcut, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q,
							QCharacterShortcut e) {
						q.where(e.shortcutId.eq(id.getID()));
					}
				});
	}

	@Override
	public List<CharacterShortcut> selectByCharacter(final L2Character character) {
		return database
				.query(new SelectListQuery<CharacterShortcut, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q,
							QCharacterShortcut e) {
						q.where(e.characterId.eq(character.getID().getID()));
					}
				});
	}

	@Override
	public List<CharacterShortcutID> selectIDs() {
		return database
				.query(new SelectListQuery<CharacterShortcutID, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, mapper
								.getIDMapper()) {
					@Override
					protected void query(AbstractSQLQuery<?> q,
							QCharacterShortcut e) {
					}
				});
	}

	@Override
	public int insertObjects(CharacterShortcut... shortcuts) {
		return database
				.query(new InsertQuery<CharacterShortcut, QCharacterShortcut, Integer>(
						QCharacterShortcut.characterShortcut,
						QCharacterShortcut.characterShortcut.shortcutId,
						shortcuts) {
					@Override
					protected void map(SQLInsertClause q, CharacterShortcut o) {
						q.set(e.characterId, o.getID().getID())
								.set(e.type, o.getType())
								.set(e.objectId, o.getItemID().getID())
								.set(e.slot, o.getSlot())
								.set(e.page, o.getPage());
					}

					@Override
					protected void key(Integer k, CharacterShortcut o) {
						// TODO
					}
				});
	}

	@Override
	public int updateObjects(CharacterShortcut... shortcuts) {
		return database
				.query(new UpdateQuery<CharacterShortcut, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, shortcuts) {
					@Override
					protected void query(SQLUpdateClause q, CharacterShortcut o) {
						q.where(e.shortcutId.eq(o.getID().getID()));
					}

					@Override
					protected void map(SQLUpdateClause q, CharacterShortcut o) {
						q.set(e.characterId, o.getID().getID())
								.set(e.type, o.getType())
								.set(e.objectId, o.getItemID().getID())
								.set(e.slot, o.getSlot())
								.set(e.page, o.getPage());
					}
				});
	}

	@Override
	public int deleteObjects(CharacterShortcut... shortcuts) {
		return database
				.query(new DeleteQuery<CharacterShortcut, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, shortcuts) {
					@Override
					protected void query(SQLDeleteClause q, CharacterShortcut o) {
						q.where(e.shortcutId.eq(o.getID().getID()));
					}
				});
	}

	@Override
	protected CharacterShortcut[] wrap(Model<?>... objects) {
		final CharacterShortcut[] array = new CharacterShortcut[objects.length];
		int i = 0;
		for (final Model<?> object : objects) {
			array[i++] = (CharacterShortcut) object;
		}
		return array;
	}
}
