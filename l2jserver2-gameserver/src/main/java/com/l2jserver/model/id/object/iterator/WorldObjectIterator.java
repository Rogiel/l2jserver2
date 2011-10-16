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
package com.l2jserver.model.id.object.iterator;

import java.util.Iterator;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.util.ArrayIterator;

/**
 * This {@link Iterator} will iterate trough another {@link Iterator} which
 * return {@link ObjectID} instances. For each ID, the
 * {@link ObjectID#getObject()} method will be called and its result will be
 * returned.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the object type
 */
public class WorldObjectIterator<T extends WorldObject> implements Iterator<T> {
	/**
	 * The {@link ObjectID} iterator
	 */
	private final Iterator<? extends ObjectID<T>> ids;

	/**
	 * Creates a new instance
	 * 
	 * @param ids
	 *            the {@link ObjectID} var-arg
	 */
	@SafeVarargs
	public WorldObjectIterator(ObjectID<T>... ids) {
		this(new ArrayIterator<ObjectID<T>>(ids));
	}

	/**
	 * Creates a new instance
	 * 
	 * @param ids
	 *            the {@link ObjectID} iterator
	 */
	public WorldObjectIterator(Iterator<? extends ObjectID<T>> ids) {
		this.ids = ids;
	}

	@Override
	public boolean hasNext() {
		return ids.hasNext();
	}

	@Override
	public T next() {
		return ids.next().getObject();
	}

	@Override
	public void remove() {
		ids.remove();
	}
}
