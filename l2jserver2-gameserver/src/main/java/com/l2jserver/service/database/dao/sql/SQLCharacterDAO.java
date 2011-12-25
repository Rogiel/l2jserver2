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
package com.l2jserver.service.database.dao.sql;

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
import com.l2jserver.service.database.sql.AbstractSQLDAO;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.DeleteQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.InsertQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.UpdateQuery;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

/**
 * {@link CharacterDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SQLCharacterDAO extends AbstractSQLDAO<L2Character, CharacterID>
		implements CharacterDAO {
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
	public SQLCharacterDAO(DatabaseService database, CharacterMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public L2Character select(final CharacterID id) {
		return database
				.query(new SelectSingleQuery<L2Character, Integer, CharacterID, QCharacter>(
						QCharacter.character, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QCharacter e) {
						q.where(e.characterId.eq(id.getID()));
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
							protected void query(AbstractSQLQuery<?> q,
									QCharacter e) {
								q.where(e.clanId.eq(clan.getID().getID()));
							}
						}));
	}

	@Override
	public L2Character selectByName(final String name) {
		return database
				.query(new SelectSingleQuery<L2Character, Integer, CharacterID, QCharacter>(
						QCharacter.character, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QCharacter e) {
						q.where(e.name.eq(name));
					}
				});
	}

	@Override
	public List<L2Character> selectByAccount(final AccountID account) {
		return database
				.query(new SelectListQuery<L2Character, Integer, CharacterID, QCharacter>(
						QCharacter.character, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QCharacter e) {
						q.where(e.accountId.eq(account.getID()));
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
					protected void query(AbstractSQLQuery<?> q, QCharacter e) {
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
			protected void query(SQLUpdateClause q, L2Character o) {
				q.where(e.characterId.eq(o.getID().getID()));
			}
		});
	}

	@Override
	public int deleteObjects(L2Character... characters) {
		return database.query(new DeleteQuery<L2Character, QCharacter>(
				QCharacter.character, characters) {
			@Override
			protected void query(SQLDeleteClause q, L2Character o) {
				q.where(e.characterId.eq(o.getID().getID()));
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
