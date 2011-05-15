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
package com.l2jserver.tool.conversor.chartemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.factory.CollectionFactory;

public class CharacterTemplateConverter {
	private static final String JDBC_URL = "jdbc:mysql://localhost/l2j-old";
	private static final String JDBC_USERNAME = "l2j";
	private static final String JDBC_PASSWORD = "changeme";

	private static String template;

	private static Map<CharacterClass, String> parents = CollectionFactory
			.newMap(CharacterClass.class, String.class);

	public static void main(String[] args) throws SQLException, IOException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		template = IOUtils.toString(CharacterTemplateConverter.class
				.getResourceAsStream("CharacterTemplateBase.txt"));
		System.out.println("Generating template classes...");

		final Connection conn = DriverManager.getConnection(JDBC_URL,
				JDBC_USERNAME, JDBC_PASSWORD);
		try {
			final PreparedStatement st = conn
					.prepareStatement("SELECT *  FROM char_templates");
			try {
				st.execute();
				final ResultSet rs = st.getResultSet();
				while (rs.next()) {
					String[] result = generateJavaClass(rs);
					IOUtils.write(result[0], new FileOutputStream(
							"generated/template/character/" + result[1]));
				}
			} finally {
				st.close();
			}
		} finally {
			conn.close();
		}
	}

	private static String[] generateJavaClass(ResultSet rs) throws SQLException {
		String name = "";
		String template = CharacterTemplateConverter.template;
		for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
			template = replace(template, rs.getMetaData().getColumnName(i),
					rs.getString(i));
			if (rs.getMetaData().getColumnName(i).equals("ClassId")) {
				final CharacterClass c = CharacterClass.fromID(Integer
						.parseInt(rs.getString(i)));
				name = camelCase(c.name()) + "Template.java";
			}
		}
		return new String[] { template, name };
	}

	private static String replace(String template, String key, String value) {
		if (key.equals("ClassId")) {
			final CharacterClass c = CharacterClass.fromID(Integer
					.parseInt(value));
			value = "CharacterClass." + c.name();
			String parent;
			if (c.parent != null) {
				parent = parents.get(c.parent);
			} else {
				parent = "Abstract" + camelCase(c.race.name()) + "Character";
			}
			parents.put(c, camelCase(c.name()));
			template = template.replaceAll("\\$\\{parent\\}", parent);
			template = template.replaceAll("\\$\\{javaClassName\\}",
					camelCase(c.name()));
		}
		if (key.equals("RaceId"))
			value = Race.fromOption(Integer.parseInt(value)).name();
		if (key.equals("canCraft"))
			value = (value.equals("1") ? "true" : "false");

		return template.replaceAll("\\$\\{" + key + "\\}", value);
	}

	private static String camelCase(String c) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]+");
		Matcher m = p.matcher(c.replaceAll("_", " "));
		StringBuffer result = new StringBuffer();
		String word;
		while (m.find()) {
			word = m.group();
			result.append(word.substring(0, 1).toUpperCase()
					+ word.substring(1).toLowerCase());
		}
		return result.toString();
	}
}
