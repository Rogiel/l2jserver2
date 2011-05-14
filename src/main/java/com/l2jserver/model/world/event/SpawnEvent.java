package com.l2jserver.model.world.event;

import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.util.Coordinate;

/**
 * Event for objects spawning
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface SpawnEvent extends WorldEvent {
	@Override
	Spawnable getObject();

	Coordinate getCoordinate();
}