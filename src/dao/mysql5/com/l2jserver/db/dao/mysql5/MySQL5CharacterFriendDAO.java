/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.db.dao.mysql5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterFriendDAO;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterFriendList;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.MySQLDatabaseService.Mapper;
import com.l2jserver.service.database.MySQLDatabaseService.SelectListQuery;

/**
 * {@link CharacterFriendDAO} implementation for MySQL5
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MySQL5CharacterFriendDAO extends AbstractMySQL5DAO<CharacterID>
		implements CharacterFriendDAO {
	/**
	 * The {@link CharacterID} factory
	 */
	private final CharacterIDProvider idFactory;

	/**
	 * Character table name
	 */
	public static final String TABLE = "character_friend";
	// FIELDS
	public static final String CHAR_ID = MySQL5CharacterDAO.CHAR_ID;
	public static final String CHAR_ID_FRIEND = MySQL5CharacterDAO.CHAR_ID
			+ "_friend";

	@Inject
	public MySQL5CharacterFriendDAO(DatabaseService database,
			final CharacterIDProvider idFactory) {
		super(database);
		this.idFactory = idFactory;
	}

	/**
	 * The {@link Mapper} for {@link CharacterID}
	 */
	private final Mapper<CharacterID> mapper = new Mapper<CharacterID>() {
		@Override
		public CharacterID map(ResultSet rs) throws SQLException {
			return idFactory.createID(rs.getInt(CHAR_ID_FRIEND));
		}
	};

	@Override
	public List<CharacterID> load(final CharacterID id) {
		return database.query(new SelectListQuery<CharacterID>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + CHAR_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, id.getID());
			}

			@Override
			protected Mapper<CharacterID> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public void load(L2Character character) {
		final List<CharacterID> list = load(character.getID());
		character.getFriendList().load(list);
	}

	@Override
	public boolean save(final CharacterFriendList friends) {
		return database.query(new InsertUpdateQuery<CharacterID>(friends
				.idIterator()) {
			@Override
			protected String query() {
				return "REPLACE INTO `" + TABLE + "` (`" + CHAR_ID + "`,`"
						+ CHAR_ID_FRIEND + "`) VALUES(?,?)";
			}

			@Override
			protected void parametize(PreparedStatement st, CharacterID id)
					throws SQLException {
				st.setInt(1, friends.getCharacter().getID().getID());
				st.setInt(2, id.getID());
			}
		}) > 0;
	}

	@Override
	public boolean delete(final CharacterFriendList friends) {
		return database.query(new InsertUpdateQuery<CharacterFriendList>(
				friends) {
			@Override
			protected String query() {
				return "DELETE FROM `" + TABLE + "` WHERE `" + CHAR_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st,
					CharacterFriendList friends) throws SQLException {
				st.setInt(1, friends.getCharacter().getID().getID());
			}
		}) > 0;
	}

	@Override
	public boolean delete(final CharacterID character, final CharacterID friend) {
		return database.query(new InsertUpdateQuery<Object>((Object) null) {
			@Override
			protected String query() {
				return "DELETE FROM `" + TABLE + "` WHERE `" + CHAR_ID
						+ "` = ?, `" + CHAR_ID_FRIEND + "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st, Object friends)
					throws SQLException {
				st.setInt(1, character.getID());
				st.setInt(2, friend.getID());
			}
		}) == 1;
	}
}
