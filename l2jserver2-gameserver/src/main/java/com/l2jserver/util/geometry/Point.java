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
package com.l2jserver.util.geometry;

/**
 * An two dimensional point
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Point {
	/**
	 * The X axis
	 */
	public final int x;
	/**
	 * The Y axis
	 */
	public final int y;

	/**
	 * Creates a new two dimensional point
	 * 
	 * @param x
	 *            the x axis
	 * @param y
	 *            the y axis
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Creates a new point from X and Y axis
	 * 
	 * @param x
	 *            the x axis
	 * @param y
	 *            the y axis
	 * @return the new created Point
	 */
	public static final Point fromXY(int x, int y) {
		return new Point(x, y);
	}
}
