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
package com.l2jserver.test;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.GameServerModule;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.template.TeleportationTemplate;
import com.l2jserver.util.jaxb.CharacterTemplateIDAdapter;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;
import com.l2jserver.util.jaxb.NPCTemplateIDAdapter;
import com.l2jserver.util.jaxb.TeleportationTemplateIDAdapter;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class XMLMappingTest {
	/**
	 * @param args
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		// final List<NPCTemplate> templates = CollectionFactory.newList();

		final JAXBContext c = JAXBContext.newInstance(CharacterTemplate.class,
				NPCTemplate.class, TeleportationTemplate.class);
		final Unmarshaller u = c.createUnmarshaller();

		final Injector injector = Guice.createInjector(new GameServerModule());
		u.setAdapter(NPCTemplateIDAdapter.class,
				injector.getInstance(NPCTemplateIDAdapter.class));
		u.setAdapter(ItemTemplateIDAdapter.class,
				injector.getInstance(ItemTemplateIDAdapter.class));
		u.setAdapter(CharacterTemplateIDAdapter.class,
				injector.getInstance(CharacterTemplateIDAdapter.class));
		u.setAdapter(TeleportationTemplateIDAdapter.class,
				injector.getInstance(TeleportationTemplateIDAdapter.class));

		// long start = System.currentTimeMillis();
		// for (int i = 0; i < 200 * 1000; i++) {
		// final NPCTemplate t = (NPCTemplate) u.unmarshal(new File(
		// "data/npc.xml"));
		// templates.add(t);
		// }
		// long end = System.currentTimeMillis();
		//
		// System.out.println("Took " + ((end - start) / 1000) + " seconds");

		final CharacterTemplate t = (CharacterTemplate) u.unmarshal(new File(
				"data/templates/character/HumanFighter.xml"));
		System.out.println(t.getID());
		System.out.println(t.getCharacterClass());
	}
}
