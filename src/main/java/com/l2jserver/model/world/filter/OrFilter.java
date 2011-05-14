package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

/**
 * <tt>OR</tt> filter that accepts all values in which at least one of the
 * <tt>filters</tt> return true.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <O>
 *            the item type
 */
public class OrFilter<O extends WorldObject> implements WorldObjectFilter<O> {
	/**
	 * The filters
	 */
	private WorldObjectFilter<O>[] filters;

	/**
	 * Creates a new instance
	 * 
	 * @param filters
	 *            filters to be used with <tt>OR</tt> operator
	 */
	public OrFilter(WorldObjectFilter<O>... filters) {
		this.filters = filters;
	}

	@Override
	public boolean accept(O object) {
		for (final WorldObjectFilter<O> filter : filters) {
			if (filter.accept(object))
				return true;
		}
		return false;
	}
}
