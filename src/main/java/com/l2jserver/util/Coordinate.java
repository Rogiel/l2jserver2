package com.l2jserver.util;

/**
 * Represents an coordinate in the game world.
 * <p>
 * Each coordinate has 3 points: x, y and z.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Coordinate {
	/**
	 * The X point
	 */
	private final int x;
	/**
	 * The Y point
	 */
	private final int y;
	/**
	 * The Z point
	 */
	private final int z;

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
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @return the x point
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y point
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the z point
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Calculates the distance between <tt>this</tt> coordinate and
	 * <tt>other</tt>
	 * 
	 * @param other
	 *            the other coodinate
	 * @return the computed distance
	 */
	public int getDistance(Coordinate other) {
		// TODO calculation
		return x + y + z;
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
