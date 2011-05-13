package com.l2jserver.model.id.object.iterator;

import java.util.Iterator;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.util.ArrayIterator;

/**
 * This {@link Iterator} will iterate trough another {@link Iterator} which
 * return {@link ObjectID} instances. For each ID, the
 * {@link ObjectID#getObject()} method will be called and its result will be
 * returned.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the object type
 */
public class WorldObjectIterator<T extends WorldObject> implements Iterator<T> {
	private final Iterator<? extends ObjectID<T>> ids;

	public WorldObjectIterator(ObjectID<T>... ids) {
		this(new ArrayIterator<ObjectID<T>>(ids));
	}

	public WorldObjectIterator(Iterator<? extends ObjectID<T>> ids) {
		this.ids = ids;
	}

	@Override
	public boolean hasNext() {
		return ids.hasNext();
	}

	@Override
	public T next() {
		return ids.next().getObject();
	}

	@Override
	public void remove() {
		ids.remove();
	}
}
