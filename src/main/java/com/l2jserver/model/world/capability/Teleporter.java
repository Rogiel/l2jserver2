package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * Defines an {@link AbstractObject} that can teleport {@link Teleportable}
 * objects.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Teleporter extends ObjectCapability {
	void teleport(Coordinate coord, Teleportable target);
}
