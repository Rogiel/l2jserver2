package com.l2jserver.model.world.event;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.capability.Listenable;

public interface WorldEvent {
	WorldObject getObject();

	Listenable<?, ?>[] getDispatchableObjects();
}
