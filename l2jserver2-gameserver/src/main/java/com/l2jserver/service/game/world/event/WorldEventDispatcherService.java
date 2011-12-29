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
package com.l2jserver.service.game.world.event;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.Service;

/**
 * This event dispatcher notify listeners that an certain event occured in their
 * objects.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WorldEventDispatcherService extends Service {
	/**
	 * Notify listeners of the <tt>event</tt>. Note that not all implementation
	 * need to invoke listeners immediately. Dispatching <b>can</b> occur
	 * concurrently.
	 * 
	 * @param <E>
	 *            the event type
	 * @param event
	 *            the event
	 * @return the future. The future can be used to be notified once the event
	 *         has been dispatched to all listeners.
	 */
	<E extends WorldEvent> WorldEventFuture<E> dispatch(E event);

	/**
	 * Adds a new global <tt>listener</tt>
	 * 
	 * @param listener
	 *            the listener
	 */
	void addListener(WorldListener listener);

	/**
	 * Adds a new <tt>listener</tt> to <tt>object</tt>
	 * 
	 * @param object
	 *            the object to listen to
	 * @param listener
	 *            the listener
	 */
	void addListener(WorldObject object, WorldListener listener);

	/**
	 * Adds a new <tt>listener</tt> to object with id <tt>id</tt>
	 * 
	 * @param id
	 *            the object id to listen to
	 * @param listener
	 *            the listener
	 */
	void addListener(ObjectID<?> id, WorldListener listener);

	/**
	 * Removes an existing global <tt>listener</tt>
	 * 
	 * @param listener
	 *            the listener
	 */
	void removeListener(WorldListener listener);

	/**
	 * Removes an existing <tt>listener</tt> from <tt>object</tt>
	 * 
	 * @param object
	 *            the object to listen to
	 * @param listener
	 *            the listener
	 */
	void removeListener(WorldObject object, WorldListener listener);

	/**
	 * Removes an existing <tt>listener</tt> from the object with id <tt>id</tt>
	 * 
	 * @param id
	 *            the object id to listen to
	 * @param listener
	 *            the listener
	 */
	void removeListener(ObjectID<?> id, WorldListener listener);
	
	/**
	 * Removes all listeners from a given object
	 * 
	 * @param id
	 *            the object id
	 */
	public void clear(ObjectID<?> id);
}
