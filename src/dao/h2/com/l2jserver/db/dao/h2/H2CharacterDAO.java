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
package com.l2jserver.db.dao.h2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ClanIDProvider;
import com.l2jserver.model.id.provider.AccountIDProvider;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.world.Actor.ActorRace;
import com.l2jserver.model.world.Actor.ActorSex;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterAppearance;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.JDBCDatabaseService.CachedMapper;
import com.l2jserver.service.database.JDBCDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.JDBCDatabaseService.Mapper;
import com.l2jserver.service.database.JDBCDatabaseService.SelectListQuery;
import com.l2jserver.service.database.JDBCDatabaseService.SelectSingleQuery;
import com.l2jserver.util.dimensional.Point;

/**
 * {@link CharacterDAO} implementation for H2
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class H2CharacterDAO extends
		AbstractH2DAO<L2Character, CharacterID> implements CharacterDAO {
	/**
	 * The {@link CharacterID} factory
	 */
	private final CharacterIDProvider idFactory;
	/**
	 * The {@link CharacterTemplateID} factory
	 */
	private final CharacterTemplateIDProvider templateIdFactory;
	/**
	 * The {@link AccountID} factory
	 */
	private final AccountIDProvider accountIdFactory;
	/**
	 * The {@link ClanID} factory
	 */
	private final ClanIDProvider clanIdFactory;

	/**
	 * Character table name
	 */
	public static final String TABLE = "character";
	// FIELDS
	public static final String CHAR_ID = "character_id";
	public static final String ACCOUNT_ID = "account_id";
	public static final String CLAN_ID = "clan_id";
	public static final String NAME = "name";

	public static final String RACE = "race";
	public static final String CLASS = "class";
	public static final String SEX = "sex";

	public static final String LEVEL = "level";
	public static final String EXPERIENCE = "experience";
	public static final String SP = "sp";

	public static final String HP = "hp";
	public static final String MP = "mp";
	public static final String CP = "cp";

	public static final String POINT_X = "point_x";
	public static final String POINT_Y = "point_y";
	public static final String POINT_Z = "point_z";
	public static final String POINT_ANGLE = "point_angle";

	public static final String APPEARANCE_HAIR_STYLE = "appearance_hair_style";
	public static final String APPEARANCE_HAIR_COLOR = "appearance_hair_color";
	public static final String APPEARANCE_FACE = "apperance_face";

	@Inject
	public H2CharacterDAO(DatabaseService database,
			final CharacterIDProvider idFactory,
			CharacterTemplateIDProvider templateIdFactory,
			AccountIDProvider accountIdFactory, ClanIDProvider clanIdFactory) {
		super(database);
		this.idFactory = idFactory;
		this.templateIdFactory = templateIdFactory;
		this.accountIdFactory = accountIdFactory;
		this.clanIdFactory = clanIdFactory;
	}

	/**
	 * The mapper for {@link CharacterID}
	 */
	private final Mapper<CharacterID> idMapper = new Mapper<CharacterID>() {
		@Override
		public CharacterID map(ResultSet rs) throws SQLException {
			return idFactory.createID(rs.getInt(CHAR_ID));
		}
	};

	/**
	 * The {@link Mapper} for {@link L2Character}
	 */
	private final Mapper<L2Character> mapper = new CachedMapper<L2Character, CharacterID>(
			database, idMapper) {
		@Override
		protected L2Character map(CharacterID id, ResultSet rs)
				throws SQLException {
			final CharacterClass charClass = CharacterClass.valueOf(rs
					.getString(CLASS));
			final CharacterTemplateID templateId = templateIdFactory
					.createID(charClass.id);
			final CharacterTemplate template = templateId.getTemplate();

			final L2Character character = template.create();

			character.setID(id);
			character.setAccountID(accountIdFactory.createID(rs
					.getString(ACCOUNT_ID)));
			if (rs.getString(CLAN_ID) != null)
				character.setClanID(clanIdFactory.createID(rs.getInt(CLAN_ID)));

			character.setName(rs.getString(NAME));

			character.setRace(ActorRace.valueOf(rs.getString(RACE)));
			character.setCharacterClass(CharacterClass.valueOf(rs
					.getString(CLASS)));
			character.setSex(ActorSex.valueOf(rs.getString(SEX)));

			character.setLevel(rs.getInt(LEVEL));
			character.setExperience(rs.getLong(EXPERIENCE));
			character.setSP(rs.getInt(SP));

			character.setHP(rs.getDouble(HP));
			character.setMP(rs.getDouble(MP));
			character.setCP(rs.getDouble(CP));

			character.setPoint(Point.fromXYZA(rs.getInt(POINT_X),
					rs.getInt(POINT_Y), rs.getInt(POINT_Z),
					rs.getDouble(POINT_ANGLE)));

			// appearance
			character.getAppearance().setHairStyle(
					CharacterHairStyle.valueOf(rs
							.getString(APPEARANCE_HAIR_STYLE)));
			character.getAppearance().setHairColor(
					CharacterHairColor.valueOf(rs
							.getString(APPEARANCE_HAIR_COLOR)));
			character.getAppearance().setFace(
					CharacterFace.valueOf(rs.getString(APPEARANCE_FACE)));

			return character;
		}
	};

	@Override
	public L2Character select(final CharacterID id) {
		return database.query(new SelectSingleQuery<L2Character>() {
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
			protected Mapper<L2Character> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public void load(final Clan clan) {
		clan.getMembers().load(
				database.query(new SelectListQuery<CharacterID>() {
					@Override
					protected String query() {
						return "SELECT `" + CHAR_ID + "` FROM `" + TABLE
								+ "` WHERE `" + CLAN_ID + "` = ?";
					}

					@Override
					protected void parametize(PreparedStatement st)
							throws SQLException {
						st.setInt(1, clan.getID().getID());
					}

					@Override
					protected Mapper<CharacterID> mapper() {
						return idMapper;
					}
				}));
	}

	@Override
	public L2Character selectByName(final String name) {
		return database.query(new SelectSingleQuery<L2Character>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + NAME + "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setString(1, name);
			}

			@Override
			protected Mapper<L2Character> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<L2Character> selectByAccount(final AccountID account) {
		return database.query(new SelectListQuery<L2Character>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + ACCOUNT_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setString(1, account.getID());
			}

			@Override
			protected Mapper<L2Character> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<CharacterID> selectIDs() {
		return database.query(new SelectListQuery<CharacterID>() {
			@Override
			protected String query() {
				return "SELECT `" + CHAR_ID + "` FROM `" + TABLE + "`";
			}

			@Override
			protected Mapper<CharacterID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public boolean insert(L2Character character) {
		return database.query(new InsertUpdateQuery<L2Character>(character) {
			@Override
			protected String query() {
				return "INSERT INTO `" + TABLE + "` (`" + CHAR_ID + "`,`"
						+ ACCOUNT_ID + "`,`" + CLAN_ID + "`,`" + NAME + "`,`"
						+ RACE + "`,`" + CLASS + "`,`" + SEX + "`,`" + LEVEL
						+ "`,`" + EXPERIENCE + "`,`" + SP + "`,`" + HP + "`,`"
						+ MP + "`,`" + CP + "`,`" + POINT_X + "`,`" + POINT_Y
						+ "`,`" + POINT_Z + "`,`" + POINT_ANGLE + "`,`"
						+ APPEARANCE_HAIR_STYLE + "`,`" + APPEARANCE_HAIR_COLOR
						+ "`,`" + APPEARANCE_FACE
						+ "`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}

			@Override
			protected void parametize(PreparedStatement st,
					L2Character character) throws SQLException {
				final CharacterAppearance appearance = character
						.getAppearance();
				int i = 1;

				st.setInt(i++, character.getID().getID());
				st.setString(i++, character.getAccountID().getID());
				if (character.getClanID() != null)
					st.setInt(i++, character.getClanID().getID());
				else
					st.setNull(i++, Types.INTEGER);

				st.setString(i++, character.getName());

				st.setString(i++, character.getRace().name());
				st.setString(i++, character.getCharacterClass().name());
				st.setString(i++, character.getSex().name());

				st.setInt(i++, character.getLevel());
				st.setLong(i++, character.getExperience());
				st.setInt(i++, character.getSP());

				st.setDouble(i++, character.getHP());
				st.setDouble(i++, character.getMP());
				st.setDouble(i++, character.getCP());

				st.setInt(i++, character.getPoint().getX());
				st.setInt(i++, character.getPoint().getY());
				st.setInt(i++, character.getPoint().getZ());
				st.setDouble(i++, character.getPoint().getAngle());

				// appearance
				st.setString(i++, appearance.getHairStyle().name());
				st.setString(i++, appearance.getHairColor().name());
				st.setString(i++, appearance.getFace().name());
			}
		}) > 0;
	}

	@Override
	public boolean update(L2Character character) {
		return database.query(new InsertUpdateQuery<L2Character>(character) {
			@Override
			protected String query() {
				return "UPDATE `" + TABLE + "` SET " + ACCOUNT_ID + "` = ?,`"
						+ CLAN_ID + "` = ?,`" + NAME + "` = ?,`" + RACE
						+ "` = ?,`" + CLASS + "` = ?,`" + SEX + "` = ?,`"
						+ LEVEL + "` = ?,`" + EXPERIENCE + "` = ?,`" + SP
						+ "` = ?,`" + HP + "` = ?,`" + MP + "` = ?,`" + CP
						+ "` = ?,`" + POINT_X + "` = ?,`" + POINT_Y + "` = ?,`"
						+ POINT_Z + "` = ?,`" + POINT_ANGLE + "` = ?,`"
						+ APPEARANCE_HAIR_STYLE + "` = ?,`"
						+ APPEARANCE_HAIR_COLOR + "` = ?,`" + APPEARANCE_FACE
						+ "` = ? WHERE `" + CHAR_ID + "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st,
					L2Character character) throws SQLException {
				final CharacterAppearance appearance = character
						.getAppearance();
				int i = 1;

				// SET
				st.setString(i++, character.getAccountID().getID());
				if (character.getClanID() != null)
					st.setInt(i++, character.getClanID().getID());
				else
					st.setNull(i++, Types.INTEGER);

				st.setString(i++, character.getName());

				st.setString(i++, character.getRace().name());
				st.setString(i++, character.getCharacterClass().name());
				st.setString(i++, character.getSex().name());

				st.setInt(i++, character.getLevel());
				st.setLong(i++, character.getExperience());
				st.setInt(i++, character.getSP());

				st.setDouble(i++, character.getHP());
				st.setDouble(i++, character.getMP());
				st.setDouble(i++, character.getCP());

				// position
				st.setInt(i++, character.getPoint().getX());
				st.setInt(i++, character.getPoint().getY());
				st.setInt(i++, character.getPoint().getZ());
				st.setDouble(i++, character.getPoint().getAngle());

				// appearance
				st.setString(i++, appearance.getHairStyle().name());
				st.setString(i++, appearance.getHairColor().name());
				st.setString(i++, appearance.getFace().name());

				// WHERE
				st.setInt(i++, character.getID().getID());
			}
		}) > 0;
	}

	@Override
	public boolean delete(L2Character character) {
		return database.query(new InsertUpdateQuery<L2Character>(character) {
			@Override
			protected String query() {
				return "DELETE FROM `" + TABLE + "` WHERE `" + CHAR_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st,
					L2Character character) throws SQLException {
				st.setInt(1, character.getID().getID());
			}
		}) > 0;
	}
}
