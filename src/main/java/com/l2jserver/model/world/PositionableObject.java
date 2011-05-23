/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.world;

import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.dimensional.Point;

/**
 * This is an abstract object that objects that can be placed in world should
 * extend.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class PositionableObject extends AbstractObject {
	/**
	 * The point this object is currently in
	 */
	private Point point;

	/**
	 * @return the coordinate point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the coordinate point to set
	 */
	public void setPoint(Point point) {
		this.point = point;
	}

	public Coordinate getPosition() {
		return point.getCoordinate();
	}

	public void setPosition(Coordinate coord) {
		this.point = new Point(coord, (point != null ? point.getAngle() : 0));
	}
}
