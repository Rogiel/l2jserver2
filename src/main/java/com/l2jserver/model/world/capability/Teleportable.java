package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.util.Coordinate;

/**
 * Defines an {@link AbstractObject} that can be teleported by
 * {@link Teleporter} objects. Note that it is also possible to teleport
 * <b>without</b> a teleporter!
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Teleportable extends ObjectCapability, Positionable, Spawnable {
	void teleport(Coordinate coordinate);
}
