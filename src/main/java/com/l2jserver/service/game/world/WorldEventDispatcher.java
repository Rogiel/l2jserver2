package com.l2jserver.service.game.world;

import com.l2jserver.model.world.event.WorldEvent;

/**
 * This event dispatcher notify listeners that an certain event occured in their
 * objects.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WorldEventDispatcher {
	/**
	 * Notify listeners of the <tt>event</tt>. Note that not all implementation
	 * need to invoke listeners immediately. Dispatching <b>can</b> occur
	 * concurrently.
	 * 
	 * @param event
	 *            the event
	 */
	void dispatch(WorldEvent event);
}
