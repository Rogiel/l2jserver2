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

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.service.Service;
import com.l2jserver.service.database.DatabaseService;

/**
 * This service manages all {@link ObjectID} related tasks. Please note that
 * this service must be started right after the {@link DatabaseService} has
 * started, since the database depends on {@link ObjectID} but the
 * {@link IDAllocator} depends on database data.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WorldIDService extends Service {
	/**
	 * Load all {@link ObjectID} from the database
	 */
	void load();

	/**
	 * Unload all loaded {@link ObjectID}
	 */
	void unload();

	/**
	 * Tries to resolve an ID based on its raw value
	 * 
	 * @param <I>
	 *            the ID type
	 * @param id
	 *            the id raw value
	 * @return the id located
	 */
	<I extends ObjectID<?>> I resolve(int id);

	/**
	 * Adds a new ID to be managed by this service
	 * 
	 * @param <I>
	 *            the ID type
	 * @param id
	 *            the id
	 */
	<I extends ObjectID<?>> void add(I id);

	/**
	 * Decouples an ID from this service
	 * 
	 * @param <I>
	 *            the ID type
	 * @param id
	 *            the id
	 */
	<I extends ObjectID<?>> void remove(I id);
}
