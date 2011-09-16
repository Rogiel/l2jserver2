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
package com.l2jserver.service.game.map;

import com.l2jserver.util.geometry.Point;

/**
 * Tiles are 2D only
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Tile {
	/**
	 * The tile point
	 */
	private final Point point;
	/**
	 * The tile flags
	 */
	private final byte flags;

	/**
	 * @param point
	 *            the file point
	 * @param flags
	 *            the tile flags
	 */
	public Tile(Point point, byte flags) {
		this.point = point;
		this.flags = flags;
	}

	/**
	 * @return the point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @return the flags
	 */
	public byte getFlags() {
		return flags;
	}
}
