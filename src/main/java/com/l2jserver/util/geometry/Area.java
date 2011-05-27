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
package com.l2jserver.util.geometry;

/**
 * Basic interface for all areas.<br>
 * It should be implemented in different ways for performance reasons.<br>
 * For instance, we don't need complex math for squares or circles, but we need
 * it for more complex polygons.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Area {
	/**
	 * Returns true if point is inside area ignoring z value
	 * 
	 * @param point
	 *            point to check
	 * @return point is inside or not
	 */
	boolean isInside2D(Point point);

	/**
	 * Returns true if coords are inside area ignoring z value
	 * 
	 * @param x
	 *            x coord
	 * @param y
	 *            y coord
	 * @return coords are inside or not
	 */
	boolean isInside2D(int x, int y);

	/**
	 * Returns true if point is inside area
	 * 
	 * @param point
	 *            point to check
	 * @return true if point is inside
	 */
	boolean isInside3D(Point3D point);

	/**
	 * Returns true if coors are inside area
	 * 
	 * @param x
	 *            x coord
	 * @param y
	 *            y coord
	 * @param z
	 *            z coord
	 * @return true if coords are inside
	 */
	boolean isInside3D(int x, int y, int z);

	/**
	 * Checks if z coord is insize
	 * 
	 * @param point
	 *            point to check
	 * @return is z inside or not
	 */
	boolean isInsideZ(Point3D point);

	/**
	 * Checks is z coord is inside
	 * 
	 * @param z
	 *            z coord
	 * @return is z inside or not
	 */
	boolean isInsideZ(int z);

	/**
	 * Returns distance from point to closest point of this area ignoring z.<br>
	 * Returns 0 if point is inside area.
	 * 
	 * @param point
	 *            point to calculate distance from
	 * @return distance or 0 if is inside area
	 */
	double getDistance2D(Point point);

	/**
	 * Returns distance from point to closest point of this area ignoring z.<br>
	 * Returns 0 point is inside area.
	 * 
	 * @param x
	 *            x coord
	 * @param y
	 *            y coord
	 * @return distance or 0 if is inside area
	 */
	double getDistance2D(int x, int y);

	/**
	 * Returns distance from point to this area.<br>
	 * Returns 0 if is inside.
	 * 
	 * @param point
	 *            point to check
	 * @return distance or 0 if is inside
	 */
	double getDistance3D(Point3D point);

	/**
	 * Returns distance from coords to this area
	 * 
	 * @param x
	 *            x coord
	 * @param y
	 *            y coord
	 * @param z
	 *            z coord
	 * @return distance or 0 if is inside
	 */
	double getDistance3D(int x, int y, int z);

	/**
	 * Returns closest point of area to given point.<br>
	 * Returns point with coords = point arg if is inside
	 * 
	 * @param point
	 *            point to check
	 * @return closest point
	 */
	Point getClosestPoint(Point point);

	/**
	 * Returns closest point of area to given coords.<br>
	 * Returns point with coords x and y if coords are inside
	 * 
	 * @param x
	 *            x coord
	 * @param y
	 *            y coord
	 * @return closest point
	 */
	Point getClosestPoint(int x, int y);

	/**
	 * Returns closest point of area to given point.<br>
	 * Works exactly like {@link #getClosestPoint(int, int)} if
	 * {@link #isInsideZ(int)} returns true.<br>
	 * In other case closest z edge is set as z coord.
	 * 
	 * @param point
	 *            point to check
	 * @return closest point of area to point
	 */
	Point3D getClosestPoint(Point3D point);

	/**
	 * Returns closest point of area to given coords.<br>
	 * Works exactly like {@link #getClosestPoint(int, int)} if
	 * {@link #isInsideZ(int)} returns true.<br>
	 * In other case closest z edge is set as z coord.
	 * 
	 * @param x
	 *            x coord
	 * @param y
	 *            y coord
	 * @param z
	 *            z coord
	 * @return closest point of area to point
	 */
	Point3D getClosestPoint(int x, int y, int z);

	/**
	 * Return minimal z of this area
	 * 
	 * @return minimal z of this area
	 */
	int getMinZ();

	/**
	 * Returns maximal z of this area
	 * 
	 * @return maximal z of this area
	 */
	int getMaxZ();
}
