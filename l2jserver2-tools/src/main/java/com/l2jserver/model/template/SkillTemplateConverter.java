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
package com.l2jserver.model.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;

import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.util.factory.CollectionFactory;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

@SuppressWarnings("restriction")
public class SkillTemplateConverter {
	private static final String JDBC_URL = "jdbc:mysql://localhost/l2jlegacy";
	private static final String JDBC_USERNAME = "l2j";
	private static final String JDBC_PASSWORD = "changeme";

	private static final String LEGACY_SKILL_FOLDER = "../L2J_DataPack_BETA/data/stats/skills";

	private static List<SkillTemplate> templates = CollectionFactory.newList();

	public static void main(String[] args) throws SQLException, IOException,
			ClassNotFoundException, JAXBException {
		Class.forName("com.mysql.jdbc.Driver");

		final File target = new File("data/templates");
		final JAXBContext c = JAXBContext.newInstance(SkillTemplate.class,
				LegacySkillList.class);
		final Connection conn = DriverManager.getConnection(JDBC_URL,
				JDBC_USERNAME, JDBC_PASSWORD);

		System.out.println("Generating template XML files...");
		c.generateSchema(new SchemaOutputResolver() {
			@Override
			public Result createOutput(String namespaceUri,
					String suggestedFileName) throws IOException {
				return new StreamResult(new File(target, suggestedFileName));
			}
		});

		try {
			final Unmarshaller u = c.createUnmarshaller();
			final Marshaller m = c.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "skill");
			m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "skill ../skill.xsd");

			Collection<File> files = FileUtils.listFiles(new File(
					LEGACY_SKILL_FOLDER), new String[] { "xml" }, true);
			for (final File legacyFile : files) {
				LegacySkillList list = (LegacySkillList) u
						.unmarshal(legacyFile);
				for (final LegacySkill legacySkill : list.skills) {
					SkillTemplate t = fillSkill(legacySkill);
					final File file = new File(target, "skill/"
							+ t.id.getID()
							+ (t.getName() != null ? "-"
									+ camelCase(t.getName()) : "") + ".xml");
					templates.add(t);

					try {
						m.marshal(t,
								getXMLSerializer(new FileOutputStream(file)));
					} catch (MarshalException e) {
						System.err
								.println("Could not generate XML template file for "
										+ t.getName() + " - " + t.getID());
						file.delete();
					}
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

	private static SkillTemplate fillSkill(LegacySkill legacy)
			throws SQLException, IOException {
		final SkillTemplate template = new SkillTemplate();
		template.id = new SkillTemplateID(legacy.id, null);
		template.name = legacy.name;
		return template;
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

	@XmlRootElement(name = "list")
	public static class LegacySkillList {
		@XmlElement(name = "skill")
		private List<LegacySkill> skills;
	}

	public static class LegacySkill {
		@XmlAttribute(name = "id")
		private int id;
		@XmlAttribute(name = "name")
		private String name;
	}
}
