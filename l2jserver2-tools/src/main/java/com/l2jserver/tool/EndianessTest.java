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
package com.l2jserver.tool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class EndianessTest {
	/**
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		final ByteBuffer bigEndian = ByteBuffer.allocate(8);
		bigEndian.order(ByteOrder.BIG_ENDIAN);
		bigEndian.putDouble(20);
		System.out.println(Arrays.toString(bigEndian.array()));

		final ByteBuffer littleEndian = ByteBuffer.allocate(8);
		littleEndian.order(ByteOrder.LITTLE_ENDIAN);
		littleEndian.putDouble(20);
		System.out.println(Arrays.toString(littleEndian.array()));
	}
}
