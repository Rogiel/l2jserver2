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
	 * Once the event call is dispatched the listener <b>WILL</b> be removed if
	 * false is returned. If you wish to keep this listener, you must return
	 * true.
	 * 
	 * @param e
	 *            the event
	 * @return true to keep listener alive
	 */
	boolean dispatch(E e);
}
