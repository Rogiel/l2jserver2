package com.l2jserver.model.world.event;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.capability.Listenable;

public interface WorldEvent {
	/**
	 * @return the object that issued this event
	 */
	WorldObject getObject();

	/**
	 * @return the list of objects that will be notified of this event
	 */
	Listenable<?, ?>[] getDispatchableObjects();
}
