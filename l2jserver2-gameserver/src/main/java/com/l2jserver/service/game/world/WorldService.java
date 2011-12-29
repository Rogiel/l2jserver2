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
package com.l2jserver.service.game.world;

import java.util.Iterator;
import java.util.List;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.Service;
import com.l2jserver.service.game.world.event.WorldEventDispatcherService;
import com.l2jserver.service.game.world.filter.WorldObjectFilter;
import com.l2jserver.service.network.broadcast.BroadcastService;

/**
 * One of the most important services in the whole server. It is responsible for
 * keeping track of all {@link WorldObject WorldObjects} in the game virtual
 * world: positioning, status, life and attributes.
 * <p>
 * As an example, once a new {@link NPC} is spawned, it is registered within
 * this service and it can be broadcasted (using {@link BroadcastService}) to
 * all nearby clients (see {@link Lineage2Client}).
 * <h1>Other tasks</h1> World event dispatching is handled by
 * {@link WorldEventDispatcherService}.
 * <p>
 * {@link ObjectID} object management is done through {@link WorldIDService}
 * that can be used to cache those IDs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WorldService extends Service, Iterable<WorldObject> {
	/**
	 * Adds an object into the world.
	 * 
	 * @param object
	 *            the object
	 * @return true if object was not present in the world
	 */
	boolean add(WorldObject object);

	/**
	 * Removes an object of the world
	 * 
	 * @param object
	 *            the object
	 * @return true if object was present in the world
	 */
	boolean remove(WorldObject object);

	/**
	 * Check if this object is in the world.
	 * 
	 * @param object
	 *            the object
	 * @return true if object exists
	 */
	boolean contains(WorldObject object);

	/**
	 * Locates the object with the given <tt>id</tt>
	 * 
	 * @param <T>
	 *            the object type
	 * @param id
	 *            the object id
	 * @return the found object
	 * @throws ClassCastException
	 *             if object found is not an instance of <tt>T</tt>
	 */
	<T extends WorldObject> T find(ObjectID<T> id) throws ClassCastException;

	/**
	 * Executes an action inside the callback for each object in <tt>object</tt>
	 * known list.
	 * 
	 * @param object
	 *            the object
	 * @param callback
	 *            the callback
	 */
	void knownlist(PositionableObject object, KnownListCallback callback);

	/**
	 * The KnownList callback is used to execute an action for each object know
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface KnownListCallback {
		/**
		 * Performs an action on the given known <tt>object</tt>
		 * 
		 * @param object
		 *            the object known by the other object
		 */
		void knownObject(WorldObject object);
	}

	/**
	 * Creates a list of all objects matching <tt>filter</tt>
	 * 
	 * @param <T>
	 *            the object type
	 * @param filter
	 *            the filter
	 * @return the list of objects
	 */
	<T extends WorldObject> List<T> list(WorldObjectFilter<T> filter);

	/**
	 * Creates a list of all objects of type <tt>type</tt>
	 * 
	 * @param <T>
	 *            the type
	 * @param type
	 *            the type class
	 * @return the list of objects
	 */
	<T extends WorldObject> List<T> list(Class<T> type);

	/**
	 * Get the iterator for this <tt>filter</tt>
	 * 
	 * @param <T>
	 *            the object type
	 * @param filter
	 *            the filter
	 * @return the iterator instance
	 */
	<T extends WorldObject> Iterator<T> iterator(
			final WorldObjectFilter<T> filter);

	/**
	 * Shortcut method for {@link Iterable#iterator()} with filters. The
	 * iterable instance returns the same {@link Iterator} as
	 * {@link #iterator(WorldObjectFilter)}.
	 * 
	 * @param <T>
	 *            the object type
	 * @param filter
	 *            the filter
	 * @return the iterable instance
	 */
	<T extends WorldObject> Iterable<T> iterable(
			final WorldObjectFilter<T> filter);
}
