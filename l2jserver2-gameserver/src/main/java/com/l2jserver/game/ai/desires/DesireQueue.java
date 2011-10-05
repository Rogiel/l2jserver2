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
package com.l2jserver.game.ai.desires;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * This class represents desire queue, it's thread-safe. Desires can be added
 * and removed. If desire is added - previous desires will be checked, if same
 * desire found then desire previous one will be removed from the queue
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see com.l2jserver.game.ai.desires.Desire
 * @see com.l2jserver.game.ai.desires.AbstractDesire
 */
public class DesireQueue {
	/**
	 * Prioritized Queue of desires, lazy initialization.
	 */
	protected PriorityQueue<Desire> queue;

	/**
	 * Returns first element of this queue not removing it. Returns null if
	 * there is no elements.
	 * 
	 * @return first element or null
	 */
	public synchronized Desire peek() {
		return queue != null ? queue.peek() : null;
	}

	/**
	 * Removes first element from the desires list and removes it. Returns null
	 * if there are no elements.
	 * 
	 * @return first element from the desires list or null.
	 */
	public synchronized Desire poll() {
		if (queue != null) {
			return queue.poll();
		}

		return null;
	}

	/**
	 * Adds desire to the queue.
	 * <p/>
	 * <p/>
	 * When adding object this method checks first for the same object by
	 * {@link AbstractDesire#equals(Object)}, if such object found, next actions
	 * will be done:<br>
	 * <br>
	 * 1). Remove old desire instance from the list.<br>
	 * 2). Check if those desires are same instances by "==".<br>
	 * 3). If they are not the same instances, add desire power from old
	 * instance to new instance, if they are - do nothing.<br>
	 * <br>
	 * After all add new desire instance to the list.
	 * 
	 * @param desire
	 *            desire instance to add
	 */
	public synchronized void addDesire(Desire desire) {
		// Lazy initialization of desire queue
		if (queue == null) {
			queue = new PriorityQueue<Desire>();
		}

		// Iterate over the list to find similar desires
		Iterator<Desire> iterator = queue.iterator();
		while (iterator.hasNext()) {
			Desire iterated = iterator.next();

			// Find similar desires by #equals method, they can be different
			// instances.
			if (desire.equals(iterated)) {
				// Remove the old desire from the list
				iterator.remove();

				// If current desire instance was not at the list - increase
				// it's power by the value of another instance power
				// and after that add it to the list
				if (desire != iterated) {
					desire.increaseDesirePower(iterated.getDesirePower());
				}

				// Break iteration, desire list can't contain two same desires
				break;
			}
		}
		// finally add desire to the list
		queue.add(desire);
	}

	/**
	 * Removes desire from this desireQueue. If desire was removed successfully
	 * this method return true, false otherwise
	 * 
	 * @param desire
	 *            what desire to remove
	 * @return result of desire removal
	 */
	public synchronized boolean removeDesire(Desire desire) {
		return queue != null && queue.remove(desire);
	}

	/**
	 * Iterates over desires, you have to provide iteration handler and
	 * optionally filters.<br>
	 * <br>
	 * Handlers and filters can't call following methods:
	 * <ul>
	 * <li>{@link #addDesire(Desire)}</li>
	 * <li>{@link #poll()}</li>
	 * <li>{@link #removeDesire(Desire)}</li>
	 * </ul>
	 * <p/>
	 * However, method {@link #clear() can be called}.
	 * 
	 * @param handler
	 *            DesireIterationhandler that will be called on the iteration
	 * @param filters
	 *            optional filters that will prevent passing unneeded desires to
	 *            the handler
	 * @throws java.util.ConcurrentModificationException
	 *             only if called handler or filter modified this queue
	 * @see com.l2jserver.game.ai.desires.DesireIteratorFilter
	 * @see com.l2jserver.game.ai.desires.DesireIteratorFilter#isOk(Desire)
	 * @see com.l2jserver.game.ai.desires.DesireIteratorHandler
	 * @see com.l2jserver.game.ai.desires.DesireIteratorHandler#next(Desire ,
	 *      java.util.Iterator)
	 */
	public synchronized void iterateDesires(DesireIteratorHandler handler,
			DesireIteratorFilter... filters)
			throws ConcurrentModificationException {

		if (queue == null) {
			return;
		}

		Iterator<Desire> iterator = queue.iterator();
		outer: while (iterator.hasNext()) {
			Desire desire = iterator.next();

			if (filters != null && filters.length > 0) {
				for (DesireIteratorFilter filter : filters) {
					if (!filter.isOk(desire)) {
						continue outer;
					}
				}
			}

			handler.next(desire, iterator);
		}
	}

	/**
	 * Returns true if this desire list contains same desire. Desires are
	 * compared by {@link AbstractDesire#equals(Object)} method.
	 * 
	 * @param desire
	 *            what desire to search
	 * @return true if there is equal desire, false in other case.
	 */
	public synchronized boolean contains(Desire desire) {
		return queue.contains(desire);
	}

	/**
	 * Returns true if this NPC has no any desires added
	 * 
	 * @return true if this NPC has no any desires added
	 */
	public synchronized boolean isEmpty() {
		return queue == null || queue.isEmpty();
	}

	/**
	 * Clears all desires
	 */
	public synchronized void clear() {
		if (queue != null) {
			queue.clear();
		}
	}

	/**
	 * Returns size of the desire list
	 * 
	 * @return size of remaining desires
	 */
	public synchronized int size() {
		return queue == null ? 0 : queue.size();
	}
}