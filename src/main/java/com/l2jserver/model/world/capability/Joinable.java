package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that other objects can join to.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Joinable<T> extends ObjectCapability {
	/**
	 * Join an <tt>member</tt> to this object.
	 * 
	 * @param member
	 *            the entering member
	 */
	void join(T member);

	/**
	 * Removes the joined <tt>member</tt> from this object
	 * 
	 * @param member
	 *            the exiting member
	 */
	void leave(T member);
}
