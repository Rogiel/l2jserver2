package com.l2jserver.model.world.filter.impl;

import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.model.world.filter.WorldObjectFilter;
import com.l2jserver.util.Coordinate;

/**
 * Filter objects that are in the <tt>range</tt> of <tt>coordinate</tt>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RangeFilter implements WorldObjectFilter<Positionable> {
	/**
	 * The coordinate point
	 */
	private final Coordinate coordinate;
	/**
	 * The desired maximum distance of the object
	 */
	private final int range;

	/**
	 * Creates a new instance
	 * 
	 * @param coordinate
	 *            the coordinate as base for range search
	 * @param range
	 *            the desired maximum distance of the object
	 */
	public RangeFilter(final Coordinate coordinate, final int range) {
		this.coordinate = coordinate;
		this.range = range;
	}

	/**
	 * Creates a new instance
	 * 
	 * @param positionable
	 *            the base object
	 * @param range
	 *            the desired maximum distance of the object
	 */
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
