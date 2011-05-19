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

import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Default {@link WorldEventDispatcher} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class WorldEventDispatcherImpl implements WorldEventDispatcher {
	private static final Logger log = LoggerFactory
			.getLogger(WorldEventDispatcherImpl.class);

	private final Timer timer = new Timer();

	private Set<WorldListener> globalListeners = CollectionFactory.newSet();
	private Map<ObjectID<?>, Set<WorldListener>> listeners = CollectionFactory
			.newMap();
	// private Queue<ListenerIDPair> listeners = CollectionFactory
	// .newConcurrentQueue(ListenerIDPair.class);
	private Queue<WorldEvent> events = CollectionFactory.newConcurrentQueue();

	public WorldEventDispatcherImpl() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				final WorldEvent event = events.poll();
				if (event == null)
					return;
				try {
					doDispatch(event);
				} catch (Throwable t) {
					log.warn("Exception in WorldEventDispatcher thread", t);
				}
			}
		}, 0, 50);
	}

	@Override
	public void dispatch(WorldEvent event) {
		log.debug("Queing dispatch for event {}", event);
		events.add(event);
	}

	public void doDispatch(WorldEvent event) {
		log.debug("Dispatching event {}", event);
		final ObjectID<?>[] objects = event.getDispatchableObjects();
		for (ObjectID<?> obj : objects) {
			if (obj == null)
				continue;
			final Set<WorldListener> listeners = getListeners(obj);
			for (final WorldListener listener : listeners) {
				try {
					if (!listener.dispatch(event))
						// remove listener if return value is false
						listeners.remove(listener);
				} catch (ClassCastException e) {
					log.debug(
							"Exception in Listener. This might indicate an implementation issue in {}",
							listener.getClass());
					listeners.remove(listener);
				}
			}
		}
	}

	@Override
	public void addListener(WorldListener listener) {
		log.debug("Adding new listener global {}", listener);
		globalListeners.add(listener);
	}

	@Override
	public void addListener(WorldObject object, WorldListener listener) {
		addListener(object.getID(), listener);
	}

	@Override
	public void addListener(ObjectID<?> id, WorldListener listener) {
		log.debug("Adding new listener {} to {}", listener, id);
		getListeners(id).add(listener);
	}

	@Override
	public void removeListener(WorldListener listener) {
		globalListeners.remove(listener);
	}

	@Override
	public void removeListener(WorldObject object, WorldListener listener) {
		removeListener(object.getID(), listener);
	}

	@Override
	public void removeListener(ObjectID<?> id, WorldListener listener) {
		log.debug("Removing new listener {} from {}", listener, id);
		getListeners(id).remove(listener);
	}

	/**
	 * Removes all listeners from a given object
	 * 
	 * @param id
	 *            the object id
	 */
	public void clear(ObjectID<?> id) {
		listeners.remove(id);
	}

	private Set<WorldListener> getListeners(ObjectID<?> id) {
		Set<WorldListener> set = listeners.get(id);
		if (set == null) {
			set = CollectionFactory.newSet();
			listeners.put(id, set);
		}
		return set;
	}
}
