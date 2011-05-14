package com.l2jserver.model.world.iterator;

import java.util.Iterator;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.WorldObjectFilter;

/**
 * The {@link FilterIterator} takes an {@link WorldObject} and a
 * {@link WorldObjectFilter} and dynamically iterates over the items and find
 * the next matching object.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <O>
 *            the object type
 */
public class FilterIterator<O extends WorldObject> implements Iterator<O> {
	/**
	 * The unfiltered object iterator
	 */
	private final Iterator<WorldObject> objects;
	/**
	 * The filter
	 */
	private final WorldObjectFilter<O> filter;
	/**
	 * The next object found
	 */
	private O selected;

	/**
	 * Creates a new instance
	 * 
	 * @param filter
	 *            the filter
	 * @param objects
	 *            the unfiltered object iterator
	 */
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

	/**
	 * Locates the next matching object
	 * 
	 * @return the next matching object
	 */
	private O findNext() {
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
