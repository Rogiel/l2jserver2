package com.l2jserver.db.dao.mysql5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterFriendDAO;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.factory.CharacterIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterFriendList;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.MySQLDatabaseService.Mapper;
import com.l2jserver.service.database.MySQLDatabaseService.SelectListQuery;

public class MySQL5CharacterFriendDAO extends AbstractMySQL5DAO<CharacterID>
		implements CharacterFriendDAO {
	private final CharacterIDFactory idFactory;

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
			final CharacterIDFactory idFactory) {
		super(database);
		this.idFactory = idFactory;
	}

	/**
	 * The {@link Mapper} instance
	 */
	private final CharacterFriendMapper mapper = new CharacterFriendMapper();

	/**
	 * The friend list mapper
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private final class CharacterFriendMapper implements Mapper<CharacterID> {
		@Override
		public CharacterID map(ResultSet rs) throws SQLException {
			return idFactory.createID(rs.getInt(CHAR_ID_FRIEND));
		}
	}

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
