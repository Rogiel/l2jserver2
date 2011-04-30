package script.dao.mysql5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.model.id.CharacterID;
import com.l2jserver.model.id.factory.CharacterIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService.Mapper;
import com.l2jserver.service.database.MySQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.MySQLDatabaseService.SelectSingleQuery;

public class MySQL5CharacterDAO extends AbstractMySQL5DAO<L2Character>
		implements CharacterDAO {
	private final CharacterIDFactory idFactory;

	/**
	 * Character table name
	 */
	public static final String TABLE = "character";
	// FIELDS
	public static final String CHAR_ID = "character_id";
	public static final String NAME = "name";

	@Inject
	public MySQL5CharacterDAO(DatabaseService database,
			final CharacterIDFactory idFactory) {
		super(database);
		this.idFactory = idFactory;
	}

	private final class CharacterMapper implements Mapper<L2Character> {
		@Override
		public L2Character map(ResultSet rs) throws SQLException {
			final L2Character character = new L2Character();
			character.setID(idFactory.createID(rs.getInt(CHAR_ID)));
			character.setName(rs.getString(NAME));
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
				return new CharacterMapper();
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
		throw new UnsupportedOperationException(
				"Saving characters is not yet implemented!");
	}
}
