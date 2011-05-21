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
package com.l2jserver.service.game.world.filter.impl;

import com.google.common.base.Preconditions;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.game.world.filter.WorldObjectFilter;

/**
 * Filter objects based on its ID.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class IDFilter implements WorldObjectFilter<WorldObject> {
	/**
	 * The object id
	 */
	private final ObjectID<?> id;

	/**
	 * Creates a new instance
	 * 
	 * @param id
	 *            the desired object ID
	 */
	public IDFilter(final ObjectID<?> id) {
		Preconditions.checkNotNull(id, "id");
		this.id = id;
	}

	@Override
	public boolean accept(WorldObject other) {
		if (other == null)
			return false;
		return other.getID().equals(id);
	}
}
