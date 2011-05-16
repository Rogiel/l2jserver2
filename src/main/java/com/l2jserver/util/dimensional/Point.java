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
package com.l2jserver.util.dimensional;

/**
 * An point is composed of an Coordinate and an angle. The angle represents the
 * facing angle of the point, that is, the direction the point is "looking".
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Point {
	/**
	 * The point coordinate
	 */
	protected final Coordinate coordinate;
	/**
	 * The point angle
	 */
	protected final double angle;

	/**
	 * Creates a new point
	 * 
	 * @param coordinate
	 *            the coordinate
	 * @param angle
	 *            the angle
	 */
	public Point(Coordinate coordinate, double angle) {
		this.coordinate = coordinate;
		this.angle = angle;
	}

	/**
	 * @return the x
	 * @see com.l2jserver.util.dimensional.Coordinate#getX()
	 */
	public int getX() {
		return coordinate.getX();
	}

	/**
	 * @return the y
	 * @see com.l2jserver.util.dimensional.Coordinate#getY()
	 */
	public int getY() {
		return coordinate.getY();
	}

	/**
	 * @return the z
	 * @see com.l2jserver.util.dimensional.Coordinate#getZ()
	 */
	public int getZ() {
		return coordinate.getZ();
	}

	/**
	 * @param other
	 *            the other coordinate
	 * @return the distance
	 * @see com.l2jserver.util.dimensional.Coordinate#getDistance(com.l2jserver.util.dimensional.Coordinate)
	 */
	public double getDistance(Coordinate other) {
		return coordinate.getDistance(other);
	}

	/**
	 * @param other
	 *            the other point
	 * @return the distance
	 * @see com.l2jserver.util.dimensional.Coordinate#getDistance(com.l2jserver.util.dimensional.Coordinate)
	 */
	public double getDistance(Point other) {
		return coordinate.getDistance(other.coordinate);
	}

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Creates a new instance from the 3 points and an angle
	 * 
	 * @param x
	 *            the x point
	 * @param y
	 *            the y point
	 * @param z
	 *            the z point
	 * @param angle
	 *            the angle
	 * @return the new {@link Point} object created
	 */
	public static Point fromXYZA(int x, int y, int z, double angle) {
		return new Point(Coordinate.fromXYZ(x, y, z), angle);
	}

	/**
	 * Creates a new instance from the 3 points. The angle will be zero.
	 * 
	 * @param x
	 *            the x point
	 * @param y
	 *            the y point
	 * @param z
	 *            the z point
	 * @return the new {@link Point} object created
	 */
	public static Point fromXYZ(int x, int y, int z) {
		return fromXYZA(x, y, z, 0);
	}
}
