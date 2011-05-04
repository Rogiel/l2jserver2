package com.l2jserver.model.id.object.factory;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.factory.IDFactory;

public interface ObjectIDFactory<T extends ObjectID<?>> extends IDFactory<T> {
	/**
	 * Generates a new ID
	 * 
	 * @return the new ID
	 */
	T createID();

	/**
	 * Destroy this ID. Releases this value to be used once again.
	 * 
	 * @param id
	 *            the id to be destroyed.
	 */
	void destroy(T id);
}
