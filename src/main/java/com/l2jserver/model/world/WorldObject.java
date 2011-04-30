package com.l2jserver.model.world;

import com.l2jserver.model.id.ObjectID;

public interface WorldObject {
	/**
	 * Get the object's ID
	 * 
	 * @return the object id
	 */
	ObjectID<?> getID();

	/**
	 * Set this object ID. Note that the ID can only be set once. Truing to
	 * change an ID will thrown an {@link IllegalStateException}.
	 * 
	 * @param id
	 *            the id
	 * @throws IllegalStateException
	 *             if ID is already set
	 */
	void setID(ObjectID<?> id) throws IllegalStateException;
}
