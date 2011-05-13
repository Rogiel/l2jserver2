package com.l2jserver.service.game.world.event;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.event.WorldEvent;
import com.l2jserver.model.world.event.WorldListener;

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

	/**
	 * Adds a new <tt>listener</tt> to <tt>object</tt>
	 * 
	 * @param <E>
	 *            the event type
	 * @param <L>
	 *            the listener type
	 * @param object
	 *            the object to listen to
	 * @param listener
	 *            the listener
	 */
	<E extends WorldEvent, L extends WorldListener<E>> void addListener(
			Listenable<L, E> object, WorldListener<E> listener);

	/**
	 * Adds a new <tt>listener</tt> to object with id <tt>id</tt>
	 * 
	 * @param <E>
	 *            the event type
	 * @param <L>
	 *            the listener type
	 * @param id
	 *            the object id to listen to
	 * @param listener
	 *            the listener
	 */
	<E extends WorldEvent, L extends WorldListener<E>> void addListener(
			ObjectID<? extends Listenable<L, E>> id, WorldListener<E> listener);

	/**
	 * Removes an existing <tt>listener</tt> from <tt>object</tt>
	 * 
	 * @param <E>
	 *            the event type
	 * @param <L>
	 *            the listener type
	 * @param object
	 *            the object to listen to
	 * @param listener
	 *            the listener
	 */
	<E extends WorldEvent, L extends WorldListener<E>> void removeListener(
			Listenable<L, E> object, WorldListener<E> listener);

	/**
	 * Removes an existing <tt>listener</tt> from the object with id <tt>id</tt>
	 * 
	 * @param <E>
	 *            the event type
	 * @param <L>
	 *            the listener type
	 * @param id
	 *            the object id to listen to
	 * @param listener
	 *            the listener
	 */
	<E extends WorldEvent, L extends WorldListener<E>> void removeListener(
			ObjectID<? extends Listenable<L, E>> id, WorldListener<E> listener);
}
