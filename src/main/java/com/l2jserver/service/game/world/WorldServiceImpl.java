package com.l2jserver.service.game.world;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.WorldObjectFilter;
import com.l2jserver.model.world.filter.impl.InstanceFilter;
import com.l2jserver.model.world.iterator.FilterIterator;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.util.factory.CollectionFactory;

public class WorldServiceImpl extends AbstractService implements WorldService {
	private static final Logger log = LoggerFactory
			.getLogger(WorldServiceImpl.class);

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
		log.debug("Adding object {} to world", object);
		objects.add(object);
	}

	@Override
	public void remove(WorldObject object) {
		log.debug("Removing object {} from world", object);
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
		log.debug("Listing objects with filter {}", filter);
		final List<T> list = CollectionFactory.newList(null);
		for (final T object : this.iterable(filter)) {
			list.add(object);
		}
		return list;
	}

	@Override
	public <T extends WorldObject> List<T> list(Class<T> type) {
		log.debug("Listing of type {}", type);
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
