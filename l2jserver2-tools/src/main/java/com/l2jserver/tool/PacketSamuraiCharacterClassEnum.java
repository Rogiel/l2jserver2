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

import com.l2jserver.model.template.character.CharacterClass;


public class PacketSamuraiCharacterClassEnum {
	public static void main(String[] args) {
		System.out.println(createClassStatement());
	}

	private static String createClassStatement() {
		final StringBuilder builder = new StringBuilder();
		builder.append("<reader type=\"MultiValue\">\n");
		for (CharacterClass c : CharacterClass.values()) {
			if (!c.name().startsWith("DUMMY"))
				builder.append("<case val=\"" + c.id + "\" display=\""
						+ c.name() + "\" />\n");
		}
		builder.append("</reader>");
		return builder.toString();
	}
}
