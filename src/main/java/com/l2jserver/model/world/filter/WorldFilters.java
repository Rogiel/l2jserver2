package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

/**
 * Utility class for common filter types
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public final class WorldFilters {
	/**
	 * Performs an <tt>AND</tt> operation
	 * 
	 * @param <O>
	 *            the object type
	 * @param filters
	 *            the filters
	 * @return the {@link AndFilter}
	 */
	public static final <O extends WorldObject> WorldObjectFilter<O> and(
			WorldObjectFilter<O>... filters) {
		return new AndFilter<O>(filters);
	}

	/**
	 * Performs an <tt>OR</tt> operation
	 * 
	 * @param <O>
	 *            the object type
	 * @param filters
	 *            the filters
	 * @return the {@link OrFilter}
	 */
	public static final <O extends WorldObject> WorldObjectFilter<O> or(
			WorldObjectFilter<O>... filters) {
		return new OrFilter<O>(filters);
	}

	/**
	 * Performs an <tt>NOTA</tt> operation
	 * 
	 * @param <O>
	 *            the object type
	 * @param filters
	 *            the filters
	 * @return the {@link NotFilter}
	 */
	public static final <O extends WorldObject> WorldObjectFilter<O> not(
			WorldObjectFilter<O> filter) {
		return new NotFilter<O>(filter);
	}
}
