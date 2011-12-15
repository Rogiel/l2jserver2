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
import com.l2jserver.game.net.packet.client.CM_CHAR_ACTION.CharacterAction;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;
import com.l2jserver.model.world.item.ItemPickUpEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.util.geometry.Point3D;

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
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;

	/**
	 * All items on the ground persisted to the database
	 */
	private List<Item> items;

	/**
	 * @param itemDao
	 *            the item DAO
	 * @param spawnService
	 *            the spawn service
	 * @param eventDispatcher
	 *            the world service event dispatcher
	 */
	@Inject
	private ItemServiceImpl(ItemDAO itemDao, SpawnService spawnService,
			WorldEventDispatcher eventDispatcher) {
		this.itemDao = itemDao;
		this.spawnService = spawnService;
		this.eventDispatcher = eventDispatcher;
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
	public Item action(Item item, L2Character character, CharacterAction action)
			throws ItemNotOnGroundServiceException, NotSpawnedServiceException {
		switch (action) {
		case CLICK:
			return pickUp(item, character);
		case RIGHT_CLICK:
			return item;
		}
		return item;
	}

	@Override
	public Item pickUp(Item item, L2Character character)
			throws ItemNotOnGroundServiceException, NotSpawnedServiceException {
		synchronized (item) {
			if (item.getLocation() != ItemLocation.GROUND)
				throw new ItemNotOnGroundServiceException();

			item.setLocation(ItemLocation.INVENTORY);
			item.setPaperdoll(null);
			item.setOwnerID(character.getID());
			character.getInventory().add(item);

			items.remove(item);
			
			spawnService.unspawn(item);
			eventDispatcher.dispatch(new ItemPickUpEvent(character, item));

			return item;
		}
	}

	@Override
	public void drop(Item item, Point3D point, Actor actor)
			throws SpawnPointNotFoundServiceException,
			ItemAlreadyOnGroundServiceException, AlreadySpawnedServiceException {
		synchronized (item) {
			if (item.getLocation() == ItemLocation.GROUND)
				throw new AlreadySpawnedServiceException();

			item.setLocation(ItemLocation.GROUND);
			item.setPaperdoll(null);
			// point will be set here
			spawnService.spawn(item, point);

			items.add(item);
		}
	}

	@Override
	protected void doStop() throws ServiceStopException {
		try {
			for (final Item item : items) {
				spawnService.unspawn(item);
			}
		} catch (NotSpawnedServiceException e) {
			throw new ServiceStopException("Item is not spawned anymore", e);
		}
	}
}
