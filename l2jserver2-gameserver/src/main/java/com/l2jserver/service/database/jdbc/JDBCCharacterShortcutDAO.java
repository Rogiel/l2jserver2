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
import com.l2jserver.model.dao.CharacterFriendDAO;
import com.l2jserver.model.dao.CharacterShortcutDAO;
import com.l2jserver.model.game.CharacterFriend;
import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.game.CharacterShortcut.ShortcutType;
import com.l2jserver.model.id.CharacterShortcutID;
import com.l2jserver.model.id.FriendID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.id.provider.CharacterShortcutIDProvider;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.CachedMapper;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.Mapper;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.SelectListQuery;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.DatabaseService;

/**
 * {@link CharacterFriendDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class JDBCCharacterShortcutDAO extends
		AbstractJDBCDAO<CharacterShortcut, CharacterShortcutID> implements
		CharacterShortcutDAO {
	/**
	 * The {@link CharacterShortcutID} provider
	 */
	private final CharacterShortcutIDProvider idProvider;
	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider charIdProvider;
	/**
	 * The {@link ItemID} provider
	 */
	private final ItemIDProvider itemIdProvider;

	/**
	 * Character table name
	 */
	public static final String TABLE = "character_shortcut";
	// FIELDS
	public static final String SHORTCUT_ID = "shortcut_id";
	public static final String CHAR_ID = JDBCCharacterDAO.CHAR_ID;
	public static final String TYPE = "type";
	public static final String SLOT = "slot";
	public static final String PAGE = "page";

	// item id, skill id (pretty much anything!)
	public static final String OBJECT_ID = "object_id";
	public static final String SKILL_LEVEL = "skill_level";

	/**
	 * @param database
	 *            the database service
	 * @param idProvider
	 *            the frind id provider
	 * @param charIdProvider
	 *            the character id provider
	 * @param itemIdProvider
	 *            the item id provider
	 */
	@Inject
	public JDBCCharacterShortcutDAO(DatabaseService database,
			final CharacterShortcutIDProvider idProvider,
			CharacterIDProvider charIdProvider, ItemIDProvider itemIdProvider) {
		super(database);
		this.idProvider = idProvider;
		this.charIdProvider = charIdProvider;
		this.itemIdProvider = itemIdProvider;
	}

	/**
	 * The {@link Mapper} for {@link FriendID}
	 */
	private final Mapper<CharacterShortcutID> idMapper = new Mapper<CharacterShortcutID>() {
		@Override
		public CharacterShortcutID map(ResultSet rs) throws SQLException {
			return idProvider.resolveID(rs.getInt(SHORTCUT_ID));
		}
	};

	/**
	 * The {@link Mapper} for {@link CharacterFriend}
	 */
	private final Mapper<CharacterShortcut> mapper = new CachedMapper<CharacterShortcut, CharacterShortcutID>(
			database, idMapper) {
		@Override
		protected CharacterShortcut map(CharacterShortcutID id, ResultSet rs)
				throws SQLException {
			final CharacterShortcut shortcut = new CharacterShortcut();
			shortcut.setID(id);
			final CharacterID charId = charIdProvider.resolveID(rs
					.getInt(CHAR_ID));
			shortcut.setCharacterID(charId);

			// resolve type
			final ShortcutType type = ShortcutType.valueOf(rs.getString(TYPE));
			shortcut.setType(type);
			switch (type) {
			case ITEM:
				final ItemID itemId = itemIdProvider.resolveID(rs
						.getInt(OBJECT_ID));
				shortcut.setItemID(itemId);
				break;
			}

			shortcut.setPage(rs.getInt(PAGE));
			shortcut.setSlot(rs.getInt(SLOT));

			return shortcut;
		}
	};

	@Override
	public CharacterShortcut select(final CharacterShortcutID id) {
		return database.query(new SelectSingleQuery<CharacterShortcut>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + SHORTCUT_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, id.getID());
			}

			@Override
			protected Mapper<CharacterShortcut> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<CharacterShortcut> selectByCharacter(final L2Character character) {
		return database.query(new SelectListQuery<CharacterShortcut>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + CHAR_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, character.getID().getID());
			}

			@Override
			protected Mapper<CharacterShortcut> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<CharacterShortcutID> selectIDs() {
		return database.query(new SelectListQuery<CharacterShortcutID>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "`";
			}

			@Override
			protected Mapper<CharacterShortcutID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public int insertObjects(CharacterShortcut... shortcuts) {
		return database.query(new InsertUpdateQuery<CharacterShortcut>(
				shortcuts) {
			@Override
			protected String query() {
				return "INSERT INTO `" + TABLE + "` (`" + CHAR_ID + "`,`"
						+ TYPE + "`, `" + OBJECT_ID + "`, `" + SLOT + "`, `"
						+ PAGE + "`) VALUES(?,?,?,?,?)";
			}

			@Override
			protected void parametize(PreparedStatement st,
					CharacterShortcut shortcut) throws SQLException {
				int i = 1;
				st.setInt(i++, shortcut.getCharacterID().getID());
				st.setString(i++, shortcut.getType().name());
				switch (shortcut.getType()) {
				case ITEM:
					st.setInt(i++, shortcut.getItemID().getID());
					break;
				}
				st.setInt(i++, shortcut.getSlot());
				st.setInt(i++, shortcut.getPage());
			}

			@Override
			protected Mapper<CharacterShortcutID> keyMapper() {
				return new Mapper<CharacterShortcutID>() {
					@Override
					public CharacterShortcutID map(ResultSet rs)
							throws SQLException {
						return idProvider.resolveID(rs.getInt(1));
					};
				};
			}
		});
	}

	@Override
	public int updateObjects(CharacterShortcut... shortcuts) {
		return database.query(new InsertUpdateQuery<CharacterShortcut>(
				shortcuts) {
			@Override
			protected String query() {
				return "UPDATE `" + TABLE + "` SET `" + CHAR_ID + "` = ?,`"
						+ TYPE + "` = ?, `" + OBJECT_ID + "` = ?, `" + SLOT
						+ "` = ?, `" + PAGE + "` = ? WHERE `" + SHORTCUT_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st,
					CharacterShortcut shortcut) throws SQLException {
				int i = 1;
				st.setInt(i++, shortcut.getCharacterID().getID());
				st.setString(i++, shortcut.getType().name());
				switch (shortcut.getType()) {
				case ITEM:
					st.setInt(i++, shortcut.getItemID().getID());
					break;
				}
				st.setInt(i++, shortcut.getSlot());
				st.setInt(i++, shortcut.getPage());

				st.setInt(i++, shortcut.getID().getID());
			}
		});
	}

	@Override
	public int deleteObjects(CharacterShortcut... shortcuts) {
		return database.query(new InsertUpdateQuery<CharacterShortcut>(
				shortcuts) {
			@Override
			protected String query() {
				return "DELETE FROM `" + TABLE + "` WHERE `" + SHORTCUT_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st,
					CharacterShortcut shortcut) throws SQLException {
				st.setInt(1, shortcut.getID().getID());
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
