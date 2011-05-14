package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

/**
 * And filter that accepts all values in which the other <tt>filter</tt> return
 * false.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <O>
 *            the item type
 */
public class NotFilter<O extends WorldObject> implements WorldObjectFilter<O> {
	/**
	 * The filter
	 */
	private WorldObjectFilter<O> filter;

	/**
	 * Creates a new instance
	 * 
	 * @param filter
	 *            the filter
	 */
	public NotFilter(WorldObjectFilter<O> filter) {
		this.filter = filter;
	}

	@Override
	public boolean accept(O object) {
		return !filter.accept(object);
	}
}
