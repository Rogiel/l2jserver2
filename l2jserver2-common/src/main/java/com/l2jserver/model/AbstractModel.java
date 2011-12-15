/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.l2jserver.model.id.ID;

/**
 * Simple model interface implementing {@link ID} related methods
 * 
 * @param <T>
 *            the ID type used to represent this {@link Model}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AbstractModel<T extends ID<?>> implements Model<T> {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The object id
	 */
	protected T id;
	/**
	 * The database object state
	 */
	protected transient ObjectDesire desire = ObjectDesire.INSERT;

	@Override
	public T getID() {
		return id;
	}

	@Override
	public void setID(T ID) {
		Preconditions.checkState(id == null, "ID is already set");
		desireInsert();
		this.id = ID;
	}

	@Override
	public ObjectDesire getObjectDesire() {
		return desire;
	}

	@Override
	public void setObjectDesire(ObjectDesire desire) {
		if (desire == null)
			desire = ObjectDesire.NONE;
		log.debug("{} set desire to {}", this, desire);
		this.desire = desire;
	}

	/**
	 * Set this object desire to {@link ObjectDesire#UPDATE}. If the desire is
	 * {@link ObjectDesire#INSERT} or {@link ObjectDesire#DELETE} the desire
	 * will not be changed.
	 */
	@SuppressWarnings("javadoc")
	protected void desireUpdate() {
		if (this.desire != ObjectDesire.INSERT
				&& this.desire != ObjectDesire.DELETE) {
			log.debug("{} desires an update", this);
			this.desire = ObjectDesire.UPDATE;
		}
	}

	/**
	 * Set this object desire to {@link ObjectDesire#INSERT}. If the desire is
	 * {@link ObjectDesire#DELETE} the desire will not be changed.
	 */
	@SuppressWarnings("javadoc")
	protected void desireInsert() {
		if (this.desire != ObjectDesire.DELETE) {
			log.debug("{} desires an insert", this);
			this.desire = ObjectDesire.INSERT;
		}
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
		AbstractModel<?> other = (AbstractModel<?>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
