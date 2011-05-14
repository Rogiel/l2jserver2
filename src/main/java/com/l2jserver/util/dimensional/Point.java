package com.l2jserver.util.dimensional;

import org.apache.commons.math.geometry.Rotation;

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
	 * Te point rotation
	 */
	protected final Rotation rotation;

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
		this.rotation = new Rotation(coordinate.vector, angle);
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
	 * @see org.apache.commons.math.geometry.Rotation#getAngle()
	 */
	public double getAngle() {
		return rotation.getAngle();
	}

	/**
	 * @param other
	 *            the other point
	 * @return the angle difference between the two points
	 * @see org.apache.commons.math.geometry.Rotation#distance(Rotation,
	 *      Rotation)
	 */
	public double getAngleDifference(Point other) {
		return Rotation.distance(this.rotation, other.rotation);
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @return the rotation
	 */
	public Rotation getRotation() {
		return rotation;
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
