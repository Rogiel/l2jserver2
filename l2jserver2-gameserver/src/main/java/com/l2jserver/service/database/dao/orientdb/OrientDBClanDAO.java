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

import java.util.Collection;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.ClanDAO;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.world.Clan;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.mapper.ClanMapper;
import com.l2jserver.service.database.model.QClan;
import com.l2jserver.service.database.orientdb.AbstractOrientDBDAO;
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
public class OrientDBClanDAO extends AbstractOrientDBDAO<Clan, ClanID> implements ClanDAO {
	private final ClanMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the mapper
	 */
	@Inject
	public OrientDBClanDAO(DatabaseService database, final ClanMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public Clan select(final ClanID id) {
		return database
				.query(new SelectSingleQuery<Clan, Integer, ClanID, QClan>(
						QClan.clan, mapper) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QClan e) {
						return record.field(name(e.clanId)).eq(id.getID());
					}
				});
	}

	@Override
	public Collection<ClanID> selectIDs() {
		return database
				.query(new SelectListQuery<ClanID, Integer, ClanID, QClan>(
						QClan.clan, mapper.getIDMapper(QClan.clan)) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, QClan e) {
						return record;
					}
				});
	}

	@Override
	public int insertObjects(Clan... objects) {
		return database.query(new InsertQuery<Clan, Integer, ClanID, QClan>(
				QClan.clan, mapper, QClan.clan.clanId, objects));
	}

	@Override
	public int updateObjects(Clan... objects) {
		return database.query(new UpdateQuery<Clan, QClan>(QClan.clan, mapper,
				objects) {
			@Override
			protected OQueryContextNative query(OQueryContextNative record,
					Clan o) {
				return record.field(name(e.clanId)).eq(o.getID().getID());
			}
		});
	}

	@Override
	public int deleteObjects(Clan... objects) {
		return database
				.query(new DeleteQuery<Clan, QClan>(QClan.clan, objects) {
					@Override
					protected OQueryContextNative query(
							OQueryContextNative record, Clan o) {
						return record.field(name(e.clanId)).eq(o.getID().getID());
					}
				});
	}

	@Override
	protected Clan[] wrap(Model<?>... objects) {
		final Clan[] array = new Clan[objects.length];
		int i = 0;
		for (final Model<?> object : objects) {
			array[i++] = (Clan) object;
		}
		return array;
	}
}
