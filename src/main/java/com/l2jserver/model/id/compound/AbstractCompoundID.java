/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.id.compound;

import com.l2jserver.model.id.ID;

/**
 * The compound {@link ID} is composed of two IDs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class AbstractCompoundID<T1, T2> extends ID<AbstractCompoundID<T1, T2>> {
	/**
	 * The first ID
	 */
	private final T1 id1;
	/**
	 * The second ID
	 */
	private final T2 id2;

	/**
	 * Creates a new compound ID
	 * 
	 * @param id1
	 *            the first id
	 * @param id2
	 *            the second id
	 */
	protected AbstractCompoundID(T1 id1, T2 id2) {
		super(null);
		this.id1 = id1;
		this.id2 = id2;
	}

	/**
	 * @return the first id
	 */
	public T1 getID1() {
		return id1;
	}

	/**
	 * @return the second id
	 */
	public T2 getID2() {
		return id2;
	}

	@Override
	public AbstractCompoundID<T1, T2> getID() {
		return this;
	}
}
