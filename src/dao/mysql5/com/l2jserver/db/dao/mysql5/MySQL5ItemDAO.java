package com.l2jserver.db.dao.mysql5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.db.dao.ItemDAO;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.factory.CharacterIDFactory;
import com.l2jserver.model.id.object.factory.ItemIDFactory;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterInventory;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService.Mapper;
import com.l2jserver.service.database.MySQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.MySQLDatabaseService.SelectSingleQuery;

public class MySQL5ItemDAO extends AbstractMySQL5DAO<Item> implements ItemDAO {
	private final ItemIDFactory idFactory;
	private final ItemTemplateIDFactory templateIdFactory;
	private final CharacterIDFactory charIdFactory;

	/**
	 * Character table name
	 */
	public static final String TABLE = "item";
	// FIELDS
	public static final String ITEM_ID = "item_id";
	public static final String TEMPLATE_ID = "template_id";
	public static final String CHAR_ID = MySQL5CharacterDAO.CHAR_ID;

	@Inject
	public MySQL5ItemDAO(DatabaseService database,
			final ItemIDFactory idFactory,
			ItemTemplateIDFactory templateIdFactory,
			CharacterIDFactory charIdFactory) {
		super(database);
		this.idFactory = idFactory;
		this.templateIdFactory = templateIdFactory;
		this.charIdFactory = charIdFactory;
	}

	private final class CharacterMapper implements Mapper<Item> {
		@Override
		public Item map(ResultSet rs) throws SQLException {
			final ItemTemplateID templateId = templateIdFactory.createID(rs
					.getInt(TEMPLATE_ID));
			final ItemTemplate template = templateId.getTemplate();
			final Item item = template.create();

			item.setID(idFactory.createID(rs.getInt(ITEM_ID)));
			item.setOwnerID(charIdFactory.createID(rs.getInt(CHAR_ID)));

			return item;
		}
	}

	@Override
	public Item load(final ItemID id) {
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
				return new CharacterMapper();
			}
		});
	}

	@Override
	public int loadInventory(final L2Character character) {
		final CharacterInventory inventory = character.getInventory();
		final List<Item> items = database.query(new SelectListQuery<Item>() {
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
			protected Mapper<Item> mapper() {
				return new CharacterMapper();
			}
		});
		inventory.load(items);
		return items.size();
	}

	@Override
	public List<ItemID> listIDs() {
		return database.query(new SelectListQuery<ItemID>() {
			@Override
			protected String query() {
				return "SELECT `" + CHAR_ID + "` FROM `" + TABLE + "`";
			}

			@Override
			protected Mapper<ItemID> mapper() {
				return new Mapper<ItemID>() {
					@Override
					public ItemID map(ResultSet rs) throws SQLException {
						return idFactory.createID(rs.getInt(CHAR_ID));
					}
				};
			}
		});
	}

	@Override
	public boolean save(Item item) {
		throw new UnsupportedOperationException(
				"Saving items is not yet implemented!");
	}
}
