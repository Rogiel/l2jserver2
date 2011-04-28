package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.util.Coordinate;

/**
 * Represents an {@link AbstractObject} that can be summoned.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Summunable extends Spawnable {
	void summon(Coordinate coordinate);

	boolean isSummoned();
}
