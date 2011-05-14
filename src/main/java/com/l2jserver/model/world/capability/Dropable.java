package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * Defines an {@link AbstractObject} that can be dropped on the ground.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Dropable extends Positionable {
	/**
	 * When an item is dropped its position will be set as <tt>position</tt>
	 * 
	 * @param position
	 *            the position
	 */
	void drop(Coordinate position);
}
