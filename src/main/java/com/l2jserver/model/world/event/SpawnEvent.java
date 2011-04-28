package com.l2jserver.model.world.event;

import com.l2jserver.model.world.capability.Spawnable;

public interface SpawnEvent extends WorldEvent {
	Spawnable getObject();
}