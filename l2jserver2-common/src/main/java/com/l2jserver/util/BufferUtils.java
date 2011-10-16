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
package com.l2jserver.util;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * This is an Netty {@link ChannelBuffer} utility class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BufferUtils {
	/**
	 * Reads an unicode string from the buffer
	 * 
	 * @param buffer
	 *            the buffer
	 * @return the read unicode string
	 */
	public static final String readString(ChannelBuffer buffer) {
		char[] str = new char[buffer.readableBytes()];
		int index = 0;
		char c;
		while ((c = buffer.readChar()) != 0) {
			str[index++] = c;
		}
		return String.valueOf(Arrays.copyOfRange(str, 0, index));
	}

	/**
	 * Writes an unicode string to the buffer
	 * 
	 * @param buffer
	 *            the buffer
	 * @param str
	 *            the string
	 */
	public static final void writeString(ChannelBuffer buffer, String str) {
		if (str != null && str.length() > 0) {
			final int len = str.length();
			for (int i = 0; i < len; i++) {
				buffer.writeChar(str.charAt(i));
			}
		}
		buffer.writeShort(0);
	}
}
