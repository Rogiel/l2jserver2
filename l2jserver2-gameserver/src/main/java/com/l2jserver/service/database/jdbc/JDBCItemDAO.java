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
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.template.item.ItemTemplate;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.CachedMapper;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.DeleteQuery;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.Mapper;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.SelectListQuery;
import com.l2jserver.service.database.AbstractJDBCDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.util.geometry.Coordinate;

/**
 * {@link ItemDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class JDBCItemDAO extends AbstractJDBCDAO<Item, ItemID>
		implements ItemDAO {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link ItemID} factory
	 */
	private final ItemIDProvider idFactory;
	/**
	 * The {@link ItemTemplateID} factory
	 */
	private final ItemTemplateIDProvider templateIdFactory;
	/**
	 * The {@link CharacterID} factory
	 */
	private final CharacterIDProvider charIdFactory;

	/**
	 * Character table name
	 */
	public static final String TABLE = "item";
	// FIELDS
	public static final String ITEM_ID = "item_id";
	public static final String TEMPLATE_ID = "template_id";
	public static final String CHAR_ID = JDBCCharacterDAO.CHAR_ID;

	public static final String LOCATION = "location";
	public static final String PAPERDOLL = "paperdoll";
	public static final String COUNT = "count";
	public static final String COORD_X = "coord_x";
	public static final String COORD_Y = "coord_y";
	public static final String COORD_Z = "coord_z";

	/**
	 * @param database
	 *            the database service
	 * @param idFactory
	 *            the item id provider
	 * @param templateIdFactory
	 *            the item template id provider
	 * @param charIdFactory
	 *            the character id provider
	 */
	@Inject
	public JDBCItemDAO(DatabaseService database,
			final ItemIDProvider idFactory,
			ItemTemplateIDProvider templateIdFactory,
			CharacterIDProvider charIdFactory) {
		super(database);
		this.idFactory = idFactory;
		this.templateIdFactory = templateIdFactory;
		this.charIdFactory = charIdFactory;
	}

	/**
	 * The {@link Mapper} for {@link ItemID}
	 */
	private final Mapper<ItemID> idMapper = new Mapper<ItemID>() {
		@Override
		public ItemID map(ResultSet rs) throws SQLException {
			return idFactory.resolveID(rs.getInt(ITEM_ID));
		}
	};

	/**
	 * The {@link Mapper} instance
	 */
	private final Mapper<Item> mapper = new CachedMapper<Item, ItemID>(
			database, idMapper) {
		@Override
		public Item map(ItemID id, ResultSet rs) throws SQLException {
			final ItemTemplateID templateId = templateIdFactory.resolveID(rs
					.getInt(TEMPLATE_ID));
			final ItemTemplate template = templateId.getTemplate();
			if (template == null) {
				log.warn("No template found for {} while loading {}",
						templateId, id);
				return null;
			}
			final Item item = template.create();

			item.setID(id);
			if (rs.getObject(CHAR_ID) != null)
				item.setOwnerID(charIdFactory.resolveID(rs.getInt(CHAR_ID)));
			if (rs.getObject(LOCATION) != null)
				item.setLocation(ItemLocation.valueOf(rs.getString(LOCATION)));
			if (rs.getObject(PAPERDOLL) != null)
				item.setPaperdoll(InventoryPaperdoll.valueOf(rs
						.getString(PAPERDOLL)));

			item.setCount(rs.getInt(COUNT));

			if (rs.getObject(COORD_X) != null && rs.getObject(COORD_Y) != null
					&& rs.getObject(COORD_Z) != null)
				item.setPosition(Coordinate.fromXYZ(rs.getInt(COORD_X),
						rs.getInt(COORD_Y), rs.getInt(COORD_Z)));

			return item;
		}
	};

	@Override
	public Item select(final ItemID id) {
		return database.query(new SelectSingleQuery<Item>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + ITEM_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, id.getID());
			}

			@Override
			protected Mapper<Item> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<Item> selectByCharacter(final L2Character character) {
		return database.query(new SelectListQuery<Item>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + CHAR_ID
						+ "` = ? AND `location` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, character.getID().getID());
				st.setString(2, ItemLocation.INVENTORY.name());
			}

			@Override
			protected Mapper<Item> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<Item> selectDroppedItems() {
		return database.query(new SelectListQuery<Item>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + LOCATION
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setString(1, ItemLocation.GROUND.name());
			}

			@Override
			protected Mapper<Item> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<ItemID> selectIDs() {
		return database.query(new SelectListQuery<ItemID>() {
			@Override
			protected String query() {
				return "SELECT `" + ITEM_ID + "` FROM `" + TABLE + "`";
			}

			@Override
			protected Mapper<ItemID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public int insertObjects(Item... items) {
		return database.query(new InsertUpdateQuery<Item>(items) {
			@Override
			protected String query() {
				return "INSERT INTO `" + TABLE + "` (`" + ITEM_ID + "`,`"
						+ TEMPLATE_ID + "`,`" + CHAR_ID + "`,`" + LOCATION
						+ "`,`" + PAPERDOLL + "`,`" + COUNT + "`,`" + COORD_X
						+ "`,`" + COORD_Y + "`,`" + COORD_Z
						+ "`) VALUES(?,?,?,?,?,?,?,?,?)";
			}

			@Override
			protected void parametize(PreparedStatement st, Item item)
					throws SQLException {
				int i = 1;

				st.setInt(i++, item.getID().getID());
				st.setInt(i++, item.getTemplateID().getID());
				if (item.getOwnerID() != null) {
					st.setInt(i++, item.getOwnerID().getID());
				} else {
					st.setNull(i++, Types.INTEGER);
				}
				st.setString(i++, item.getLocation().name());
				st.setString(i++, (item.getPaperdoll() != null ? item
						.getPaperdoll().name() : null));
				st.setLong(i++, item.getCount());
				if (item.getPoint() != null) {
					st.setInt(i++, item.getPoint().getX());
					st.setInt(i++, item.getPoint().getY());
					st.setInt(i++, item.getPoint().getZ());
				} else {
					st.setNull(i++, Types.INTEGER);
					st.setNull(i++, Types.INTEGER);
					st.setNull(i++, Types.INTEGER);
				}
			}
		});
	}

	@Override
	public int updateObjects(Item... items) {
		return database.query(new InsertUpdateQuery<Item>(items) {
			@Override
			protected String query() {
				return "UPDATE `" + TABLE + "` SET `" + CHAR_ID + "` = ?,`"
						+ LOCATION + "` = ?,`" + PAPERDOLL + "` = ?,`" + COUNT
						+ "` = ?,`" + COORD_X + "` = ?,`" + COORD_Y + "` = ?,`"
						+ COORD_Z + "` = ? WHERE `" + ITEM_ID + "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st, Item item)
					throws SQLException {
				int i = 1;

				// SET
				if (item.getOwnerID() != null) {
					st.setInt(i++, item.getOwnerID().getID());
				} else {
					st.setNull(i++, Types.INTEGER);
				}
				st.setString(i++, item.getLocation().name());
				st.setString(i++, (item.getPaperdoll() != null ? item
						.getPaperdoll().name() : null));
				st.setLong(i++, item.getCount());
				if (item.getPoint() != null) {
					st.setInt(i++, item.getPoint().getX());
					st.setInt(i++, item.getPoint().getY());
					st.setInt(i++, item.getPoint().getZ());
				} else {
					st.setNull(i++, Types.INTEGER);
					st.setNull(i++, Types.INTEGER);
					st.setNull(i++, Types.INTEGER);
				}

				// WHERE
				st.setInt(i++, item.getID().getID());
			}
		});
	}

	@Override
	public int deleteObjects(Item... items) {
		return database.query(new DeleteQuery<Item>(database, items) {
			@Override
			protected String query() {
				return "DELETE FROM `" + TABLE + "` WHERE `" + ITEM_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st, Item item)
					throws SQLException {
				st.setInt(1, item.getID().getID());
			}

			@Override
			protected void dispose(Item object) {
				idFactory.destroy(object.getID());
			}
		});
	}
	
	@Override
	protected Item[] wrap(Model<?>... objects) {
		final Item[] array = new Item[objects.length];
		int i = 0;
		for (final Model<?> object : objects) {
			array[i++] = (Item) object;
		}
		return array;
	}
}
