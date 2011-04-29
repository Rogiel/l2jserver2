package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can receive skill castings.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Castable extends ObjectCapability {
	void cast();
}
