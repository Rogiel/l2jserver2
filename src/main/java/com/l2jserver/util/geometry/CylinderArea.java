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

import com.l2jserver.util.MathUtil;

/**
 * This class implements cylinder area
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CylinderArea extends AbstractArea {
	/**
	 * Center of cylinder
	 */
	private final int centerX;
	/**
	 * Center of cylinder
	 */
	private final int centerY;
	/**
	 * Cylinder radius
	 */
	private final int radius;

	/**
	 * Creates new cylinder with given radius
	 * 
	 * @param center
	 *            center of the circle
	 * @param radius
	 *            radius of the circle
	 * @param minZ
	 *            min z
	 * @param maxZ
	 *            max z
	 */
	public CylinderArea(Point center, int radius, int minZ, int maxZ) {
		this(center.x, center.y, radius, minZ, maxZ);
	}

	/**
	 * Creates new cylider with given radius
	 * 
	 * @param x
	 *            center coord
	 * @param y
	 *            center coord
	 * @param radius
	 *            radius of the circle
	 * @param minZ
	 *            min z
	 * @param maxZ
	 *            max z
	 */
	public CylinderArea(int x, int y, int radius, int minZ, int maxZ) {
		super(minZ, maxZ);
		this.centerX = x;
		this.centerY = y;
		this.radius = radius;
	}

	@Override
	public boolean isInside2D(int x, int y) {
		return MathUtil.getDistance(centerX, centerY, x, y) < radius;
	}

	@Override
	public double getDistance2D(int x, int y) {
		if (isInside2D(x, y)) {
			return 0;
		} else {
			return Math.abs(MathUtil.getDistance(centerX, centerY, x, y)
					- radius);
		}
	}

	@Override
	public double getDistance3D(int x, int y, int z) {
		if (isInside3D(x, y, z)) {
			return 0;
		} else if (isInsideZ(z)) {
			return getDistance2D(x, y);
		} else {
			if (z < getMinZ()) {
				return MathUtil.getDistance(centerX, centerY, getMinZ(), x, y,
						z);
			} else {
				return MathUtil.getDistance(centerX, centerY, getMaxZ(), x, y,
						z);
			}
		}
	}

	@Override
	public Point getClosestPoint(int x, int y) {
		if (isInside2D(x, y)) {
			return new Point(x, y);
		} else {
			int vX = x - this.centerX;
			int vY = y - this.centerY;
			double magV = MathUtil.getDistance(centerX, centerY, x, y);
			double pointX = centerX + vX / magV * radius;
			double pointY = centerY + vY / magV * radius;
			return new Point((int) Math.round(pointX), (int) Math.round(pointY));
		}
	}
}
