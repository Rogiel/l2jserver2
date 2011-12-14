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
package com.l2jserver.service.game.item;

import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.world.Item;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ SpawnService.class, DatabaseService.class })
public class ItemServiceImpl extends AbstractService implements ItemService {
	/**
	 * The item DAO
	 */
	private final ItemDAO itemDao;
	/**
	 * The Spawn Service
	 */
	private final SpawnService spawnService;

	/**
	 * All items on the ground persisted to the database
	 */
	private List<Item> items;

	/**
	 * @param itemDao
	 *            the item DAO
	 * @param spawnService
	 *            the spawn service
	 */
	@Inject
	private ItemServiceImpl(ItemDAO itemDao, SpawnService spawnService) {
		this.itemDao = itemDao;
		this.spawnService = spawnService;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		items = itemDao.loadDroppedItems();
		try {
			for (final Item item : items) {
				spawnService.spawn(item, null);
			}
		} catch (SpawnPointNotFoundServiceException e) {
			throw new ServiceStartException(e);
		} catch (AlreadySpawnedServiceException e) {
			throw new ServiceStartException(e);
		}
	}

	@Override
	protected void doStop() throws ServiceStopException {
		super.doStop();
	}
}
