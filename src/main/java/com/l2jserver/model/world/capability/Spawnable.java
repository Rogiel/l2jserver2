package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.util.Coordinate;

/**
 * Represents an {@link AbstractObject} that can be spawned.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Spawnable extends WorldCapability, Positionable {
	void spawn(Coordinate coordinate);

	boolean isSpawned();
}
