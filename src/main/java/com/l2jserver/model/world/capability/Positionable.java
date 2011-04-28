package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.util.Coordinate;

/**
 * Defines an {@link AbstractObject} that can be positioned in the world.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Positionable extends WorldCapability {
	Coordinate getPosition();
}
