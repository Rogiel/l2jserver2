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

import java.awt.Rectangle;

import com.l2jserver.util.MathUtil;

/**
 * Rectangle area, most wide spread in the game
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RectangleArea extends AbstractArea {
	/**
	 * Min x point
	 */
	private final int minX;

	/**
	 * Max x point
	 */
	private final int maxX;

	/**
	 * Min y point
	 */
	private final int minY;

	/**
	 * Max y point
	 */
	private final int maxY;

	/**
	 * Creates new area from given points. Point order doesn't matter
	 * 
	 * @param p1
	 *            point
	 * @param p2
	 *            point
	 * @param p3
	 *            point
	 * @param p4
	 *            point
	 * @param minZ
	 *            minimal z
	 * @param maxZ
	 *            maximal z
	 */
	public RectangleArea(Point p1, Point p2, Point p3, Point p4, int minZ,
			int maxZ) {
		super(minZ, maxZ);

		// we should use a better way to do this
		Rectangle r = new Rectangle();
		r.add(new java.awt.Point(p1.x, p1.y));
		r.add(new java.awt.Point(p2.x, p2.y));
		r.add(new java.awt.Point(p3.x, p3.y));
		r.add(new java.awt.Point(p4.x, p4.y));

		minX = (int) r.getMinX();
		maxX = (int) r.getMaxX();
		minY = (int) r.getMinY();
		maxY = (int) r.getMaxY();
	}

	/**
	 * Creates new are from given coords
	 * 
	 * @param minX
	 *            mimal x point
	 * @param minY
	 *            minimal y point
	 * @param maxX
	 *            maximal x point
	 * @param maxY
	 *            maximal y point
	 * @param minZ
	 *            minimal z point
	 * @param maxZ
	 *            maximal z point
	 */
	public RectangleArea(int minX, int minY, int maxX, int maxY, int minZ,
			int maxZ) {
		super(minZ, maxZ);
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	@Override
	public boolean isInside2D(int x, int y) {
		return x >= minX && x <= maxX && y >= minY && y <= maxY;
	}

	@Override
	public double getDistance2D(int x, int y) {
		if (isInside2D(x, y)) {
			return 0;
		} else {
			Point cp = getClosestPoint(x, y);
			return MathUtil.getDistance(x, y, cp.x, cp.y);
		}
	}

	@Override
	public double getDistance3D(int x, int y, int z) {
		if (isInside3D(x, y, z)) {
			return 0;
		} else if (isInsideZ(z)) {
			return getDistance2D(x, y);
		} else {
			Point3D cp = getClosestPoint(x, y, z);
			return MathUtil.getDistance(x, y, z, cp.getX(), cp.getY(),
					cp.getZ());
		}
	}

	@Override
	public Point getClosestPoint(int x, int y) {

		if (isInside2D(x, y)) {
			return new Point(x, y);
		} else {
			// bottom edge
			Point closestPoint = MathUtil.getClosestPointOnSegment(minX, minY,
					maxX, minY, x, y);
			double distance = MathUtil.getDistance(x, y, closestPoint.x,
					closestPoint.y);

			// top edge
			Point cp = MathUtil.getClosestPointOnSegment(minX, maxY, maxX,
					maxY, x, y);
			double d = MathUtil.getDistance(x, y, cp.x, cp.y);
			if (d < distance) {
				closestPoint = cp;
				distance = d;
			}

			// left edge
			cp = MathUtil
					.getClosestPointOnSegment(minX, minY, minX, maxY, x, y);
			d = MathUtil.getDistance(x, y, cp.x, cp.y);
			if (d < distance) {
				closestPoint = cp;
				distance = d;
			}

			// Right edge
			cp = MathUtil
					.getClosestPointOnSegment(maxX, minY, maxX, maxY, x, y);
			d = MathUtil.getDistance(x, y, cp.x, cp.y);
			if (d < distance) {
				closestPoint = cp;
				// distance = d;
			}

			return closestPoint;
		}
	}
}
