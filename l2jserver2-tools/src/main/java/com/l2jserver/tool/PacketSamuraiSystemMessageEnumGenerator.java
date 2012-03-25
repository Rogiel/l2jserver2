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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.l2jserver.service.network.model.SystemMessage;

public class PacketSamuraiSystemMessageEnumGenerator {
	public static void main(String[] args) throws IOException {
		System.out.println(createClassStatement());
		Files.write(Paths.get("generated", "SystemMessageEnum.txt"),
				createClassStatement().getBytes());
	}

	private static String createClassStatement() {
		final StringBuilder builder = new StringBuilder();
		builder.append("<reader type=\"MultiValue\">\n");
		for (SystemMessage c : SystemMessage.values()) {
			builder.append("<case val=\"" + c.id + "\" display=\"" + c.name()
					+ "\" />\n");
		}
		builder.append("</reader>");
		return builder.toString();
	}
}
