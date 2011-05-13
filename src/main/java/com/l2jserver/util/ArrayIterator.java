package com.l2jserver.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {
	private final T[] objects;
	private int i = 0;

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
