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

import java.awt.Polygon;
import java.util.Collection;

import com.l2jserver.util.MathUtil;

/**
 * Area of free form
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PolygonArea extends AbstractArea {
	/**
	 * Collection of x points
	 */
	private final int[] xPoints;

	/**
	 * Collection of y points
	 */
	private final int[] yPoints;

	/**
	 * Polygon used to calculate isInside()
	 */
	private final Polygon poly;

	/**
	 * Creates new area from given points
	 * 
	 * @param points
	 *            list of points
	 * @param zMin
	 *            minimal z
	 * @param zMax
	 *            maximal z
	 */
	public PolygonArea(Collection<Point> points, int zMin, int zMax) {
		this(points.toArray(new Point[points.size()]), zMin, zMax);
	}

	/**
	 * Creates new area from given points
	 * 
	 * @param points
	 *            list of points
	 * @param zMin
	 *            minimal z
	 * @param zMax
	 *            maximal z
	 */
	public PolygonArea(Point[] points, int zMin, int zMax) {
		super(zMin, zMax);

		if (points.length < 3) {
			throw new IllegalArgumentException(
					"Not enough points, needed at least 3 but got "
							+ points.length);
		}

		this.xPoints = new int[points.length];
		this.yPoints = new int[points.length];

		Polygon polygon = new Polygon();
		for (int i = 0, n = points.length; i < n; i++) {
			Point p = points[i];
			polygon.addPoint(p.x, p.y);
			xPoints[i] = p.x;
			yPoints[i] = p.y;
		}
		this.poly = polygon;
	}

	@Override
	public boolean isInside2D(int x, int y) {
		return poly.contains(x, y);
	}

	@Override
	public double getDistance2D(int x, int y) {
		if (isInside2D(x, y)) {
			return 0;
		} else {
			Point cp = getClosestPoint(x, y);
			return MathUtil.getDistance(cp.x, cp.y, x, y);
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
			return MathUtil.getDistance(cp.getX(), cp.getY(), cp.getZ(), x, y,
					z);
		}
	}

	@Override
	public Point getClosestPoint(int x, int y) {

		Point closestPoint = null;
		double closestDistance = 0;

		for (int i = 0; i < xPoints.length; i++) {
			int nextIndex = i + 1;
			if (nextIndex == xPoints.length) {
				nextIndex = 0;
			}

			int p1x = xPoints[i];
			int p1y = yPoints[i];
			int p2x = xPoints[nextIndex];
			int p2y = yPoints[nextIndex];

			Point point = MathUtil.getClosestPointOnSegment(p1x, p1y, p2x, p2y,
					x, y);

			if (closestPoint == null) {
				closestPoint = point;
				closestDistance = MathUtil.getDistance(closestPoint.x,
						closestPoint.y, x, y);
			} else {
				double newDistance = MathUtil.getDistance(point.x, point.y, x,
						y);
				if (newDistance < closestDistance) {
					closestPoint = point;
					closestDistance = newDistance;
				}
			}
		}

		return closestPoint;
	}
}
