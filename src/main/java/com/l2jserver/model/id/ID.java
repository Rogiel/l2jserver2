package com.l2jserver.model.id;

import com.google.inject.Inject;

/**
 * The ID interface. Each Object or Template must be represented by an unique
 * ID.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ID {
	/**
	 * The id itself
	 */
	protected final int id;

	@Inject
	protected ID(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		ID other = (ID) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
