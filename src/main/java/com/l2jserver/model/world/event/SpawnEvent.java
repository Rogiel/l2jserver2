package com.l2jserver.model.world.event;

import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.util.Coordinate;

public interface SpawnEvent extends WorldEvent {
	Spawnable getObject();

	Coordinate getCoordinate();
}