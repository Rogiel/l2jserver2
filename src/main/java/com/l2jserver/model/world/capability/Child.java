package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that is a child of another
 * {@link AbstractObject}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Child<P extends Parent> extends WorldCapability {
	public P getParent();
}
