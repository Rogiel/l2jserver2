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

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.capability.Listenable;
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

	private Queue<ListenerIDPair> listeners = CollectionFactory
			.newConcurrentQueue(ListenerIDPair.class);
	private Queue<WorldEvent> events = CollectionFactory
			.newConcurrentQueue(WorldEvent.class);

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
		final Listenable<?, ?>[] objects = event.getDispatchableObjects();
		for (final ListenerIDPair pair : listeners) {
			for (Listenable<?, ?> obj : objects) {
				if (obj == null)
					continue;
				if (!pair.testDispatch(obj.getID()))
					continue;
				try {
					if (pair.dispatch(event))
						continue;
				} catch (ClassCastException e) {
					log.debug(
							"Exception in Listener. This might indicate an implementation issue in {}",
							pair.listener.getClass());
				}
				listeners.remove(pair);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends WorldEvent, L extends WorldListener<E>> void addListener(
			Listenable<L, E> object, WorldListener<E> listener) {
		log.debug("Adding new listener {} to {}", listener, object.getID());
		listeners.add(new ListenerIDPair(object.getID(),
				(WorldListener<WorldEvent>) listener));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends WorldEvent, L extends WorldListener<E>> void addListener(
			ObjectID<? extends Listenable<L, E>> id, WorldListener<E> listener) {
		log.debug("Adding new listener {} to {}", listener, id);
		listeners.add(new ListenerIDPair(id,
				(WorldListener<WorldEvent>) listener));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends WorldEvent, L extends WorldListener<E>> void removeListener(
			Listenable<L, E> object, WorldListener<E> listener) {
		log.debug("Removing new listener {} from {}", listener, object.getID());
		listeners.remove(new ListenerIDPair(object.getID(),
				(WorldListener<WorldEvent>) listener));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends WorldEvent, L extends WorldListener<E>> void removeListener(
			ObjectID<? extends Listenable<L, E>> id, WorldListener<E> listener) {
		log.debug("Removing new listener {} from {}", listener, id);
		listeners.remove(new ListenerIDPair(id,
				(WorldListener<WorldEvent>) listener));
	}

	private class ListenerIDPair {
		private ObjectID<?> ID;
		private WorldListener<WorldEvent> listener;

		public ListenerIDPair(ObjectID<?> ID, WorldListener<WorldEvent> listener) {
			super();
			this.ID = ID;
			this.listener = listener;
		}

		public boolean testDispatch(ObjectID<?> id) {
			return id.equals(this.ID);
		}

		public boolean dispatch(WorldEvent e) {
			return listener.dispatch(e);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((ID == null) ? 0 : ID.hashCode());
			result = prime * result
					+ ((listener == null) ? 0 : listener.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ListenerIDPair other = (ListenerIDPair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (ID == null) {
				if (other.ID != null)
					return false;
			} else if (!ID.equals(other.ID))
				return false;
			if (listener == null) {
				if (other.listener != null)
					return false;
			} else if (!listener.equals(other.listener))
				return false;
			return true;
		}

		private WorldEventDispatcherImpl getOuterType() {
			return WorldEventDispatcherImpl.this;
		}
	}
}
