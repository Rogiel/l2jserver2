package com.l2jserver.service.game.world;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.Service;

public interface WorldService extends Service, Iterable<WorldObject> {
	/**
	 * Register a new {@link WorldObject} to the service.
	 * 
	 * @param object
	 *            the object
	 */
	void register(WorldObject object);

	/**
	 * Removes an registered {@link WorldObject} from the service.
	 * 
	 * @param object
	 *            the object
	 */
	void unregister(WorldObject object);
}
