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
package com.l2jserver.service.game.map.pathing;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.geometry.Point3D;

/**
 * Base {@link Path} for implementations
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AbstractPath implements Path {
	/**
	 * The source point
	 */
	protected final Point3D source;
	/**
	 * The list of all points
	 */
	protected final List<Point3D> points = CollectionFactory.newList();

	/**
	 * Creates a new instance
	 * 
	 * @param source
	 *            the source point
	 * @param points
	 *            all pathing points
	 */
	public AbstractPath(Point3D source, Point3D... points) {
		this.source = source;
		Collections.addAll(this.points, points);
	}

	@Override
	public double getDistance() {
		int distance = 0;
		Point3D last = source;
		for (final Point3D point : points) {
			distance += point.getDistance(last);
			last = point;
		}
		return distance;
	}

	@Override
	public Point3D getSource() {
		return source;
	}

	@Override
	public Point3D getTarget() {
		return points.get(points.size() - 1);
	}

	@Override
	public int getPointCount() {
		return points.size();
	}

	@Override
	public Iterator<Point3D> iterator() {
		return points.iterator();
	}
}
