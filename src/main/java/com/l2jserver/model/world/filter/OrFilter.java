package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

public class OrFilter<O extends WorldObject> implements WorldObjectFilter<O> {
	private WorldObjectFilter<O>[] filters;

	public OrFilter(WorldObjectFilter<O>... filters) {
		this.filters = filters;
	}

	@Override
	public boolean accept(O object) {
		for (final WorldObjectFilter<O> filter : filters) {
			if (filter.accept(object))
				return true;
		}
		return false;
	}
}
