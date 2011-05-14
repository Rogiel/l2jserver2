package com.l2jserver.model.id.object.iterator;

import java.util.Iterator;

import com.l2jserver.model.world.WorldObject;

/**
 * This is a simple {@link Iterable} implementation that always return the same
 * {@link Iterator}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the return object type
 */
public class WorldObjectIterable<T extends WorldObject> implements Iterable<T> {
	/**
	 * The {@link Iterator}
	 */
	private final Iterator<T> iterator;

	/**
	 * Creates a new instance
	 * 
	 * @param iterator
	 *            the iterator
	 */
	public WorldObjectIterable(Iterator<T> iterator) {
		this.iterator = iterator;
	}

	@Override
	public Iterator<T> iterator() {
		return iterator;
	}
}
