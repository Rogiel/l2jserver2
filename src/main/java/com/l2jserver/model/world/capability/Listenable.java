package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.model.world.event.WorldEvent;
import com.l2jserver.model.world.event.WorldListener;

/**
 * Defines an {@link AbstractObject} that can attach {@link WorldListener} that
 * notifies of events on that object.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <L>
 *            the listener type
 * @param <E>
 *            the event type
 */
public interface Listenable<L extends WorldListener<E>, E extends WorldEvent>
		extends WorldCapability {
	/**
	 * Adds a new listener
	 * 
	 * @param listener
	 *            the listener
	 */
	void addListener(L listener);

	/**
	 * Removes an listener
	 * 
	 * @param listener
	 *            the listener
	 */
	void removeListener(L listener);

	/**
	 * Don't use this method directly. It is called by the event dispatcher.
	 * 
	 * @param e
	 *            the event
	 */
	void dispatch(E e);
}
