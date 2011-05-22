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
package com.l2jserver.service.game.world.filter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * And filter that accepts all values that are not in <tt>objects</tt>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <O>
 *            the object type
 */
public class ExcludeFilter<O extends WorldObject> implements
		WorldObjectFilter<O> {
	/**
	 * The objects to be exluded
	 */
	private Set<WorldObject> objects = CollectionFactory.newSet();

	/**
	 * Creates a new instance
	 * 
	 * @param objects
	 *            the excluded objects
	 */
	public ExcludeFilter(WorldObject... objects) {
		Collections.addAll(this.objects, objects);
	}

	/**
	 * Creates a new instance
	 * 
	 * @param objects
	 *            the excluded objects
	 */
	public ExcludeFilter(Collection<WorldObject> objects) {
		this.objects.addAll(objects);
	}

	@Override
	public boolean accept(O object) {
		return objects.contains(object);
	}
}
