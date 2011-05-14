package com.l2jserver.util.dimensional;

import org.apache.commons.math.geometry.Vector3D;

/**
 * Represents an coordinate in the game world.
 * <p>
 * Each coordinate has 3 points: x, y and z.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Coordinate {
	private static final long serialVersionUID = 1L;

	/**
	 * The backing vector of this Coordinate
	 */
	protected final Vector3D vector;

	/**
	 * Creates a new coordinate
	 * 
	 * @param x
	 *            the x point
	 * @param y
	 *            the y point
	 * @param z
	 *            the z point
	 */
	protected Coordinate(int x, int y, int z) {
		this.vector = new Vector3D(x, y, z);
	}

	public int getX() {
		return (int) vector.getX();
	}

	public int getY() {
		return (int) vector.getY();
	}

	public int getZ() {
		return (int) vector.getZ();
	}

	/**
	 * Calculates the distance between <tt>this</tt> coordinate and
	 * <tt>other</tt>
	 * 
	 * @param other
	 *            the other coordinate
	 * @return the calculated distance
	 */
	public double getDistance(Coordinate other) {
		return Vector3D.distance(vector, other.vector);
	}

	/**
	 * Creates a new instance from the 3 points
	 * 
	 * @param x
	 *            the x point
	 * @param y
	 *            the y point
	 * @param z
	 *            the z point
	 * @return the new {@link Coordinate} object created
	 */
	public static Coordinate fromXYZ(int x, int y, int z) {
		return new Coordinate(x, y, z);
	}
}
