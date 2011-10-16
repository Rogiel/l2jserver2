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
package com.l2jserver.model.template.character;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.template.character.CharacterTemplate;
import com.l2jserver.model.template.character.CharacterTemplate.CharacterStatsMetadata;
import com.l2jserver.model.template.character.CharacterTemplate.CollitionMetadataContainer;
import com.l2jserver.model.template.character.CharacterTemplate.CharacterStatsMetadata.AttackMetadata;
import com.l2jserver.model.template.character.CharacterTemplate.CharacterStatsMetadata.BaseMetadata;
import com.l2jserver.model.template.character.CharacterTemplate.CharacterStatsMetadata.DefenseMetadata;
import com.l2jserver.model.template.character.CharacterTemplate.CharacterStatsMetadata.MoveMetadata;
import com.l2jserver.model.template.character.CharacterTemplate.CharacterStatsMetadata.Stat;
import com.l2jserver.model.template.character.CharacterTemplate.CharacterStatsMetadata.AttackMetadata.AttackValueMetadata;
import com.l2jserver.model.template.character.CharacterTemplate.CharacterStatsMetadata.DefenseMetadata.DefenseValueMetadata;
import com.l2jserver.model.template.character.CharacterTemplate.CollitionMetadataContainer.CollisionMetadata;

/**
 * The need to use this package to get access to protected fields.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterTemplateConverter {
	private static final String JDBC_URL = "jdbc:mysql://localhost/l2jlegacy";
	private static final String JDBC_USERNAME = "l2j";
	private static final String JDBC_PASSWORD = "changeme";

	public static void main(String[] args) throws SQLException, IOException,
			ClassNotFoundException, JAXBException {
		Class.forName("com.mysql.jdbc.Driver");
		final File target = new File("generated/template");

		System.out.println("Generating template classes...");

		final JAXBContext c = JAXBContext.newInstance(CharacterTemplate.class);
		c.generateSchema(new SchemaOutputResolver() {
			@Override
			public Result createOutput(String namespaceUri,
					String suggestedFileName) throws IOException {
				return new StreamResult(new File(target, "character.xsd"));
			}
		});

		final Marshaller m = c.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		final Connection conn = DriverManager.getConnection(JDBC_URL,
				JDBC_USERNAME, JDBC_PASSWORD);
		try {
			final PreparedStatement st = conn
					.prepareStatement("SELECT * FROM char_templates "
							+ "LEFT JOIN lvlupgain ON (char_templates.Classid = lvlupgain.classid)");
			try {
				st.execute();
				final ResultSet rs = st.getResultSet();
				while (rs.next()) {
					CharacterTemplate t = fillTemplate(rs);

					final File file = new File(target, "character/"
							+ camelCase(t.getCharacterClass().name()) + ".xml");
					file.getParentFile().mkdirs();

					m.marshal(t, file);

					// m.marshal(t, System.out);
					// System.exit(0);
				}
			} finally {
				st.close();
			}
		} finally {
			conn.close();
		}
	}

	private static CharacterTemplate fillTemplate(ResultSet rs)
			throws SQLException {
		final CharacterTemplate t = new CharacterTemplate();
		
		t.id = new CharacterTemplateID(rs.getInt("Classid"), null);

		t.stats = new CharacterStatsMetadata();
		t.stats.hp = new Stat();
		t.stats.hp.base = rs.getDouble("defaulthpbase");
		t.stats.hp.modifier = rs.getDouble("defaulthpmod");
		t.stats.hp.add = rs.getDouble("defaulthpadd");

		t.stats.mp = new Stat();
		t.stats.mp.base = rs.getDouble("defaultmpbase");
		t.stats.mp.modifier = rs.getDouble("defaultmpmod");
		t.stats.mp.add = rs.getDouble("defaultmpadd");

		t.stats.cp = new Stat();
		t.stats.cp.base = rs.getDouble("defaultcpbase");
		t.stats.cp.modifier = rs.getDouble("defaultcpmod");
		t.stats.cp.add = rs.getDouble("defaultcpadd");

		t.stats.base = new BaseMetadata();
		t.stats.base.intelligence = rs.getInt("_INT");
		t.stats.base.strength = rs.getInt("STR");
		t.stats.base.concentration = rs.getInt("CON");
		t.stats.base.mentality = rs.getInt("MEN");
		t.stats.base.dexterity = rs.getInt("DEX");
		t.stats.base.witness = rs.getInt("WIT");

		t.stats.attack = new AttackMetadata();
		t.stats.attack.critical = rs.getInt("CRITICAL");
		t.stats.attack.evasion = rs.getInt("EVASION");
		t.stats.attack.accuracy = rs.getInt("ACC");

		t.stats.attack.physical = new AttackValueMetadata();
		t.stats.attack.physical.damage = rs.getDouble("P_ATK");
		t.stats.attack.physical.speed = rs.getDouble("P_SPD");
		t.stats.attack.magical = new AttackValueMetadata();
		t.stats.attack.magical.damage = rs.getDouble("M_ATK");
		t.stats.attack.magical.speed = rs.getDouble("M_SPD");

		t.stats.defense = new DefenseMetadata();
		t.stats.defense.physical = new DefenseValueMetadata();
		t.stats.defense.physical.value = rs.getDouble("P_DEF");
		t.stats.defense.magical = new DefenseValueMetadata();
		t.stats.defense.magical.value = rs.getDouble("M_DEF");

		t.stats.move = new MoveMetadata();
		t.stats.move.run = rs.getInt("MOVE_SPD");
		// TODO this is not really the same
		t.stats.move.walk = rs.getInt("MOVE_SPD");

		t.stats.level = rs.getInt("class_lvl");
		t.stats.maximumLoad = rs.getInt("_LOAD");
		t.stats.crafter = rs.getBoolean("canCraft");

		t.collision = new CollitionMetadataContainer();
		t.collision.male = new CollisionMetadata();
		t.collision.male.radius = rs.getDouble("M_COL_R");
		t.collision.male.height = rs.getDouble("M_COL_H");
		t.collision.female = new CollisionMetadata();
		t.collision.female.radius = rs.getDouble("F_COL_R");
		t.collision.female.height = rs.getDouble("F_COL_H");

		return t;
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
