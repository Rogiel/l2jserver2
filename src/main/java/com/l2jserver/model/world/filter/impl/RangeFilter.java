package com.l2jserver.model.world.filter.impl;

import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.model.world.filter.WorldFilter;
import com.l2jserver.util.Coordinate;

public class RangeFilter implements WorldFilter<Positionable> {
	private final Coordinate coordinate;
	private final int range;

	public RangeFilter(final Coordinate coordinate, final int range) {
		this.coordinate = coordinate;
		this.range = range;
	}

	public RangeFilter(final Positionable positionable, final int range) {
		this(positionable.getPosition(), range);
	}

	@Override
	public boolean accept(Positionable other) {
		if (other == null)
			return false;
		return other.getPosition().getDistance(coordinate) <= range;
	}
}
