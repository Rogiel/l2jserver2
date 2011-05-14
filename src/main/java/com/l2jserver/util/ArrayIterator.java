package com.l2jserver.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This {@link Iterator} takes an array as input and iterate over it.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the array type
 */
public class ArrayIterator<T> implements Iterator<T> {
	/**
	 * The objects
	 */
	private final T[] objects;
	/**
	 * the iterator index
	 */
	private int i = 0;

	/**
	 * Creates a new iterator instance
	 * 
	 * @param objects
	 *            the objects
	 */
	public ArrayIterator(T... objects) {
		this.objects = objects;
	}

	@Override
	public boolean hasNext() {
		return i < objects.length;
	}

	@Override
	public T next() {
		try {
			return objects[i++];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void remove() {
	}
}
