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
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.template.ObjectFactory;

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
							+ camelCase(t.getID().getCharacterClass().name())
							+ ".xml");
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
		final ObjectFactory factory = new ObjectFactory();

		final CharacterTemplate t = factory.createCharacterTemplate();
		t.setID(new CharacterTemplateID(rs.getInt("Classid"), null));
		t.setStats(factory.createCharacterTemplateStats());

		t.getStats().setHp(factory.createCharacterTemplateStatsHp());
		t.getStats().getHp().setBase(rs.getDouble("defaulthpbase"));
		t.getStats().getHp().setModifier(rs.getDouble("defaulthpmod"));
		t.getStats().getHp().setAdd(rs.getDouble("defaulthpadd"));

		t.getStats().setMp(factory.createCharacterTemplateStatsMp());
		t.getStats().getMp().setBase(rs.getDouble("defaultmpbase"));
		t.getStats().getMp().setModifier(rs.getDouble("defaultmpmod"));
		t.getStats().getMp().setAdd(rs.getDouble("defaultmpadd"));

		t.getStats().setCp(factory.createCharacterTemplateStatsCp());
		t.getStats().getCp().setBase(rs.getDouble("defaultcpbase"));
		t.getStats().getCp().setModifier(rs.getDouble("defaultcpmod"));
		t.getStats().getCp().setAdd(rs.getDouble("defaultcpadd"));

		t.getStats().setBase(factory.createCharacterTemplateStatsBase());
		t.getStats().getBase().setInt(rs.getInt("_INT"));
		t.getStats().getBase().setStr(rs.getInt("STR"));
		t.getStats().getBase().setCon(rs.getInt("CON"));
		t.getStats().getBase().setMen(rs.getInt("MEN"));
		t.getStats().getBase().setDex(rs.getInt("DEX"));
		t.getStats().getBase().setWit(rs.getInt("WIT"));

		t.getStats().setAttack(factory.createCharacterTemplateStatsAttack());
		t.getStats().getAttack().setCritical(rs.getInt("CRITICAL"));
		t.getStats().getAttack().setEvasion(rs.getInt("EVASION"));
		t.getStats().getAttack().setAccuracy(rs.getInt("ACC"));

		t.getStats()
				.getAttack()
				.setPhysical(
						factory.createCharacterTemplateStatsAttackPhysical());
		t.getStats().getAttack().getPhysical().setDamage(rs.getDouble("P_ATK"));
		t.getStats().getAttack().getPhysical().setSpeed(rs.getDouble("P_SPD"));
		t.getStats()
				.getAttack()
				.setMagical(factory.createCharacterTemplateStatsAttackMagical());
		t.getStats().getAttack().getMagical().setDamage(rs.getDouble("M_ATK"));
		t.getStats().getAttack().getMagical().setSpeed(rs.getDouble("M_SPD"));

		t.getStats().setDefense(factory.createCharacterTemplateStatsDefense());
		t.getStats()
				.getDefense()
				.setPhysical(
						factory.createCharacterTemplateStatsDefensePhysical());
		t.getStats().getDefense().getPhysical().setValue(rs.getDouble("P_DEF"));
		t.getStats()
				.getDefense()
				.setMagical(
						factory.createCharacterTemplateStatsDefenseMagical());
		t.getStats().getDefense().getMagical().setValue(rs.getDouble("M_DEF"));

		t.getStats().setMove(factory.createCharacterTemplateStatsMove());
		t.getStats().getMove().setRun(rs.getInt("MOVE_SPD"));
		// TODO this is not really the same
		t.getStats().getMove().setWalk(rs.getInt("MOVE_SPD"));
		// TODO this is not really the same

		t.getStats().setLevel(rs.getInt("class_lvl"));
		t.getStats().setMaxload(rs.getInt("_LOAD"));
		t.getStats().setCrafter(rs.getBoolean("canCraft"));

		t.setCollision(factory.createCharacterTemplateCollision());
		t.getCollision()
				.setMale(factory.createCharacterTemplateCollisionMale());
		t.getCollision().getMale().setRadius(rs.getDouble("M_COL_R"));
		t.getCollision().getMale().setHeigth(rs.getDouble("M_COL_R"));
		t.getCollision().setFemale(
				factory.createCharacterTemplateCollisionFemale());
		t.getCollision().getMale().setRadius(rs.getDouble("F_COL_R"));
		t.getCollision().getMale().setHeigth(rs.getDouble("F_COL_R"));

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
