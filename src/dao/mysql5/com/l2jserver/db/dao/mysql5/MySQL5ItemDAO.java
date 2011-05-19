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
import com.l2jserver.db.dao.ItemDAO;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterInventory;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService.CachedMapper;
import com.l2jserver.service.database.MySQLDatabaseService.Mapper;
import com.l2jserver.service.database.MySQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.MySQLDatabaseService.SelectSingleQuery;

/**
 * {@link ItemDAO} implementation for MySQL5
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MySQL5ItemDAO extends AbstractMySQL5DAO<Item> implements ItemDAO {
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
	public static final String CHAR_ID = MySQL5CharacterDAO.CHAR_ID;

	@Inject
	public MySQL5ItemDAO(DatabaseService database,
			final ItemIDProvider idFactory,
			ItemTemplateIDProvider templateIdFactory,
			CharacterIDProvider charIdFactory) {
		super(database);
		this.idFactory = idFactory;
		this.templateIdFactory = templateIdFactory;
		this.charIdFactory = charIdFactory;
	}

	/**
	 * The {@link Mapper} instance
	 */
	private final Mapper<Item> mapper = new CachedMapper<Item, ItemID>(database) {
		@Override
		protected ItemID createID(ResultSet rs) throws SQLException {
			return idFactory.createID(rs.getInt(ITEM_ID));
		}

		@Override
		public Item map(ItemID id, ResultSet rs) throws SQLException {
			final ItemTemplateID templateId = templateIdFactory.createID(rs
					.getInt(TEMPLATE_ID));
			final ItemTemplate template = templateId.getTemplate();
			final Item item = template.create();

			item.setID(id);
			item.setOwnerID(charIdFactory.createID(rs.getInt(CHAR_ID)));

			return item;
		}
	};

	/**
	 * The {@link Mapper} for {@link ItemID}
	 */
	private final Mapper<ItemID> idMapper = new Mapper<ItemID>() {
		@Override
		public ItemID map(ResultSet rs) throws SQLException {
			return idFactory.createID(rs.getInt(CHAR_ID));
		}
	};

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
				return mapper;
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
				return mapper;
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
				return idMapper;
			}
		});
	}

	@Override
	public boolean save(Item item) {
		throw new UnsupportedOperationException(
				"Saving items is not yet implemented!");
	}
}
