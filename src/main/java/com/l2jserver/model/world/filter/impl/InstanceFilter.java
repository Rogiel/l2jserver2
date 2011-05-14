package com.l2jserver.model.world.filter.impl;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.WorldObjectFilter;

/**
 * Filter object based on their types
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the instance type
 */
public class InstanceFilter<T extends WorldObject> implements
		WorldObjectFilter<T> {
	/**
	 * The object's type
	 */
	private final Class<?> type;

	/**
	 * Creates a new instance
	 * 
	 * @param instance
	 *            the instance type
	 */
	public InstanceFilter(Class<?> instance) {
		this.type = instance;
	}

	@Override
	public boolean accept(T other) {
		if (other == null)
			return false;
		return type.isInstance(other);
	}
}
