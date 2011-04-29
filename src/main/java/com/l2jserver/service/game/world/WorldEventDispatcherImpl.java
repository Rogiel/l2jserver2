package com.l2jserver.service.game.world;

import com.l2jserver.model.world.event.WorldEvent;

/**
 * {@link WorldEventDispatcher} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class WorldEventDispatcherImpl implements WorldEventDispatcher {
	public void dispatch(WorldEvent event) {
		// TODO implement threaded model
		event.dispatch();
	}
}
