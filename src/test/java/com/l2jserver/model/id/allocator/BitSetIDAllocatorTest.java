package com.l2jserver.model.id.allocator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class BitSetIDAllocatorTest {
	private final BitSetIDAllocator allocator = new BitSetIDAllocator();

	@Before
	public void tearUp() {
		allocator.init();
	}

	@Test
	public void testAllocate() {
		final int id1 = allocator.allocate();
		final int id2 = allocator.allocate();
		assertFalse(id1 == id2);
		assertEquals(IDAllocator.FIRST_ID, id1);
		assertEquals(IDAllocator.FIRST_ID + 1, id2);
	}

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

	@Test
	public void testAllocateMany() {
		for (int i = 0; i < 100 * 1000; i++) {
			allocator.allocate();
		}
		assertEquals(100000, allocator.getAllocatedIDs());
	}

	@Test(expected = IDAllocatorException.class)
	public void testAllocateAlreadyAllocated() {
		final int id1 = allocator.allocate();
		allocator.allocate(id1);
	}

	@Test
	public void testRelease() {
		final int id = allocator.allocate();
		allocator.release(id);
	}

	@Test(expected = IDAllocatorException.class)
	public void testReleaseUnalloc() {
		allocator.release(IDAllocator.FIRST_ID);
	}
}
