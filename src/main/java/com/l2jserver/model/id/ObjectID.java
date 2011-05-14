package com.l2jserver.model.id;

import com.l2jserver.model.id.factory.IDFactory;
import com.l2jserver.model.world.WorldObject;

/**
 * {@link ObjectID}s cannot be instantiated directly. This must be done through
 * an {@link IDFactory}. The {@link ObjectID} provides a facility
 * {@link #getObject() method} that allows easily fetch this object from
 * database without the need to directly use DAOs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the {@link WorldObject} type
 */
public abstract class ObjectID<T extends WorldObject> extends ID {
	/**
	 * Creates a new instance
	 * 
	 * @param id
	 *            the raw id
	 */
	protected ObjectID(int id) {
		super(id);
	}

	/**
	 * Returns the {@link WorldObject} associated with this {@link ID}
	 * 
	 * @return the {@link WorldObject} if existent, <tt>null</tt> otherwise
	 */
	public abstract T getObject();
}
