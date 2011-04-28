package com.l2jserver.model.world.filter;

import com.l2jserver.model.world.WorldObject;

public final class WorldFilters {
	public static final <O extends WorldObject> WorldFilter<O> and(
			WorldFilter<O>... filters) {
		return new AndFilter<O>(filters);
	}

	public static final <O extends WorldObject> WorldFilter<O> or(
			WorldFilter<O>... filters) {
		return new OrFilter<O>(filters);
	}

	public static final <O extends WorldObject> WorldFilter<O> notf(
			WorldFilter<O> filter) {
		return new NotFilter<O>(filter);
	}
}
