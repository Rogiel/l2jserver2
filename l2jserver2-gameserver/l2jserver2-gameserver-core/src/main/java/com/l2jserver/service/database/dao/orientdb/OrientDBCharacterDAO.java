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
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.mapper.CharacterMapper;
import com.l2jserver.service.database.model.QCharacter;
import com.l2jserver.service.database.orientdb.AbstractOrientDBDAO;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.CountQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.DeleteQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.InsertQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.SelectListQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.UpdateQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNative;

/**
 * {@link CharacterDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class OrientDBCharacterDAO extends
		AbstractOrientDBDAO<L2Character, CharacterID> implements CharacterDAO {
	/**
	 * The {@link L2Character} mapper
	 */
	private final CharacterMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the character mapper
	 */
	@Inject
	public OrientDBCharacterDAO(DatabaseService database, CharacterMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public L2Character select(final CharacterID id) {
		return database
				.query(new SelectSingleQuery<L2Character, Integer, CharacterID, QCharacter>(
						QCharacter.character, mapper) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacter e) {
						return record.field(name(entity.characterId)).eq(
								id.getID());
					}
				});
	}

	@Override
	public void load(final Clan clan) {
		clan.getMembers()
				.load(database
						.query(new SelectListQuery<CharacterID, Integer, CharacterID, QCharacter>(
								QCharacter.character, mapper
										.getIDMapper(QCharacter.character)) {
							@Override
							protected OQueryContextNative query(
									OQueryContextNative record, QCharacter e) {
								return record.field(name(entity.clanId)).eq(
										clan.getID().getID());
							}
						}));
	}

	@Override
	public L2Character selectByName(final String name) {
		return database
				.query(new SelectSingleQuery<L2Character, Integer, CharacterID, QCharacter>(
						QCharacter.character, mapper) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacter e) {
						return record.field(name(entity.name)).eq(name);
					}
				});
	}

	@Override
	public List<L2Character> selectByAccount(final AccountID account) {
		return database
				.query(new SelectListQuery<L2Character, Integer, CharacterID, QCharacter>(
						QCharacter.character, mapper) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacter e) {
						return record.field(name(entity.accountId)).eq(
								account.getID());
					}
				});
	}

	@Override
	public int countByAccount(final AccountID account) {
		return database.query(new CountQuery<QCharacter>(QCharacter.character) {
			@Override
			protected OQueryContextNative query(OQueryContextNative record,
					QCharacter e) {
				return record.field(name(e.accountId)).eq(account.getID());
			}
		});
	}

	@Override
	public List<CharacterID> selectIDs() {
		return database
				.query(new SelectListQuery<CharacterID, Integer, CharacterID, QCharacter>(
						QCharacter.character, mapper
								.getIDMapper(QCharacter.character)) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QCharacter e) {
						return null;
					}
				});
	}

	@Override
	public int insertObjects(L2Character... characters) {
		return database
				.query(new InsertQuery<L2Character, Integer, CharacterID, QCharacter>(
						QCharacter.character, mapper, characters));
	}

	@Override
	public int updateObjects(L2Character... characters) {
		return database.query(new UpdateQuery<L2Character, QCharacter>(
				QCharacter.character, mapper, characters) {
			@Override
			protected OQueryContextNative query(OQueryContextNative record,
					L2Character o) {
				return record.field(name(entity.characterId)).eq(
						o.getID().getID());
			}
		});
	}

	@Override
	public int deleteObjects(L2Character... characters) {
		return database.query(new DeleteQuery<L2Character, QCharacter>(
				QCharacter.character, characters) {
			@Override
			protected OQueryContextNative query(OQueryContextNative record,
					L2Character o) {
				return record.field(name(entity.characterId)).eq(
						o.getID().getID());
			}
		});
	}

	@Override
	protected L2Character[] wrap(Model<?>... objects) {
		final L2Character[] array = new L2Character[objects.length];
		int i = 0;
		for (final Model<?> object : objects) {
			array[i++] = (L2Character) object;
		}
		return array;
	}
}
