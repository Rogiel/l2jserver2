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
package com.l2jserver.model.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.l2jserver.util.factory.CollectionFactory;

public class NPCOldTemplateConverter {
	private static final String JDBC_URL = "jdbc:mysql://localhost/l2j-old";
	private static final String JDBC_USERNAME = "l2j";
	private static final String JDBC_PASSWORD = "changeme";

	private static String template;

	private static Set<String> names = CollectionFactory.newSet();

	public static void main(String[] args) throws SQLException, IOException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		template = IOUtils.toString(NPCOldTemplateConverter.class
				.getResourceAsStream("NPCTemplateBase.txt"));
		System.out.println("Generating template classes...");

		final Connection conn = DriverManager.getConnection(JDBC_URL,
				JDBC_USERNAME, JDBC_PASSWORD);
		try {
			final PreparedStatement st = conn
					.prepareStatement("SELECT *  FROM npc");
			try {
				st.execute();
				final ResultSet rs = st.getResultSet();
				// final Set<String> directories = CollectionFactory.newSet();
				while (rs.next()) {
					String[] result = generateJavaClass(rs);
					if (result == null)
						continue;
					// directories.add(result[1]);
					// System.out.println(result[0]);
					// System.exit(0);
					final File file = new File("generated/script/template/npc"
							+ result[1] + result[2]);
					file.getParentFile().mkdirs();
					IOUtils.write(result[0], new FileOutputStream(file));
				}
				// System.out.println(directories);
			} finally {
				st.close();
			}
		} finally {
			conn.close();
		}
	}

	private static String[] generateJavaClass(ResultSet rs) throws SQLException {
		String npcName = "";
		@SuppressWarnings("unused")
		String npcClass = "";
		String npcTitle = "";
		String npcType = "";

		String name = "";
		String folder = "";
		String template = NPCOldTemplateConverter.template;
		for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
			template = replace(template, rs.getMetaData().getColumnName(i),
					rs.getString(i));
			if (rs.getMetaData().getColumnName(i).equals("name")) {
				npcName = rs.getString(i);
			} else if (rs.getMetaData().getColumnName(i).equals("class")) {
				npcClass = rs.getString(i);
			} else if (rs.getMetaData().getColumnName(i).equals("title")) {
				npcTitle = rs.getString(i);
			} else if (rs.getMetaData().getColumnName(i).equals("type")) {
				npcType = rs.getString(i);
			}
		}

		if (npcType.equals("L2Pet"))
			return null;
		// generate class and file name
		if (npcName.length() == 0)
			return null;

		String className = camelCase(prepareName(npcName));
		if (npcTitle.equals("Gatekeeper")) {
			className += npcTitle;
		}

		if (!names.add(className)) {
			int i = 2;
			String nameAppend = className + i;
			while (names.add(nameAppend) == false) {
				i++;
				nameAppend = className + i;
			}
			className = nameAppend;

			// System.out.println("Name already taken: " + className);
			// System.exit(0);
		}
		className += "Template";

		folder = "";
		name = className + ".java";

		folder = createFolder(npcType);
		if (folder.contains("villagemaster")) {
			folder = "villagemaster";
		}

		String packageName = "";
		if (folder.length() > 0)
			packageName = "." + folder;
		template = replace(template, "javaClassName", className);
		template = replace(template, "javaPackage", packageName);

		return new String[] { template, "/" + folder + "/", name };
	}

	private static String replace(String template, String key, String value) {
		if (key.equals("class")) {

			// template = template.replaceAll("\\$\\{javaClassName\\}",
			// camelCase(c.name()));
		}
		if (key.equals("sex"))
			value = value.toUpperCase();
		if (key.equals("type"))
			template = template.replaceAll("\\$\\{javaParentClassName\\}",
					createParentType(value));
		if (key.equals("serverSideName") || key.equals("serverSideTitle")
				|| key.equals("aggro") || key.equals("targetable")
				|| key.equals("show_name") || key.equals("basestats")
				|| key.equals("serverSideName") || key.equals("serverSideName")
				|| key.equals("serverSideName"))
			value = (value.equals("1") ? "true" : "false");

		return template.replaceAll("\\$\\{" + key + "\\}", value);
	}

	private static String prepareName(String name) {
		return name.replaceAll("'", "");
	}

	private static String camelCase(String c) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]+");
		Matcher m = p.matcher(c.replaceAll("_", " ").replaceAll("\\.", " "));
		StringBuffer result = new StringBuffer();
		String word;
		while (m.find()) {
			word = m.group();
			result.append(word.substring(0, 1).toUpperCase()
					+ word.substring(1).toLowerCase());
		}
		return result.toString();
	}

	private static String createParentType(String l2j) {
		if (l2j.startsWith("L2"))
			l2j = l2j.substring(2);
		if (l2j.equals("Npc"))
			return "";
		if (l2j.contains("VillageMaster"))
			return (l2j.replaceAll("VillageMaster", "") + "VillageMaster");
		if (l2j.contains("Npc"))
			l2j = l2j.replaceAll("Npc", "");
		return l2j;
	}

	private static String createFolder(String l2j) {
		if (l2j.startsWith("L2"))
			l2j = l2j.substring(2);
		if (l2j.equals("Npc"))
			return "";
		if (l2j.toLowerCase().contains("monster"))
			return "monster";
		if (l2j.toLowerCase().contains("castle"))
			return "castle";
		if (l2j.toLowerCase().contains("fort"))
			return "fort";
		if (l2j.toLowerCase().contains("xmasstree"))
			return "misc";
		return l2j.toLowerCase();
	}
}
