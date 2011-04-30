package com.l2jserver.service.game.world;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.event.WorldEvent;
import com.l2jserver.model.world.event.WorldListener;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * {@link WorldEventDispatcher} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class WorldEventDispatcherImpl implements WorldEventDispatcher {
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

	public void dispatch(WorldEvent event) {
		events.add(event);
	}

	public void doDispatch(WorldEvent event) {
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
				}
				listeners.remove(pair);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends WorldEvent, L extends WorldListener<E>> void addListener(
			Listenable<L, E> object, WorldListener<E> listener) {
		listeners.add(new ListenerIDPair(object.getID(),
				(WorldListener<WorldEvent>) listener));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends WorldEvent, L extends WorldListener<E>> void addListener(
			ObjectID<? extends Listenable<L, E>> id, WorldListener<E> listener) {
		listeners.add(new ListenerIDPair(id,
				(WorldListener<WorldEvent>) listener));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends WorldEvent, L extends WorldListener<E>> void removeListener(
			Listenable<L, E> object, WorldListener<E> listener) {
		listeners.remove(new ListenerIDPair(object.getID(),
				(WorldListener<WorldEvent>) listener));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends WorldEvent, L extends WorldListener<E>> void removeListener(
			ObjectID<? extends Listenable<L, E>> id, WorldListener<E> listener) {
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
