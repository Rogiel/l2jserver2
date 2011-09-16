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
package com.l2jserver.service.game.world;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.dao.NPCDAO;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.cache.Cache;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.database.DatabaseService;

/**
 * Implementation for {@link WorldIDService} that caches all {@link ID} objects
 * in memory.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ DatabaseService.class, CacheService.class })
public class CachedWorldIDService extends AbstractService implements
		WorldIDService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The cache service
	 */
	private final CacheService cacheService;
	/**
	 * The {@link IDAllocator}
	 */
	private final IDAllocator allocator;

	// DAOS
	/**
	 * The {@link CharacterDAO}
	 */
	private final CharacterDAO characterDao;
	/**
	 * The {@link ItemDAO}
	 */
	private final ItemDAO itemDao;
	/**
	 * The {@link NPCDAO}
	 */
	private final NPCDAO npcDao;

	/**
	 * The ID cache
	 */
	private Cache<Integer, ObjectID<?>> cache;

	/**
	 * The loaded state
	 */
	private boolean loaded = false;

	/**
	 * @param cacheService
	 *            the cache service
	 * @param allocator
	 *            the id allocator
	 * @param characterDao
	 *            the character DAO
	 * @param itemDao
	 *            the item dao
	 * @param npcDao
	 *            the NPC dao
	 */
	@Inject
	public CachedWorldIDService(CacheService cacheService,
			IDAllocator allocator, CharacterDAO characterDao, ItemDAO itemDao,
			NPCDAO npcDao) {
		this.cacheService = cacheService;
		this.allocator = allocator;
		this.characterDao = characterDao;
		this.itemDao = itemDao;
		this.npcDao = npcDao;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		// we allocate an cache which can fit all ids
		cache = cacheService.createEternalCache("id-cache",
				IDAllocator.ALLOCABLE_IDS);
	}

	@Override
	public void load() {
		log.debug("Loading IDs from database");

		load(characterDao.selectIDs());
		load(itemDao.selectIDs());
		load(npcDao.selectIDs());

		log.debug("IDs loaded from database");
		loaded = true;
	}

	@Override
	public void unload() {
		log.debug("Clearing load IDs");
		cache.clear();
	}

	/**
	 * Load the pre-existing ids
	 * 
	 * @param ids
	 *            an collection of ids
	 */
	private void load(Collection<? extends ObjectID<?>> ids) {
		Preconditions.checkNotNull(ids, "ids");
		log.debug("Loading {} IDs", ids.size());
		for (final ObjectID<?> id : ids) {
			log.debug("Loading {}", id);
			allocator.allocate(id.getID());
			add(id);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <I extends ObjectID<?>> I resolve(int id) {
		Preconditions.checkNotNull(id, "id");
		if (!loaded) {
			// ignore resolving before all IDs are loaded
			return null;
		}
		log.debug("Resolving {}", id);
		return (I) cache.get(id);
	}

	@Override
	public <I extends ObjectID<?>> void add(I id) {
		Preconditions.checkNotNull(id, "id");
		cache.put(id.getID(), id);
	}

	@Override
	public <I extends ObjectID<?>> void remove(I id) {
		Preconditions.checkNotNull(id, "id");
		cache.remove(id.getID());
	}

	@Override
	protected void doStop() throws ServiceStopException {
		cacheService.dispose(cache);
		cache = null;
		allocator.clear();
	}
}
