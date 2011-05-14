package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.util.dimensional.Point;

/**
 * Defines an {@link AbstractObject} that can be positioned and pointed in the
 * world.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Pointable extends Positionable {
	Point getPoint();

	void setPoint(Point point);
}
