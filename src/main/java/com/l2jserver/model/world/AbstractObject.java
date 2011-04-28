package com.l2jserver.model.world;

import com.l2jserver.model.id.ObjectID;

/**
 * This is an abstract object representing all the world objects in Lineage II.
 * @author Rogiel
 */
public abstract class AbstractObject implements WorldObject {
	protected ObjectID id;

	public ObjectID getId() {
		return id;
	}

	public void setId(ObjectID id) {
		this.id = id;
	}
}
