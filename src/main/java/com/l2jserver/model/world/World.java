package com.l2jserver.model.world;

import java.util.Iterator;
import java.util.Set;

import com.l2jserver.model.world.event.WorldEventDispatcher;
import com.l2jserver.model.world.filter.WorldObjectFilter;
import com.l2jserver.model.world.iterator.FilterIterator;
import com.l2jserver.util.factory.CollectionFactory;

public class World implements Iterable<WorldObject> {
	private final Set<WorldObject> objects = CollectionFactory
			.newSet(WorldObject.class);
	private final WorldEventDispatcher dispatcher = new WorldEventDispatcher(
			this);

	public void add(WorldObject object) {
		objects.add(object);
	}

	public void remove(WorldObject object) {
		objects.remove(object);
	}

	public boolean contains(WorldObject object) {
		return objects.contains(object);
	}

	public WorldEventDispatcher getDispatcher() {
		return dispatcher;
	}

	@Override
	public Iterator<WorldObject> iterator() {
		return objects.iterator();
	}

	public <T extends WorldObject> Iterator<T> iterator(
			final WorldObjectFilter<T> filter) {
		return new FilterIterator<T>(filter, objects.iterator());
	}

	public <T extends WorldObject> Iterable<T> iterable(
			final WorldObjectFilter<T> filter) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new FilterIterator<T>(filter, objects.iterator());
			}
		};
	}
}
