package com.l2jserver.db.dao.mysql5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.factory.CharacterIDFactory;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.AbstractActor.Sex;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterAppearance;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.MySQLDatabaseService.Mapper;
import com.l2jserver.service.database.MySQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.MySQLDatabaseService.SelectSingleQuery;
import com.l2jserver.util.Coordinate;

public class MySQL5CharacterDAO extends AbstractMySQL5DAO<L2Character>
		implements CharacterDAO {
	private final CharacterIDFactory idFactory;
	private final CharacterTemplateIDFactory templateIdFactory;

	/**
	 * Character table name
	 */
	public static final String TABLE = "character";
	// FIELDS
	public static final String CHAR_ID = "character_id";
	public static final String ACCOUNT_ID = "account_id";
	public static final String NAME = "name";

	public static final String RACE = "race";
	public static final String CLASS = "class";
	public static final String SEX = "sex";

	public static final String LEVEL = "level";
	public static final String EXPERIENCE = "experience";
	public static final String SP = "sp";

	public static final String COORD_X = "position_x";
	public static final String COORD_Y = "position_y";
	public static final String COORD_Z = "position_z";

	public static final String APPEARANCE_HAIR_STYLE = "appearance_hair_style";
	public static final String APPEARANCE_HAIR_COLOR = "appearance_hair_color";
	public static final String APPEARANCE_FACE = "apperance_face";

	@Inject
	public MySQL5CharacterDAO(DatabaseService database,
			final CharacterIDFactory idFactory,
			CharacterTemplateIDFactory templateIdFactory) {
		super(database);
		this.idFactory = idFactory;
		this.templateIdFactory = templateIdFactory;
	}

	/**
	 * The {@link Mapper} instance
	 */
	private final CharacterMapper mapper = new CharacterMapper();

	private final class CharacterMapper implements Mapper<L2Character> {
		@Override
		public L2Character map(ResultSet rs) throws SQLException {
			final CharacterClass charClass = CharacterClass.valueOf(rs
					.getString(CLASS));
			final CharacterTemplateID templateId = templateIdFactory
					.createID(charClass.id);
			final CharacterTemplate template = templateId.getTemplate();

			final L2Character character = new L2Character(
					template.getBaseAttributes());
			character.setID(idFactory.createID(rs.getInt(CHAR_ID)));
			character.setName(rs.getString(NAME));

			character.setRace(Race.valueOf(rs.getString(RACE)));
			character.setCharacterClass(CharacterClass.valueOf(rs
					.getString(CLASS)));
			character.setSex(Sex.valueOf(rs.getString(SEX)));

			character.setLevel(rs.getInt(LEVEL));
			// TODO load experience
			// TODO load sp

			character.setPosition(Coordinate.fromXYZ(rs.getInt(COORD_X),
					rs.getInt(COORD_Y), rs.getInt(COORD_Z)));

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
	}

	@Override
	public L2Character load(final CharacterID id) {
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
	public List<L2Character> selectByAccount(final String username) {
		return database.query(new SelectListQuery<L2Character>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + ACCOUNT_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setString(1, username);
			}

			@Override
			protected Mapper<L2Character> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<CharacterID> listIDs() {
		return database.query(new SelectListQuery<CharacterID>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + CHAR_ID
						+ "` = ?";
			}

			@Override
			protected Mapper<CharacterID> mapper() {
				return new Mapper<CharacterID>() {
					@Override
					public CharacterID map(ResultSet rs) throws SQLException {
						return idFactory.createID(rs.getInt(CHAR_ID));
					}
				};
			}
		});
	}

	@Override
	public boolean save(L2Character character) {
		return database.query(new InsertUpdateQuery<L2Character>(character) {
			@Override
			protected String query() {
				return "INSERT INTO `" + TABLE + "` (`" + CHAR_ID + "`,`"
						+ NAME + "`,`" + RACE + "`,`" + CLASS + "`,`" + SEX
						+ "`,`" + LEVEL + "`,`" + COORD_X + "`,`" + COORD_Y
						+ "`,`" + COORD_Z + "`,`" + APPEARANCE_HAIR_STYLE
						+ "`,`" + APPEARANCE_HAIR_COLOR + "`,`"
						+ APPEARANCE_FACE
						+ "`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			}

			@Override
			protected void parametize(PreparedStatement st,
					L2Character character) throws SQLException {
				final CharacterAppearance appearance = character
						.getAppearance();
				int i = 1;

				st.setInt(i++, character.getID().getID());
				st.setString(i++, character.getName());

				st.setString(i++, character.getRace().name());
				st.setString(i++, character.getCharacterClass().name());
				st.setString(i++, character.getSex().name());

				st.setInt(i++, character.getLevel());
				st.setInt(i++, character.getPosition().getX());
				st.setInt(i++, character.getPosition().getY());
				st.setInt(i++, character.getPosition().getZ());

				// appearance
				st.setString(i++, appearance.getHairStyle().name());
				st.setString(i++, appearance.getHairColor().name());
				st.setString(i++, appearance.getFace().name());
			}
		});
	}
}
