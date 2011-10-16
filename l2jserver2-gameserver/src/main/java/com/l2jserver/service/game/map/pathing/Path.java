/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.map.pathing;

import com.l2jserver.util.geometry.Point3D;

/**
 * This interface represents the path an given object needs to do in order to
 * avoid the obstacles in from of it.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Path extends Iterable<Point3D> {
	/**
	 * @return the starting point
	 */
	Point3D getSource();

	/**
	 * @return the last point
	 */
	Point3D getTarget();

	/**
	 * @return the amount of points in this path
	 */
	int getPointCount();

	/**
	 * Calculates the path real distance. This is actually the sum of distance
	 * of all nodes.
	 * 
	 * @return the distance
	 */
	double getDistance();
}
