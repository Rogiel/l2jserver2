package com.l2jserver.model.world.player;

import com.l2jserver.model.world.actor.ActorEvent;
import com.l2jserver.model.world.actor.ActorListener;

public abstract class PlayerListener implements ActorListener {
	@Override
	public boolean dispatch(ActorEvent e) {
		if (!(e instanceof PlayerEvent))
			return false;
		return dispatch((PlayerEvent) e);
	}

	/**
	 * Once the event call is dispatched, the listener <b>WILL NOT</b> be
	 * removed. You must manually remove it from the <tt>event</tt> object.
	 * 
	 * @param e
	 *            the event
	 */
	protected abstract boolean dispatch(PlayerEvent e);
}
