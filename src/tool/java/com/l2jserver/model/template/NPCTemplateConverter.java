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
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.NPCTemplate.DropItemMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.CollisionMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.ItemMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCNameMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCStatsMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCStatsMetadata.AttackMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCStatsMetadata.AttackMetadata.AttackValueMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCStatsMetadata.BaseMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCStatsMetadata.DefenseMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCStatsMetadata.DefenseMetadata.DefenseValueMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCStatsMetadata.MoveMetadata;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCStatsMetadata.Stat;
import com.l2jserver.model.template.NPCTemplate.NPCInformationMetadata.NPCTitleMetadata;
import com.l2jserver.model.world.Actor.ActorSex;
import com.l2jserver.util.factory.CollectionFactory;

public class NPCTemplateConverter {
	private static final String JDBC_URL = "jdbc:mysql://localhost/l2j-old";
	private static final String JDBC_USERNAME = "l2j";
	private static final String JDBC_PASSWORD = "changeme";

	private static List<NPCTemplate> templates = CollectionFactory.newList();

	public static void main(String[] args) throws SQLException, IOException,
			ClassNotFoundException, JAXBException {
		Class.forName("com.mysql.jdbc.Driver");
		final File target = new File("generated/template");

		System.out.println("Generating template classes...");

		final JAXBContext c = JAXBContext.newInstance(NPCTemplate.class);
		c.generateSchema(new SchemaOutputResolver() {
			@Override
			public Result createOutput(String namespaceUri,
					String suggestedFileName) throws IOException {
				return new StreamResult(new File(target, "npc.xsd"));
			}
		});

		final Marshaller m = c.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "npc");

		final Connection conn = DriverManager.getConnection(JDBC_URL,
				JDBC_USERNAME, JDBC_PASSWORD);
		try {
			final PreparedStatement st = conn
					.prepareStatement("SELECT *  FROM npc");
			st.execute();
			final ResultSet rs = st.getResultSet();
			while (rs.next()) {
				NPCTemplate t = fillNPC(rs);

				String folder = createFolder(t.type);
				if (folder.isEmpty()) {
					m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
							"npc ../npc.xsd");
				} else {
					m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
							"npc ../../npc.xsd");
				}

				final File file = new File(target, "npc/"
						+ folder
						+ "/"
						+ t.id
						+ (t.info.nameMetadata != null ? "-"
								+ camelCase(t.info.nameMetadata.name) : "")
						+ ".xml");
				file.getParentFile().mkdirs();
				templates.add(t);

				// m.marshal(t, file);

				// m.marshal(t, System.out);
				// System.exit(0);
			}

			System.gc();
			System.out.println("Free: " + Runtime.getRuntime().freeMemory()
					/ 1024 / 1024 + " MB");
			System.out.println("Total: " + Runtime.getRuntime().totalMemory()
					/ 1024 / 1024 + " MB");
			System.out.println("Used: "
					+ (Runtime.getRuntime().totalMemory() - Runtime
							.getRuntime().freeMemory()) / 1024 / 1024 + " MB");
			System.out.println("Max: " + Runtime.getRuntime().maxMemory()
					/ 1024 / 1024 + " MB");
		} finally {
			conn.close();
		}
	}

	private static NPCTemplate fillNPC(ResultSet rs) throws SQLException {
		final NPCTemplate template = new NPCTemplate();
		template.id = null;
		template.type = createParentType(rs.getString("type"));
		template.info = new NPCInformationMetadata();

		template.info.nameMetadata = new NPCNameMetadata();
		template.info.nameMetadata.name = rs.getString("name");
		template.info.nameMetadata.display = rs.getBoolean("show_name");
		template.info.nameMetadata.send = rs.getBoolean("serverSideName");

		template.info.titleMetadata = new NPCTitleMetadata();
		template.info.titleMetadata.title = rs.getString("title");
		template.info.titleMetadata.send = rs.getBoolean("serverSideTitle");

		if (template.info.titleMetadata.title.length() == 0)
			template.info.titleMetadata = null;
		if (template.info.nameMetadata.name.length() == 0)
			template.info.nameMetadata = null;

		template.info.level = rs.getInt("level");
		if (!rs.getString("sex").equals("etc"))
			template.info.sex = ActorSex.valueOf(rs.getString("sex")
					.toUpperCase());
		// template.info.attackable = rs.getBoolean("attackable");
		template.info.targetable = rs.getBoolean("targetable");
		template.info.aggressive = rs.getBoolean("aggro");

		template.info.stats = new NPCStatsMetadata();

		template.info.stats.hp = new Stat();
		template.info.stats.hp.max = rs.getDouble("hp");
		template.info.stats.hp.regen = rs.getDouble("hpreg");

		template.info.stats.mp = new Stat();
		template.info.stats.mp.max = rs.getDouble("mp");
		template.info.stats.mp.regen = rs.getDouble("mpreg");

		template.info.stats.attack = new AttackMetadata();
		template.info.stats.attack.range = rs.getInt("attackrange");
		template.info.stats.attack.critical = rs.getInt("critical");

		template.info.stats.attack.physical = new AttackValueMetadata();
		template.info.stats.attack.physical.damage = rs.getDouble("patk");
		template.info.stats.attack.physical.speed = rs.getDouble("atkspd");

		template.info.stats.attack.magical = new AttackValueMetadata();
		template.info.stats.attack.magical.damage = rs.getDouble("matk");
		template.info.stats.attack.magical.speed = rs.getDouble("matkspd");

		template.info.stats.defense = new DefenseMetadata();
		template.info.stats.defense.physical = new DefenseValueMetadata();
		template.info.stats.defense.physical.value = rs.getDouble("pdef");
		template.info.stats.defense.magical = new DefenseValueMetadata();
		template.info.stats.defense.magical.value = rs.getDouble("mdef");

		template.info.stats.move = new MoveMetadata();
		template.info.stats.move.run = rs.getDouble("runspd");
		template.info.stats.move.walk = rs.getDouble("walkspd");

		template.info.stats.base = new BaseMetadata();
		template.info.stats.base.intelligence = rs.getInt("int");
		template.info.stats.base.strength = rs.getInt("str");
		template.info.stats.base.concentration = rs.getInt("con");
		template.info.stats.base.dexterity = rs.getInt("dex");
		template.info.stats.base.witness = rs.getInt("wit");
		template.info.stats.base.mentality = rs.getInt("men");

		template.info.experience = rs.getLong("exp");
		template.info.sp = rs.getInt("sp");

		if (rs.getInt("rhand") > 0 || rs.getInt("lhand") > 0)
			template.info.item = new ItemMetadata();
		if (rs.getInt("rhand") > 0)
			template.info.item.rightHand = new ItemTemplateID(
					rs.getInt("rhand"), null);
		if (rs.getInt("lhand") > 0)
			template.info.item.leftHand = new ItemTemplateID(
					rs.getInt("lhand"), null);

		template.info.collision = new CollisionMetadata();
		template.info.collision.radius = rs.getDouble("collision_radius");
		template.info.collision.height = rs.getDouble("collision_height");

		// TODO import teleporter data
		// TODO import html files

		template.droplist = fillDropList(rs, template.id.getID());

		return template;
	}

	private static List<DropItemMetadata> fillDropList(ResultSet npcRs,
			int npcId) throws SQLException {
		final Connection conn = npcRs.getStatement().getConnection();
		final List<DropItemMetadata> drops = CollectionFactory.newList();

		final PreparedStatement st = conn
				.prepareStatement("SELECT * FROM droplist WHERE mobId = ?");
		st.setInt(1, npcId);
		st.execute();
		final ResultSet rs = st.getResultSet();
		while (rs.next()) {
			DropItemMetadata m = new DropItemMetadata();
			m.item = new ItemTemplateID(rs.getInt("itemId"), null);
			m.min = rs.getInt("min");
			m.max = rs.getInt("max");
			m.chance = rs.getInt("chance");
			drops.add(m);
			// TODO category
		}
		if (drops.size() == 0)
			return null;
		return drops;
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
