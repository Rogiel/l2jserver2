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

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.math.geometry.Vector3D;

/**
 * Represents an coordinate in the game world.
 * <p>
 * Each coordinate has 3 points: x, y and z.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Coordinate {
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

	/**
	 * @return the x cord
	 */
	public int getX() {
		return (int) vector.getX();
	}

	/**
	 * @return the y cord
	 */
	public int getY() {
		return (int) vector.getY();
	}

	/**
	 * @return the z cord
	 */
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
	 * Calculates the squared distance between <tt>this</tt> coordinate and
	 * <tt>other</tt>. This method is slighter faster then
	 * {@link #getDistance(Coordinate)}.
	 * 
	 * @param other
	 *            the other coordinate
	 * @return the calculated distance
	 */
	public double getDistanceSquared(Coordinate other) {
		return Vector3D.distanceSq(vector, other.vector);
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

	@Override
	public String toString() {
		return "Coordinate [" + vector + "]";
	}

	/**
	 * @return the {@link Point3D} representing this {@link Coordinate}
	 */
	public Point3D toPoint() {
		return new Point3D(this, 0);
	}
}
