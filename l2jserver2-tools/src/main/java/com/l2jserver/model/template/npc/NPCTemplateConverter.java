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
package com.l2jserver.model.template.npc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.id.template.TeleportationTemplateID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.template.NPCTemplate.Droplist;
import com.l2jserver.model.template.NPCTemplate.Droplist.Item.DropCategory;
import com.l2jserver.model.template.NPCTemplate.Skills;
import com.l2jserver.model.template.NPCTemplate.Talk;
import com.l2jserver.model.template.NPCTemplate.Talk.Chat;
import com.l2jserver.model.template.ObjectFactory;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.npc.TeleportationTemplate.TeleportRestriction;
import com.l2jserver.model.world.npc.BaseNPCController;
import com.l2jserver.model.world.npc.NPCController;
import com.l2jserver.model.world.npc.controller.impl.MonsterController;
import com.l2jserver.model.world.npc.controller.impl.NotImplementedNPCController;
import com.l2jserver.model.world.npc.controller.impl.TeleporterController;
import com.l2jserver.service.game.template.XMLTemplateService.TeleportationTemplateContainer;
import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.geometry.Coordinate;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

@SuppressWarnings("restriction")
public class NPCTemplateConverter {
	private static final String JDBC_URL = "jdbc:mysql://localhost/l2jserver_legacy";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "";
	private static final File L2J_HTML_FOLDER = new File(
			"../../L2J_DataPack_BETA/dist/game/data/html");

	private static final Map<String, Class<? extends NPCController>> controllers = CollectionFactory
			.newMap();

	private static List<NPCTemplate> templates = CollectionFactory.newList();
	private static Collection<File> htmlScannedFiles;
	private static TeleportationTemplateContainer teleportation = new TeleportationTemplateContainer();

	public static void main(String[] args) throws SQLException, IOException,
			ClassNotFoundException, JAXBException {
		controllers.put("L2Teleporter", TeleporterController.class);
		controllers.put("L2CastleTeleporter", TeleporterController.class);
		controllers.put("L2Npc", BaseNPCController.class);
		controllers.put("L2Monster", MonsterController.class);
		controllers.put("L2FlyMonster", MonsterController.class);
		Class.forName("com.mysql.jdbc.Driver");

		final File target = new File("generated/template/npc");
	
		System.out.println("Scaning legacy HTML files...");
		htmlScannedFiles = FileUtils.listFiles(L2J_HTML_FOLDER, new String[] {
				"html", "htm" }, true);

		final JAXBContext c = JAXBContext.newInstance(NPCTemplate.class,
				TeleportationTemplateContainer.class);

		final Connection conn = DriverManager.getConnection(JDBC_URL,
				JDBC_USERNAME, JDBC_PASSWORD);
		{
			System.out.println("Converting teleport templates...");
			teleportation.templates = CollectionFactory.newList();

			final Marshaller m = c.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			final PreparedStatement st = conn
					.prepareStatement("SELECT * FROM teleport");
			st.execute();
			final ResultSet rs = st.getResultSet();
			while (rs.next()) {
				final TeleportationTemplate template = new TeleportationTemplate();

				template.id = new TeleportationTemplateID(rs.getInt("id"), null);
				template.name = rs.getString("Description");
				template.coordinate = Coordinate.fromXYZ(rs.getInt("loc_x"),
						rs.getInt("loc_y"), rs.getInt("loc_z"));
				template.price = rs.getInt("price");
				template.itemTemplateID = new ItemTemplateID(
						rs.getInt("itemId"), null);
				if (rs.getBoolean("fornoble")) {
					template.restrictions = Arrays
							.asList(TeleportRestriction.NOBLE);
				}
				teleportation.templates.add(template);
			}
			m.marshal(teleportation, getXMLSerializer(new FileOutputStream(
					new File(target, "../teleports.xml"))));
			// System.exit(0);
		}

		System.out.println("Generating template XML files...");
//		c.generateSchema(new SchemaOutputResolver() {
//			@Override
//			public Result createOutput(String namespaceUri,
//					String suggestedFileName) throws IOException {
//				// System.out.println(new File(target, suggestedFileName));
//				// return null;
//				return new StreamResult(new File(target, suggestedFileName));
//			}
//		});

		try {
			final Marshaller m = c.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			final PreparedStatement st = conn
					.prepareStatement("SELECT npc.*, npcskills.level AS race "
							+ "FROM npc "
							+ "LEFT JOIN npcskills "
							+ "ON(npc.idTemplate = npcskills.npcid AND npcskills.skillid = ?)");
			st.setInt(1, 4416);
			st.execute();
			final ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Object[] result = fillNPC(rs);
				NPCTemplate t = (NPCTemplate) result[0];
				String type = (String) result[1];

				String folder = createFolder(type);
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
						+ t.getID().getID()
						+ (t.getInfo().getName() != null ? "-"
								+ camelCase(t.getInfo().getName().getValue())
								: "") + ".xml");
				file.getParentFile().mkdirs();
				templates.add(t);

				try {
					m.marshal(t, getXMLSerializer(new FileOutputStream(file)));
				} catch (MarshalException e) {
					System.err
							.println("Could not generate XML template file for "
									+ t.getInfo().getName().getValue()
									+ " - "
									+ t.getID());
					file.delete();
				}
			}

			System.out.println("Generated " + templates.size() + " templates");

			System.gc();
			System.out.println("Free: "
					+ FileUtils.byteCountToDisplaySize(Runtime.getRuntime()
							.freeMemory()));
			System.out.println("Total: "
					+ FileUtils.byteCountToDisplaySize(Runtime.getRuntime()
							.totalMemory()));
			System.out
					.println("Used: "
							+ FileUtils.byteCountToDisplaySize(Runtime
									.getRuntime().totalMemory()
									- Runtime.getRuntime().freeMemory()));
			System.out.println("Max: "
					+ FileUtils.byteCountToDisplaySize(Runtime.getRuntime()
							.maxMemory()));
		} finally {
			conn.close();
		}
	}

	private static Object[] fillNPC(ResultSet rs) throws SQLException,
			IOException {
		final ObjectFactory factory = new ObjectFactory();

		final NPCTemplate template = factory.createNPCTemplate();
		template.setID(new NPCTemplateID(rs.getInt("idTemplate"), null));

		template.setController(controllers.get(rs.getString("type")));
		if (template.getController() == null)
			template.setController(NotImplementedNPCController.class);
		template.setInfo(factory.createNPCTemplateInfo());

		template.getInfo().setName(factory.createNPCTemplateInfoName());
		template.getInfo().getName().setValue(rs.getString("name"));
		//template.getInfo().getName().setDisplay(rs.getBoolean("show_name"));
		template.getInfo().getName().setSend(rs.getBoolean("serverSideName"));

		template.getInfo().setTitle(factory.createNPCTemplateInfoTitle());
		template.getInfo().getTitle().setValue(rs.getString("title"));
		template.getInfo().getTitle().setSend(rs.getBoolean("serverSideTitle"));

		if (template.getInfo().getName().getValue().length() == 0)
			template.getInfo().setName(null);
		if (template.getInfo().getTitle().getValue().length() == 0)
			template.getInfo().setTitle(null);

		template.getInfo().setLevel(rs.getInt("level"));
		template.getInfo().setRace(getRace(rs.getInt("race")));
		if (!rs.getString("sex").equals("etc"))
			template.getInfo().setSex(
					ActorSex.valueOf(rs.getString("sex").toUpperCase()));
		// template.info.attackable = rs.getBoolean("attackable");
		//template.getInfo().setTargetable(rs.getBoolean("targetable"));
		template.getInfo().setTargetable(true);
		//template.getInfo().setAggressive(rs.getBoolean("aggro"));
		template.getInfo().setAggressive(true);
		template.getInfo().setAttackable(true); // FIXME

		template.getInfo().setStats(factory.createNPCTemplateInfoStats());

		template.getInfo().getStats()
				.setHp(factory.createNPCTemplateInfoStatsHp());
		template.getInfo().getStats().getHp().setMax(rs.getDouble("hp"));
		template.getInfo().getStats().getHp().setRegen(rs.getDouble("hpreg"));

		template.getInfo().getStats()
				.setMp(factory.createNPCTemplateInfoStatsMp());
		template.getInfo().getStats().getMp().setMax(rs.getDouble("mp"));
		template.getInfo().getStats().getMp().setRegen(rs.getDouble("mpreg"));

		template.getInfo().getStats()
				.setAttack(factory.createNPCTemplateInfoStatsAttack());
		template.getInfo().getStats().getAttack()
				.setRange(rs.getInt("attackrange"));
		template.getInfo().getStats().getAttack()
				.setCritical(rs.getInt("critical"));

		template.getInfo()
				.getStats()
				.getAttack()
				.setPhysical(factory.createNPCTemplateInfoStatsAttackPhysical());
		template.getInfo().getStats().getAttack().getPhysical()
				.setDamage(rs.getDouble("patk"));
		template.getInfo().getStats().getAttack().getPhysical()
				.setSpeed(rs.getDouble("atkspd"));

		template.getInfo().getStats().getAttack()
				.setMagical(factory.createNPCTemplateInfoStatsAttackMagical());
		template.getInfo().getStats().getAttack().getMagical()
				.setDamage(rs.getDouble("matk"));
		template.getInfo().getStats().getAttack().getMagical()
				.setSpeed(rs.getDouble("matkspd"));

		template.getInfo().getStats()
				.setDefense(factory.createNPCTemplateInfoStatsDefense());
		template.getInfo()
				.getStats()
				.getDefense()
				.setPhysical(
						factory.createNPCTemplateInfoStatsDefensePhysical());
		template.getInfo().getStats().getDefense().getPhysical()
				.setValue(rs.getDouble("pdef"));
		template.getInfo().getStats().getDefense()
				.setMagical(factory.createNPCTemplateInfoStatsDefenseMagical());
		template.getInfo().getStats().getDefense().getMagical()
				.setValue(rs.getDouble("mdef"));

		template.getInfo().getStats()
				.setMove(factory.createNPCTemplateInfoStatsMove());
		template.getInfo().getStats().getMove().setRun(rs.getDouble("runspd"));
		template.getInfo().getStats().getMove()
				.setWalk(rs.getDouble("walkspd"));

		template.getInfo().getStats()
				.setBase(factory.createNPCTemplateInfoStatsBase());
		template.getInfo().getStats().getBase().setInt(rs.getInt("int"));
		template.getInfo().getStats().getBase().setStr(rs.getInt("str"));
		template.getInfo().getStats().getBase().setCon(rs.getInt("con"));
		template.getInfo().getStats().getBase().setDex(rs.getInt("dex"));
		template.getInfo().getStats().getBase().setWit(rs.getInt("wit"));
		template.getInfo().getStats().getBase().setMen(rs.getInt("men"));

		template.getInfo().setExperience(rs.getLong("exp"));
		template.getInfo().setSp(rs.getInt("sp"));

		if (rs.getInt("rhand") > 0 || rs.getInt("lhand") > 0)
			template.getInfo().setItem(factory.createNPCTemplateInfoItem());
		if (rs.getInt("rhand") > 0)
			template.getInfo().getItem()
					.setRightHand(new ItemTemplateID(rs.getInt("rhand"), null));
		if (rs.getInt("lhand") > 0)
			template.getInfo().getItem()
					.setLeftHand(new ItemTemplateID(rs.getInt("lhand"), null));

		template.getInfo().setCollision(
				factory.createNPCTemplateInfoCollision());
		template.getInfo().getCollision()
				.setRadius(rs.getDouble("collision_radius"));
		template.getInfo().getCollision()
				.setHeigth(rs.getDouble("collision_height"));

		template.setDroplist(fillDropList(factory, rs, template.getID().getID()));
		template.setSkills(fillSkillList(factory, rs, template.getID().getID()));
		template.setTalk(fillHtmlChat(factory, template.getID().getID()));

		return new Object[] { template, createParentType(rs.getString("type")) };
	}

	private static Droplist fillDropList(final ObjectFactory factory,
			ResultSet npcRs, int npcId) throws SQLException {
		final Connection conn = npcRs.getStatement().getConnection();
		final Droplist drops = factory.createNPCTemplateDroplist();

		final PreparedStatement st = conn
				.prepareStatement("SELECT * FROM droplist WHERE mobId = ?");
		st.setInt(1, npcId);
		st.execute();
		final ResultSet rs = st.getResultSet();
		while (rs.next()) {
			final Droplist.Item item = factory.createNPCTemplateDroplistItem();
			item.setId( new ItemTemplateID(rs.getInt("itemId"), null));
			item.setMin(rs.getInt("min"));
			item.setMax( rs.getInt("max"));
			item.setChance(rs.getInt("chance"));
			item.setCategory(getCategory(rs.getInt("category")));
			drops.getItem().add(item);
		}
		if (drops.getItem().size() == 0)
			return null;
		return drops;
	}

	private static Talk fillHtmlChat(final ObjectFactory factory, int npcId) throws IOException {
		final Talk talk = factory.createNPCTemplateTalk();
		talk.setDefault("default");
		for (final File file : htmlScannedFiles) {
			String id = null;
			if (file.getName().startsWith(npcId + "-")) {
				int preffixLength = (npcId + "-").length();
				id = file.getName().substring(preffixLength,
						file.getName().indexOf("."));
			} else if (file.getName().startsWith(npcId + ".")) {
				id = "default";
			}
			if (id != null && !file.getAbsolutePath().contains("/half/")
					&& !file.getAbsolutePath().contains("/free/")) {
				Chat chat = new Chat();
				chat.setId(id);
				chat.setValue(FileUtils.readFileToString(file));
				talk.getChat().add(chat);
			}
		}

		if (talk.getChat().size() == 0)
			return null;
		return talk;
	}

	private static Skills fillSkillList(final ObjectFactory factory, ResultSet npcRs, int npcId)
			throws SQLException {
		final Connection conn = npcRs.getStatement().getConnection();
		final Skills skills = factory.createNPCTemplateSkills();

		final PreparedStatement st = conn
				.prepareStatement("SELECT * FROM npcskills WHERE npcid = ?");
		st.setInt(1, npcId);
		st.execute();
		final ResultSet rs = st.getResultSet();
		while (rs.next()) {
			Skills.Skill s = factory.createNPCTemplateSkillsSkill();
			s.setId(new SkillTemplateID(rs.getInt("skillid"), null));
			s.setLevel(rs.getInt("level"));
			skills.getSkill().add(s);
		}
		if (skills.getSkill().size() == 0)
			return null;
		return skills;
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

	private static XMLSerializer getXMLSerializer(OutputStream w) {
		// configure an OutputFormat to handle CDATA
		OutputFormat of = new OutputFormat();

		// specify which of your elements you want to be handled as CDATA.
		// The use of the '^' between the namespaceURI and the localname
		// seems to be an implementation detail of the xerces code.
		// When processing xml that doesn't use namespaces, simply omit the
		// namespace prefix as shown in the third CDataElement below.
		of.setCDataElements(new String[] { "^chat" });

		// set any other options you'd like
		of.setPreserveSpace(false);
		of.setIndenting(true);

		// create the serializer
		XMLSerializer serializer = new XMLSerializer(of);
		serializer.setOutputByteStream(w);

		return serializer;
	}

	public static NPCRace getRace(int id) {
		switch (id) {
		case 1:
			return NPCRace.UNDEAD;
		case 2:
			return NPCRace.MAGIC_CREATURE;
		case 3:
			return NPCRace.BEAST;
		case 4:
			return NPCRace.ANIMAL;
		case 5:
			return NPCRace.PLANT;
		case 6:
			return NPCRace.HUMANOID;
		case 7:
			return NPCRace.SPIRIT;
		case 8:
			return NPCRace.ANGEL;
		case 9:
			return NPCRace.DEMON;
		case 10:
			return NPCRace.DRAGON;
		case 11:
			return NPCRace.GIANT;
		case 12:
			return NPCRace.BUG;
		case 13:
			return NPCRace.FAIRIE;
		case 14:
			return NPCRace.HUMAN;
		case 15:
			return NPCRace.ELVEN;
		case 16:
			return NPCRace.DARKELVEN;
		case 17:
			return NPCRace.ORC;
		case 18:
			return NPCRace.DWARVEN;
		case 19:
			return NPCRace.OTHER;
		case 20:
			return NPCRace.NON_LIVING;
		case 21:
			return NPCRace.SIEGE_WEAPON;
		case 22:
			return NPCRace.DEFENDING_ARMY;
		case 23:
			return NPCRace.MERCENARIE;
		case 24:
			return NPCRace.UNKNOWN;
		case 25:
			return NPCRace.KAMAEL;
		default:
			return NPCRace.NONE;
		}
	}

	public static DropCategory getCategory(int id) {
		switch (id) {
		case -1:
			return DropCategory.SPOIL;
		case 0:
			return DropCategory.DROP;
		default:
			return DropCategory.valueOf("UNK_" + id);
		}
	}
}
