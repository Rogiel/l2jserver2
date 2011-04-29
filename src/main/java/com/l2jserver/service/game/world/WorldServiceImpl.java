package com.l2jserver.service.game.world;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.WorldObjectFilter;
import com.l2jserver.model.world.filter.impl.InstanceFilter;
import com.l2jserver.model.world.iterator.FilterIterator;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.util.factory.CollectionFactory;

public class WorldServiceImpl implements WorldService {
	private final Set<WorldObject> objects = CollectionFactory
			.newSet(WorldObject.class);
	private final WorldEventDispatcher dispatcher;

	@Inject
	public WorldServiceImpl(WorldEventDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public void start() throws ServiceStartException {
		objects.clear();
	}

	@Override
	public void add(WorldObject object) {
		objects.add(object);
	}

	@Override
	public void remove(WorldObject object) {
		objects.remove(object);
	}

	@Override
	public boolean contains(WorldObject object) {
		return objects.contains(object);
	}

	@Override
	public WorldEventDispatcher getEventDispatcher() {
		return dispatcher;
	}

	@Override
	public <T extends WorldObject> List<T> list(WorldObjectFilter<T> filter) {
		final List<T> list = CollectionFactory.newList(null);
		for (final T object : this.iterable(filter)) {
			list.add(object);
		}
		return list;
	}

	@Override
	public <T extends WorldObject> List<T> list(Class<T> type) {
		return list(new InstanceFilter<T>(type));
	}

	@Override
	public Iterator<WorldObject> iterator() {
		return objects.iterator();
	}

	@Override
	public <T extends WorldObject> Iterator<T> iterator(
			final WorldObjectFilter<T> filter) {
		return new FilterIterator<T>(filter, objects.iterator());
	}

	@Override
	public <T extends WorldObject> Iterable<T> iterable(
			final WorldObjectFilter<T> filter) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new FilterIterator<T>(filter, objects.iterator());
			}
		};
	}

	@Override
	public void stop() throws ServiceStopException {
		objects.clear();
	}
}
