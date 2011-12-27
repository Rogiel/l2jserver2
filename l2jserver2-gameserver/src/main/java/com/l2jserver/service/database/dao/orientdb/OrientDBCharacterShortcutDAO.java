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
import com.l2jserver.model.dao.CharacterShortcutDAO;
import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.id.CharacterShortcutID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.mapper.CharacterShortcutMapper;
import com.l2jserver.service.database.model.QCharacterShortcut;
import com.l2jserver.service.database.orientdb.AbstractOrientDBDAO;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.DeleteQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.InsertQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.SelectListQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.UpdateQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNative;

/**
 * {@link CharacterFriendDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class OrientDBCharacterShortcutDAO extends
		AbstractOrientDBDAO<CharacterShortcut, CharacterShortcutID> implements
		CharacterShortcutDAO {
	/**
	 * The {@link CharacterShortcut} mapper
	 */
	private final CharacterShortcutMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the {@link CharacterShortcut} mapper
	 */
	@Inject
	public OrientDBCharacterShortcutDAO(DatabaseService database,
			CharacterShortcutMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public CharacterShortcut select(final CharacterShortcutID id) {
		return database
				.query(new SelectSingleQuery<CharacterShortcut, Integer, CharacterShortcutID, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, mapper) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacterShortcut e) {
						return record.field(name(e.shortcutId)).eq(id.getID());
					}
				});
	}

	@Override
	public List<CharacterShortcut> selectByCharacter(final L2Character character) {
		return database
				.query(new SelectListQuery<CharacterShortcut, Integer, CharacterShortcutID, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, mapper) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacterShortcut e) {
						return record.field(name(e.characterId)).eq(
								character.getID().getID());
					}
				});
	}

	@Override
	public List<CharacterShortcutID> selectIDs() {
		return database
				.query(new SelectListQuery<CharacterShortcutID, Integer, CharacterShortcutID, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut,
						mapper.getIDMapper(QCharacterShortcut.characterShortcut)) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacterShortcut e) {
						return null;
					}
				});
	}

	@Override
	public int insertObjects(CharacterShortcut... shortcuts) {
		return database
				.query(new InsertQuery<CharacterShortcut, Integer, CharacterShortcutID, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, mapper,
						QCharacterShortcut.characterShortcut.shortcutId,
						shortcuts));
	}

	@Override
	public int updateObjects(CharacterShortcut... shortcuts) {
		return database
				.query(new UpdateQuery<CharacterShortcut, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, mapper, shortcuts) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, CharacterShortcut o) {
						return record.field(name(entity.shortcutId)).eq(
								o.getID().getID());
					}
				});
	}

	@Override
	public int deleteObjects(CharacterShortcut... shortcuts) {
		return database
				.query(new DeleteQuery<CharacterShortcut, QCharacterShortcut>(
						QCharacterShortcut.characterShortcut, shortcuts) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, CharacterShortcut o) {
						return record.field(name(entity.shortcutId)).eq(
								o.getID().getID());
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
