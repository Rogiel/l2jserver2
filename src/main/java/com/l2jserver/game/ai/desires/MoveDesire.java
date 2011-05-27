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
package com.l2jserver.game.ai.desires;

import com.l2jserver.model.world.Actor;
import com.l2jserver.util.geometry.Point3D;

/**
 * This class indicates that {@link Actor} wants to move somewhere
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public final class MoveDesire extends AbstractDesire {
	/**
	 * Target of this desire
	 */
	protected final Point3D point;

	/**
	 * Creates new move desire. Target can't be changed
	 * 
	 * @param point
	 *            where to move
	 * @param desirePower
	 *            initial attack power
	 */
	protected MoveDesire(Point3D point, int desirePower) {
		super(desirePower);
		this.point = point;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof MoveDesire))
			return false;

		MoveDesire that = (MoveDesire) o;

		return point.equals(that.point);
	}

	@Override
	public int hashCode() {
		return point.hashCode();
	}

	/**
	 * Returns target of this desire
	 * 
	 * @return target of this desire
	 */
	public Point3D getTarget() {
		return point;
	}
}
