package com.l2jserver.model.world.player;

import com.l2jserver.model.world.actor.ActorEvent;
import com.l2jserver.model.world.actor.ActorListener;
import com.l2jserver.model.world.event.WorldEvent;
import com.l2jserver.model.world.event.WorldListener;

/**
 * Listener for {@link PlayerEvent}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class PlayerListener implements ActorListener {
	@Override
	public boolean dispatch(ActorEvent e) {
		if (!(e instanceof PlayerEvent))
			return false;
		return dispatch((PlayerEvent) e);
	}

	/**
	 * @see WorldListener#dispatch(WorldEvent)
	 */
	protected abstract boolean dispatch(PlayerEvent e);
}
