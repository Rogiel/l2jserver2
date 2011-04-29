package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

public class NotFilter<O extends WorldObject> implements WorldObjectFilter<O> {
	private WorldObjectFilter<O> filter;

	public NotFilter(WorldObjectFilter<O> filter) {
		this.filter = filter;
	}

	@Override
	public boolean accept(O object) {
		return !filter.accept(object);
	}
}
