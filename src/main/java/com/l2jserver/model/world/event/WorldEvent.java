package com.l2jserver.model.world.event;

import com.l2jserver.model.world.WorldObject;

public interface WorldEvent {
	WorldObject getObject();

	/**
	 * Dispatch this event to all the objects
	 */
	void dispatch();
}
