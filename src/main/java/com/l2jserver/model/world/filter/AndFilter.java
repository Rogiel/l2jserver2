package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

/**
 * <tt>AND</tt> filter that accepts all values in which all other
 * <tt>filters</tt> return true.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <O>
 *            the item type
 */
public class AndFilter<O extends WorldObject> implements WorldObjectFilter<O> {
	/**
	 * The filters
	 */
	private WorldObjectFilter<O>[] filters;

	/**
	 * Creates a new instance
	 * 
	 * @param filters
	 *            filters to be used with <tt>AND</tt> operator
	 */
	public AndFilter(WorldObjectFilter<O>... filters) {
		this.filters = filters;
	}

	@Override
	public boolean accept(O object) {
		for (final WorldObjectFilter<O> filter : filters) {
			if (!filter.accept(object))
				return false;
		}
		return true;
	}
}
