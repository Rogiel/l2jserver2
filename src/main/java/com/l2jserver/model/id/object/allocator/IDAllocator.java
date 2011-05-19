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
package com.l2jserver.model.id.object.allocator;

/**
 * The ID allocator is used to alloc new ID and to release IDs that aren't used
 * anymore.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface IDAllocator {
	/**
	 * The first ID ever allocated
	 */
	public final static int FIRST_ID = 0x10000000;
	/**
	 * The last ID ever allocated
	 */
	public final static int LAST_ID = 0x7FFFFFFF;
	/**
	 * Total of available IDs for allocation
	 */
	public final static int ALLOCABLE_IDS = LAST_ID - FIRST_ID;

	/**
	 * This is method is used to register IDs as used at startup time.
	 * 
	 * @param id
	 *            the id
	 */
	void allocate(int id);

	/**
	 * Allocates a new ID
	 * 
	 * @return the allocated ID value
	 */
	int allocate();

	/**
	 * Release an ID
	 * 
	 * @param id
	 *            the id
	 */
	void release(int id);

	/**
	 * Release all allocated IDs
	 */
	void clear();

	/**
	 * Get the amount of already allocated IDs
	 * 
	 * @return allocated ids count
	 */
	int getAllocatedIDs();

	/**
	 * Get the amount of IDs remaining to be allocated
	 * 
	 * @return free ids count
	 */
	int getFreeIDs();
}
