package com.l2jserver.model.id;

import com.google.inject.Inject;

/**
 * The ID interface. Each object must be represented by an unique ID.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ID<T> {
	/**
	 * The id itself
	 */
	protected final T id;

	@Inject
	protected ID(T id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public T getID() {
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
		result = prime * result + id.hashCode() + this.getClass().hashCode();
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
		@SuppressWarnings("rawtypes")
		ID other = (ID) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
