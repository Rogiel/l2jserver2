package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

public class OrFilter<O extends WorldObject> implements WorldFilter<O> {
	private WorldFilter<O>[] filters;

	public OrFilter(WorldFilter<O>... filters) {
		this.filters = filters;
	}

	@Override
	public boolean accept(O object) {
		for(final WorldFilter<O> filter : filters) {
			if(filter.accept(object))
				return true;
		}
		return false;
	}
}
