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
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.provider.IDProvider;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.database.DataAccessObject;
import com.l2jserver.service.game.world.WorldService;

/**
 * An {@link ObjectID} instance representing an {@link NPC} object. Since NPC
 * instances are stores in run-time only, the search is performed in the
 * {@link WorldService} instead of using a {@link DataAccessObject}.
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
	 * @param id
	 *            the raw id
	 * @param worldService
	 *            the world service
	 */
	@Inject
	public NPCID(@Assisted int id, WorldService worldService) {
		super(id);
		this.worldService = worldService;
	}

	@Override
	public NPC getObject() {
		return worldService.find(this);
	}
}
