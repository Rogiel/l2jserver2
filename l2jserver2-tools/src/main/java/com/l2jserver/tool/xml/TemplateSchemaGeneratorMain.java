package com.l2jserver.tool.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.service.game.template.XMLTemplateService.TeleportationTemplateContainer;

public class TemplateSchemaGeneratorMain {
	/**
	 * @param args
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		final File baseDir = new File("generated/schemas");

		JAXBContext context = JAXBContext.newInstance(CharacterTemplate.class,
				NPCTemplate.class, ItemTemplate.class, SkillTemplate.class,
				TeleportationTemplateContainer.class);

		context.generateSchema(new SchemaOutputResolver() {
			public Result createOutput(String namespaceUri,
					String suggestedFileName) throws IOException {
				switch (namespaceUri) {
				case "http://schemas.l2jserver2.com/teleport":
					suggestedFileName = "teleport.xsd";
					break;
				case "http://schemas.l2jserver2.com/skill":
					suggestedFileName = "skill.xsd";
					break;
				case "http://schemas.l2jserver2.com/npc":
					suggestedFileName = "npc.xsd";
					break;
				case "http://schemas.l2jserver2.com/item":
					suggestedFileName = "item.xsd";
					break;
				case "http://schemas.l2jserver2.com/character":
					suggestedFileName = "character.xsd";
					break;
				case "":
					namespaceUri = "[empty]";
					suggestedFileName = "l2jserver2.xsd";
					break;
				}

				System.out.println(namespaceUri + " mapped to "
						+ suggestedFileName);
				return new StreamResult(new File(baseDir, suggestedFileName));
			}
		});
	}
}
