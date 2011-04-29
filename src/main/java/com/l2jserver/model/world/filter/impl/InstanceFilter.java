package com.l2jserver.model.world.filter.impl;

import com.l2jserver.model.world.WorldObject;
import com.l2jserver.model.world.filter.WorldObjectFilter;

public class InstanceFilter<T extends WorldObject> implements WorldObjectFilter<T> {
	private final Class<?> type;

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
