package com.l2jserver.util.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Factory class to create {@link Collection} instances.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CollectionFactory {
	/**
	 * Creates a new list of type <tt>T</tt>
	 * 
	 * @param <T>
	 *            the type
	 * @param type
	 *            the type
	 * @return the created list
	 */
	public static final <T> List<T> newList(Class<T> type) {
		return new ArrayList<T>();
	}

	/**
	 * Creates a new set of type <tt>T</tt>
	 * 
	 * @param <T>
	 *            the type
	 * @param type
	 *            the type
	 * @return the created set
	 */
	public static final <T> Set<T> newSet(Class<T> type) {
		return new HashSet<T>();
	}
}
