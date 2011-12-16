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
package com.l2jserver.service.database.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.ClanDAO;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ClanIDProvider;
import com.l2jserver.model.world.Clan;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.CachedMapper;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.Mapper;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.SelectListQuery;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.DatabaseService;

/**
 * {@link CharacterDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class JDBCClanDAO extends AbstractJDBCDAO<Clan, ClanID>
		implements ClanDAO {
	/**
	 * The {@link ClanID} factory
	 */
	private final ClanIDProvider idFactory;
	/**
	 * The {@link CharacterID} factory
	 */
	@SuppressWarnings("unused")
	private final CharacterIDProvider charIdFactory;

	/**
	 * Character table name
	 */
	public static final String TABLE = "clan";
	// FIELDS
	public static final String CLAN_ID = "clan_id";
	public static final String CHAR_ID_LEADER = "character_id_leader";

	/**
	 * @param database
	 *            the database service
	 * @param clanIdFactory
	 *            the clan id provider
	 * @param idFactory
	 *            the character id provider
	 */
	@Inject
	public JDBCClanDAO(DatabaseService database, ClanIDProvider clanIdFactory,
			final CharacterIDProvider idFactory) {
		super(database);
		this.idFactory = clanIdFactory;
		this.charIdFactory = idFactory;
	}

	/**
	 * The {@link Mapper} for {@link ClanID}
	 */
	private final Mapper<ClanID> idMapper = new Mapper<ClanID>() {
		@Override
		public ClanID map(ResultSet rs) throws SQLException {
			return idFactory.resolveID(rs.getInt(CLAN_ID));
		}
	};

	/**
	 * The {@link Mapper} for {@link Clan}
	 */
	private final Mapper<Clan> mapper = new CachedMapper<Clan, ClanID>(
			database, idMapper) {
		@Override
		protected Clan map(ClanID id, ResultSet rs) throws SQLException {
			final Clan clan = new Clan();
			clan.setID(id);
			return clan;
		}
	};

	@Override
	public Clan select(final ClanID id) {
		return database.query(new SelectSingleQuery<Clan>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + CLAN_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, id.getID());
			}

			@Override
			protected Mapper<Clan> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<ClanID> selectIDs() {
		return database.query(new SelectListQuery<ClanID>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "`";
			}

			@Override
			protected Mapper<ClanID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public int insertObjects(Clan... clans) {
		return database.query(new InsertUpdateQuery<Clan>(clans) {
			@Override
			protected String query() {
				return "INSERT INTO `" + TABLE + "` (`" + CLAN_ID
						+ "`) VALUES(?)";
			}

			@Override
			protected void parametize(PreparedStatement st, Clan clan)
					throws SQLException {
				st.setInt(1, clan.getID().getID());
			}
		});
	}

	@Override
	public int updateObjects(Clan... clans) {
		// TODO implement clan update
		return 0;
	}

	@Override
	public int deleteObjects(Clan... clans) {
		return database.query(new InsertUpdateQuery<Clan>(clans) {
			@Override
			protected String query() {
				return "DELETE FROM `" + TABLE + "` WHERE `" + CLAN_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st, Clan clan)
					throws SQLException {
				st.setInt(1, clan.getID().getID());
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
