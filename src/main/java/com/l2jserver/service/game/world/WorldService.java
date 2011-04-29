package com.l2jserver.service.game.world;

import java.util.Iterator;
import java.util.List;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.WorldObjectFilter;
import com.l2jserver.service.Service;

/**
 * Service responsible for managing {@link WorldObject} and dispatch events.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WorldService extends Service, Iterable<WorldObject> {
	/**
	 * Adds an object into the world.
	 * 
	 * @param object
	 *            the object
	 */
	public void add(WorldObject object);

	/**
	 * Removes an object of the world
	 * 
	 * @param object
	 *            the object
	 */
	public void remove(WorldObject object);

	/**
	 * Check if this object is in the world.
	 * 
	 * @param object
	 *            the object
	 * @return true if object exists
	 */
	public boolean contains(WorldObject object);

	/**
	 * Get the event dispatcher
	 * 
	 * @return the event dispatcher
	 */
	public WorldEventDispatcher getEventDispatcher();

	/**
	 * Creates a list of all objects matching <tt>filter</tt>
	 * 
	 * @param <T>
	 *            the object type
	 * @param filter
	 *            the filter
	 * @return the list of objects
	 */
	public <T extends WorldObject> List<T> list(WorldObjectFilter<T> filter);

	/**
	 * Creates a list of all objects of type <tt>type</tt>
	 * 
	 * @param <T>
	 *            the type
	 * @param type
	 *            the type class
	 * @return the list of objects
	 */
	<T extends WorldObject> List<T> list(Class<T> type);

	/**
	 * Get the iterator for this <tt>filter</tt>
	 * 
	 * @param <T>
	 *            the object type
	 * @param filter
	 *            the filter
	 * @return the iterator instance
	 */
	<T extends WorldObject> Iterator<T> iterator(
			final WorldObjectFilter<T> filter);

	/**
	 * Shortcut method for {@link Iterable#iterable()} with filters. The
	 * iterable instance returns the same {@link Iterator} as
	 * {@link #iterator(WorldObjectFilter)}.
	 * 
	 * @param <T>
	 *            the object type
	 * @param filter
	 *            the filter
	 * @return the iterable instance
	 */
	<T extends WorldObject> Iterable<T> iterable(
			final WorldObjectFilter<T> filter);
}
