package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.model.world.WorldObject;

/**
 * Defines an {@link AbstractObject} that can be killed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Killable extends ObjectCapability {
	/**
	 * Process the dying routines. Note that if the object killed himself,
	 * <tt>killer</tt> must be his instance.
	 * 
	 * @param killer
	 *            the killer. Can be null if unknown.
	 */
	void die(WorldObject killer);
}
