package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

public class NotFilter<O extends WorldObject> implements WorldFilter<O> {
	private WorldFilter<O> filter;

	public NotFilter(WorldFilter<O> filter) {
		this.filter = filter;
	}

	@Override
	public boolean accept(O object) {
		return !filter.accept(object);
	}
}
