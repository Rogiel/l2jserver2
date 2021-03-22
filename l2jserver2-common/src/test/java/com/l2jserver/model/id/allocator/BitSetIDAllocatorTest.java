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
package com.l2jserver.model.id.allocator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.l2jserver.model.id.object.allocator.BitSetIDAllocator;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.model.id.object.allocator.IDAllocatorException;

/**
 * Tests for {@link BitSetIDAllocator}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BitSetIDAllocatorTest {
	/**
	 * The allocator
	 */
	private final BitSetIDAllocator allocator = new BitSetIDAllocator();

	/**
	 * Preparation for tests
	 */
	@Before
	public void tearUp() {
		allocator.init();
	}

	/**
	 * Test id allocation
	 */
	@Test
	public void testAllocate() {
		final int id1 = allocator.allocate();
		final int id2 = allocator.allocate();
		assertFalse("IDs must not be equal", id1 == id2);
		assertEquals("First allocated ID must be equal to the first allocatable ID", IDAllocator.FIRST_ID, id1);
		assertEquals("IDs must increment sequentially", IDAllocator.FIRST_ID + 1, id2);
	}

	/**
	 * Test restoring ID allocation
	 */
	@Test
	public void testAllocateRestore() {
		final int id1 = IDAllocator.FIRST_ID;
		final int id2 = IDAllocator.FIRST_ID + 1;

		allocator.allocate(id1);
		allocator.allocate(id2);

		int id3 = allocator.allocate();

		assertFalse(id1 == id3);
		assertFalse(id2 == id3);
	}

	/**
	 * Tests allocation of several ids
	 */
	@Test
	public void testAllocateMany() {
		for (int i = 0; i < 100 * 1000; i++) {
			allocator.allocate();
		}
		assertEquals(100000, allocator.getAllocatedIDs());
	}

	/**
	 * Tests allocation of an used id
	 */
	@Test(expected = IDAllocatorException.class)
	public void testAllocateAlreadyAllocated() {
		final int id1 = allocator.allocate();
		allocator.allocate(id1);
	}

	/**
	 * Tests id release
	 */
	@Test
	public void testRelease() {
		final int id = allocator.allocate();
		allocator.release(id);
		assertEquals(0, allocator.getAllocatedIDs());
	}

	/**
	 * Tests releasing unallocated id
	 */
	@Test(expected = IDAllocatorException.class)
	public void testReleaseUnalloc() {
		allocator.release(IDAllocator.FIRST_ID);
	}
}
