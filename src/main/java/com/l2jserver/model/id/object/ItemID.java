package com.l2jserver.model.id.object;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.db.dao.ItemDAO;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.Item;

public final class ItemID extends ObjectID<Item> {
	/**
	 * Data Access Object (DAO) for items
	 */
	private final ItemDAO itemDao;

	@Inject
	protected ItemID(@Assisted int id, ItemDAO itemDao) {
		super(id);
		this.itemDao = itemDao;
	}

	@Override
	public Item getObject() {
		return itemDao.load(this);
	}
}
