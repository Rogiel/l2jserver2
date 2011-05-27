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
package com.l2jserver.util.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.jaxb.CoordinateAdapter.CoordinateElement;

/**
 * This adapter converts an simple set of attributes <tt>x</tt>, <tt>y</tt> and
 * <tt>z</tt> to an {@link Coordinate} object. Please note that the tag name is
 * indifferent.
 * 
 * <pre>
 * &lt;coordinate x="200" y="100" z="0" /&gt;
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CoordinateAdapter extends
		XmlAdapter<CoordinateElement, Coordinate> {
	@Override
	public CoordinateElement marshal(Coordinate coordinate) throws Exception {
		return new CoordinateElement(coordinate.getX(), coordinate.getY(),
				coordinate.getZ());
	}

	@Override
	public Coordinate unmarshal(CoordinateElement elem) throws Exception {
		return Coordinate.fromXYZ(elem.x, elem.y, elem.z);

	}

	public static class CoordinateElement {
		@XmlAttribute(name = "x")
		public int x;
		@XmlAttribute(name = "y")
		public int y;
		@XmlAttribute(name = "z")
		public int z;

		protected CoordinateElement() {
		}

		public CoordinateElement(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}