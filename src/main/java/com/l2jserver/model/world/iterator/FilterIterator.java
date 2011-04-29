package com.l2jserver.model.world.iterator;

import java.util.Iterator;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.WorldObjectFilter;

public class FilterIterator<O extends WorldObject> implements Iterator<O> {
	private final Iterator<WorldObject> objects;
	private final WorldObjectFilter<O> filter;

	private O selected;

	public FilterIterator(final WorldObjectFilter<O> filter,
			Iterator<WorldObject> objects) {
		this.filter = filter;
		this.objects = objects;
	}

	@Override
	public boolean hasNext() {
		O next = findNext();
		return (next != null);
	}

	@Override
	public O next() {
		try {
			return findNext();
		} finally {
			selected = null;
		}
	}

	@Override
	public void remove() {
	}

	public O findNext() {
		if (selected != null)
			return selected;
		while (objects.hasNext()) {
			try {
				@SuppressWarnings("unchecked")
				final O object = (O) objects.next();
				if (filter.accept(object)) {
					selected = object;
					return selected;
				}
			} catch (ClassCastException e) {
			}
		}
		return null;
	}
}
