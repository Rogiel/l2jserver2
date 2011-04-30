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
	protected ObjectID(int id) {
		super(id);
	}

	public abstract T getObject();
}
