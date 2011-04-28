package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

/**
 * Filter an object in a world
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WorldFilter<O extends WorldObject> {
	/**
	 * Test if <tt>object</tt> matches the filter requirements
	 * 
	 * @param object
	 *            the object
	 * @return true if object match requirements
	 */
	boolean accept(O object);
}
