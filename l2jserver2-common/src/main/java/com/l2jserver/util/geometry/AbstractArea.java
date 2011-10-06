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
 * Class with basic method implementation for areas.<br>
 * If possible it should be subclassed. <br>
 * In other case {@link Area} should be implemented directly
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AbstractArea implements Area {
	/**
	 * Minimal z of area
	 */
	private final int minZ;

	/**
	 * Maximal Z of area
	 */
	private final int maxZ;

	/**
	 * Creates new AbstractArea with min and max z
	 * 
	 * @param minZ
	 *            min z
	 * @param maxZ
	 *            max z
	 */
	protected AbstractArea(int minZ, int maxZ) {
		if (minZ > maxZ) {
			throw new IllegalArgumentException("minZ(" + minZ + ") > maxZ("
					+ maxZ + ")");
		}
		this.minZ = minZ;
		this.maxZ = maxZ;
	}

	@Override
	public boolean isInside2D(Point point) {
		return isInside2D(point.x, point.y);
	}

	@Override
	public boolean isInside3D(Point3D point) {
		return isInside3D(point.getX(), point.getY(), point.getZ());
	}

	@Override
	public boolean isInside3D(int x, int y, int z) {
		return isInsideZ(z) && isInside2D(x, y);
	}

	@Override
	public boolean isInsideZ(Point3D point) {
		return isInsideZ(point.getZ());
	}

	@Override
	public boolean isInsideZ(int z) {
		return z >= getMinZ() && z <= getMaxZ();
	}

	@Override
	public double getDistance2D(Point point) {
		return getDistance2D(point.x, point.y);
	}

	@Override
	public double getDistance3D(Point3D point) {
		return getDistance3D(point.getX(), point.getY(), point.getZ());
	}

	@Override
	public Point getClosestPoint(Point point) {
		return getClosestPoint(point.x, point.y);
	}

	@Override
	public Point3D getClosestPoint(Point3D point) {
		return getClosestPoint(point.getX(), point.getY(), point.getZ());
	}

	@Override
	public Point3D getClosestPoint(int x, int y, int z) {
		Point closest2d = getClosestPoint(x, y);

		int zCoord;

		if (isInsideZ(z)) {
			zCoord = z;
		} else if (z < getMinZ()) {
			zCoord = getMinZ();
		} else {
			zCoord = getMaxZ();
		}

		return new Point3D(closest2d.x, closest2d.y, zCoord);
	}

	@Override
	public int getMinZ() {
		return minZ;
	}

	@Override
	public int getMaxZ() {
		return maxZ;
	}
}
