package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can become invisible to other objects.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Invisible extends ObjectCapability {
	boolean isInvisible();

	void setInvisible();
}
