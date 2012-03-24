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
package com.l2jserver.service.database.dao.sql;

import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.mapper.ItemMapper;
import com.l2jserver.service.database.model.QItem;
import com.l2jserver.service.database.sql.AbstractSQLDAO;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.DeleteQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.InsertQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.UpdateQuery;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

/**
 * {@link ItemDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SQLItemDAO extends AbstractSQLDAO<Item, ItemID> implements ItemDAO {
	/**
	 * The {@link Item} mapper
	 */
	private final ItemMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the mapper
	 */
	@Inject
	public SQLItemDAO(DatabaseService database, ItemMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public Item select(final ItemID id) {
		return database
				.query(new SelectSingleQuery<Item, Integer, ItemID, QItem>(
						QItem.item, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QItem e) {
						q.where(e.itemId.eq(id.getID()));
					}
				});
	}

	@Override
	public List<Item> selectByCharacter(final L2Character character) {
		return database
				.query(new SelectListQuery<Item, Integer, ItemID, QItem>(
						QItem.item, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QItem e) {
						q.where(e.characterId.eq(character.getID().getID()));
					}
				});
	}

	@Override
	public List<Item> selectDroppedItems() {
		return database
				.query(new SelectListQuery<Item, Integer, ItemID, QItem>(
						QItem.item, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QItem e) {
						q.where(e.location.eq(ItemLocation.GROUND));
					}
				});
	}

	@Override
	public Collection<ItemID> selectIDs() {
		return database
				.query(new SelectListQuery<ItemID, Integer, ItemID, QItem>(
						QItem.item, mapper.getIDMapper(QItem.item)) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QItem e) {
					}
				});
	}

	@Override
	public int insertObjects(Item... objects) {
		return database.query(new InsertQuery<Item, Integer, ItemID, QItem>(
				QItem.item, mapper, objects));
	}

	@Override
	public int updateObjects(Item... objects) {
		return database.query(new UpdateQuery<Item, QItem>(QItem.item, mapper,
				objects) {
			@Override
			protected void query(SQLUpdateClause q, Item o) {
				q.where(entity.itemId.eq(o.getID().getID()));
			}
		});
	}

	@Override
	public int deleteObjects(Item... objects) {
		return database
				.query(new DeleteQuery<Item, QItem>(QItem.item, objects) {
					@Override
					protected void query(SQLDeleteClause q, Item o) {
						q.where(entity.itemId.eq(o.getID().getID()));
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
