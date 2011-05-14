package com.l2jserver.model.world;

import com.l2jserver.model.id.ObjectID;

/**
 * This is an abstract object representing all the world objects in Lineage II.
 * 
 * @author Rogiel
 */
public abstract class AbstractObject implements WorldObject {
	/**
	 * The {@link ObjectID}
	 */
	protected ObjectID<?> id;

	public ObjectID<?> getID() {
		return id;
	}

	public void setID(ObjectID<?> id) {
		if (this.id != null)
			throw new IllegalStateException("ID is already set!");
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractObject other = (AbstractObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
