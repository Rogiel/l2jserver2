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
package com.l2jserver.model.id.object;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.dao.NPCDAO;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.provider.IDProvider;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.database.DataAccessObject;
import com.l2jserver.service.game.world.WorldService;

/**
 * An {@link ObjectID} instance representing an {@link NPC} object. Since NPC
 * instances can be stored in run-time only, the search is performed first in
 * the {@link WorldService}, if no match is found, search is descended to an
 * {@link DataAccessObject}.
 * <p>
 * <h1>Search order</h1>
 * <ol>
 * <li>{@link WorldService}: for run-time only {@link NPC NPCs}, but will also
 * include all spawned {@link NPC NPCs};</li>
 * <li>{@link NPCDAO}: for persisted {@link NPC NPCs}. Only for {@link NPC NPCs}
 * that are not currently spawned in the world.</li>
 * </ol>
 * <p>
 * Please, do not directly instantiate this class, use an {@link IDProvider}
 * instead.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public final class NPCID extends ActorID<NPC> {
	/**
	 * {@link WorldService} used to locate objects
	 */
	private final WorldService worldService;
	/**
	 * {@link NPCDAO} used to locate objects if not located first by
	 * {@link WorldService}
	 */
	private final NPCDAO npcDao;

	/**
	 * @param id
	 *            the raw id
	 * @param worldService
	 *            the world service
	 * @param npcDao
	 *            the {@link NPC} {@link DataAccessObject DAO}
	 */
	@Inject
	public NPCID(@Assisted int id, WorldService worldService, NPCDAO npcDao) {
		super(id);
		this.worldService = worldService;
		this.npcDao = npcDao;
	}

	@Override
	public NPC getObject() {
		NPC npc = worldService.find(this);
		if (npc == null)
			npc = npcDao.select(this);
		return npc;
	}
}
