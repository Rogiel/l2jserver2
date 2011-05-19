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

import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.util.PrimeFinder;

/**
 * The {@link BitSet} id allocator allocates new IDs backed by an {@link BitSet}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BitSetIDAllocator implements IDAllocator {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(BitSetIDAllocator.class);

	/**
	 * Lock to guarantee synchronization
	 */
	private Lock lock = new ReentrantLock();

	/**
	 * Available IDs
	 */
	private BitSet ids = new BitSet();
	/**
	 * Amount of free ids
	 */
	private AtomicInteger freeIdCount = new AtomicInteger();
	/**
	 * Next free ID
	 */
	private AtomicInteger nextId = new AtomicInteger();

	/**
	 * Initializes this allocator
	 */
	public void init() {
		ids = new BitSet(PrimeFinder.nextPrime(100000));
		ids.clear();
		freeIdCount = new AtomicInteger(ALLOCABLE_IDS);

		nextId = new AtomicInteger(ids.nextClearBit(0));
		log.info("BitSet IDAllocator initialized. Next available ID is {}",
				nextId.get());
	}

	@Override
	public void allocate(int id) {
		if (ids.get(id - FIRST_ID))
			throw new IDAllocatorException("ID not allocated");
		log.debug("Allocating ID {}", id);
		lock.lock();
		try {
			if (id < FIRST_ID)
				return;
			ids.set(id - FIRST_ID);
			nextId = new AtomicInteger(ids.nextClearBit(0));
		} finally {
			lock.unlock();
		}
	}

	@Override
	public int allocate() {
		lock.lock();
		try {
			final int newID = nextId.get();
			ids.set(newID);
			freeIdCount.decrementAndGet();

			if (log.isDebugEnabled())
				log.debug("Allocated a new ID {}", newID + FIRST_ID);

			int nextFree = ids.nextClearBit(newID);

			if (nextFree < 0) {
				nextFree = ids.nextClearBit(0);
			}
			if (nextFree < 0) {
				if (ids.size() < ALLOCABLE_IDS) {
					increaseBitSetCapacity();
				} else {
					log.error("ID exhaustion");
					throw new IDAllocatorException("ID exhaustion");
				}
			}

			nextId.set(nextFree);
			return newID + FIRST_ID;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void release(int id) {
		if (id < FIRST_ID)
			throw new IDAllocatorException(
					"Can't release ID, smaller then initial ID");
		if (!ids.get(id - FIRST_ID))
			throw new IDAllocatorException("ID not allocated");

		log.debug("Releasing allocated ID {}", id);
		lock.lock();
		try {
			ids.clear(id - FIRST_ID);
			freeIdCount.incrementAndGet();
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public void clear() {
		ids.clear();
	}

	/**
	 * Increases the {@link BitSet} capacity
	 */
	private void increaseBitSetCapacity() {
		log.debug("Increasing BitSet capacity from {}", ids.size());
		BitSet newBitSet = new BitSet(
				PrimeFinder.nextPrime((getAllocatedIDs() * 11) / 10));
		newBitSet.or(ids);
		ids = newBitSet;
	}

	@Override
	public int getAllocatedIDs() {
		return ALLOCABLE_IDS - getFreeIDs();
	}

	@Override
	public int getFreeIDs() {
		return freeIdCount.get();
	}
}
