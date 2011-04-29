package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

public final class WorldFilters {
	public static final <O extends WorldObject> WorldObjectFilter<O> and(
			WorldObjectFilter<O>... filters) {
		return new AndFilter<O>(filters);
	}

	public static final <O extends WorldObject> WorldObjectFilter<O> or(
			WorldObjectFilter<O>... filters) {
		return new OrFilter<O>(filters);
	}

	public static final <O extends WorldObject> WorldObjectFilter<O> notf(
			WorldObjectFilter<O> filter) {
		return new NotFilter<O>(filter);
	}
}
