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
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.inject.Inject;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.core.threading.ThreadPool;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Default {@link WorldEventDispatcher} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class WorldEventDispatcherImpl implements WorldEventDispatcher {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(WorldEventDispatcherImpl.class);

	private final ThreadService threadService;

	/**
	 * The execution thread
	 */
	private ThreadPool threadPool;

	/**
	 * The list of all global listeners
	 */
	private Set<WorldListener> globalListeners = CollectionFactory.newSet();
	/**
	 * The {@link Map} containing all listeners for every object
	 */
	private Map<ObjectID<?>, Set<WorldListener>> listeners = CollectionFactory
			.newMap();
	/**
	 * The events pending dispatch
	 */
	private Queue<EventContainer> events = CollectionFactory
			.newConcurrentQueue();

	@Inject
	public WorldEventDispatcherImpl(ThreadService threadService) {
		this.threadService = threadService;
	}

	public void start() {
		threadPool = threadService.createThreadPool("event-dispatcher", 1);
		threadPool.async(0, TimeUnit.MILLISECONDS, 20, new TimerTask() {
			@Override
			public void run() {
				EventContainer event;
				while ((event = events.poll()) != null) {
					try {
						log.debug("Dispatching event {}", event.event);

						// set state
						event.future.running = true;
						event.future.complete = false;

						// dispatch
						if (doDispatch(event))
							// the set will update state
							event.future.set(event.event);
					} catch (Throwable t) {
						event.future.setException(t);
						log.warn("Exception in WorldEventDispatcher thread", t);
					}
				}
			}
		});
	}

	@Override
	public <E extends WorldEvent> WorldEventFuture<E> dispatch(E event) {
		Preconditions.checkNotNull(event, "event");
		log.debug("Queing dispatch for event {}", event);
		final WorldEventFutureImpl<E> future = new WorldEventFutureImpl<E>();
		events.add(new EventContainer(event, future));
		// final WorldEventFutureImpl<E> future = new WorldEventFutureImpl<E>();
		// final EventContainer c = new EventContainer(event, future);
		// doDispatch(c);
		return future;
	}

	/**
	 * Do the dispatching
	 * 
	 * @param event
	 *            the event
	 * @return true if dispatch was not canceled
	 */
	public synchronized boolean doDispatch(EventContainer event) {
		final ObjectID<?>[] objects = event.event.getDispatchableObjects();
		for (ObjectID<?> obj : objects) {
			if (obj == null)
				continue;
			for (final WorldListener listener : globalListeners) {
				if (event.future.isCancelled())
					return false;
				try {
					if (!listener.dispatch(event.event))
						// remove listener if return value is false
						globalListeners.remove(listener);
				} catch (Throwable t) {
					log.warn("Exception in listener", t);
					// always remove any listener that throws an exception
					globalListeners.remove(listener);
				}
			}
			final Set<WorldListener> listeners = getListeners(obj);
			for (final WorldListener listener : listeners) {
				if (event.future.isCancelled())
					return false;
				try {
					if (!listener.dispatch(event.event))
						// remove listener if return value is false
						listeners.remove(listener);
				} catch (Throwable t) {
					log.warn("Exception in listener", t);
					// always remove any listener that throws an exception
					listeners.remove(listener);
				}
			}
		}
		return true;
	}

	@Override
	public void addListener(WorldListener listener) {
		Preconditions.checkNotNull(listener, "listener");
		log.debug("Adding new listener global {}", listener);
		globalListeners.add(listener);
	}

	@Override
	public void addListener(WorldObject object, WorldListener listener) {
		Preconditions.checkNotNull(object, "object");
		Preconditions.checkNotNull(listener, "listener");
		addListener(object.getID(), listener);
	}

	@Override
	public void addListener(ObjectID<?> id, WorldListener listener) {
		Preconditions.checkNotNull(id, "id");
		Preconditions.checkNotNull(listener, "listener");
		log.debug("Adding new listener {} to {}", listener, id);
		getListeners(id).add(listener);
	}

	@Override
	public void removeListener(WorldListener listener) {
		Preconditions.checkNotNull(listener, "listener");
		globalListeners.remove(listener);
	}

	@Override
	public void removeListener(WorldObject object, WorldListener listener) {
		Preconditions.checkNotNull(object, "object");
		Preconditions.checkNotNull(listener, "listener");
		removeListener(object.getID(), listener);
	}

	@Override
	public void removeListener(ObjectID<?> id, WorldListener listener) {
		Preconditions.checkNotNull(id, "id");
		Preconditions.checkNotNull(listener, "listener");
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
		Preconditions.checkNotNull(id, "id");
		listeners.remove(id);
	}

	/**
	 * Get the {@link Set} of listeners for an given object. Creates a new one
	 * if does not exists.
	 * 
	 * @param id
	 *            the object id
	 * @return the {@link Set}. Never null.
	 */
	private Set<WorldListener> getListeners(ObjectID<?> id) {
		Preconditions.checkNotNull(id, "id");
		Set<WorldListener> set = listeners.get(id);
		if (set == null) {
			set = CollectionFactory.newSet();
			listeners.put(id, set);
		}
		return set;
	}

	public void stop() {
		threadService.dispose(threadPool);
		threadPool = null;
	}

	/**
	 * {@link WorldEventFuture} implementation
	 * 
	 * @param <E>
	 *            the event type
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private static class WorldEventFutureImpl<E extends WorldEvent> extends
			AbstractFuture<E> implements WorldEventFuture<E> {
		private boolean running = false;
		private boolean complete = false;

		@Override
		@SuppressWarnings("unchecked")
		protected boolean set(WorldEvent value) {
			running = false;
			complete = true;
			return super.set((E) value);
		}

		@Override
		protected boolean setException(Throwable throwable) {
			return super.setException(throwable);
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			if (!mayInterruptIfRunning && running)
				return false;
			if (complete)
				return false;
			return cancel();
		}

		@Override
		public void await() throws InterruptedException {
			try {
				super.get();
			} catch (ExecutionException e) {
			}
		}

		@Override
		public void await(long timeout, TimeUnit unit)
				throws InterruptedException, TimeoutException {
			try {
				super.get(timeout, unit);
			} catch (ExecutionException e) {
			}
		}

		@Override
		public boolean awaitUninterruptibly() {
			try {
				super.get();
				return true;
			} catch (InterruptedException e) {
				return false;
			} catch (ExecutionException e) {
				return false;
			}
		}

		@Override
		public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
			try {
				super.get(timeout, unit);
				return true;
			} catch (InterruptedException e) {
				return false;
			} catch (ExecutionException e) {
				return false;
			} catch (TimeoutException e) {
				return false;
			}
		}
	}

	/**
	 * Simple container that contains an event and a future
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private static class EventContainer {
		/**
		 * The event
		 */
		private final WorldEvent event;
		/**
		 * The future
		 */
		private final WorldEventFutureImpl<? extends WorldEvent> future;

		/**
		 * Creates a new instance
		 * 
		 * @param event
		 *            the event
		 * @param future
		 *            the future
		 */
		public EventContainer(WorldEvent event,
				WorldEventFutureImpl<? extends WorldEvent> future) {
			this.event = event;
			this.future = future;
		}
	}
}
