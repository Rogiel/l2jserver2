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
package com.l2jserver.service.game.world.event;

import com.google.common.base.Preconditions;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.game.world.filter.WorldObjectFilter;

/**
 * This listener will filter to only dispatch events on which the object matches
 * an given {@link WorldObjectFilter}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class FilteredWorldListener<T extends WorldObject> implements
		WorldListener {
	private final WorldObjectFilter<T> filter;

	public FilteredWorldListener(WorldObjectFilter<T> filter) {
		Preconditions.checkNotNull(filter, "filter");
		this.filter = filter;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean dispatch(WorldEvent e) {
		if (!filter.accept((T) e.getObject()))
			return false;
		return dispatch(e, (T) e.getObject());
	}

	/**
	 * @see WorldListener#dispatch(WorldEvent)
	 */
	protected abstract boolean dispatch(WorldEvent e, T object);
}
