package com.l2jserver.model.world.event;

/**
 * This is the most abstract listener for the listening engine.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <E>
 *            the received event type
 */
public interface WorldListener<E extends WorldEvent> {
	/**
	 * Once the event call is dispatched, the listener <b>WILL NOT</b> be
	 * removed. You must manually remove it from the <tt>event</tt> object.
	 * 
	 * @param e
	 *            the event
	 */
	void dispatch(E e);
}
