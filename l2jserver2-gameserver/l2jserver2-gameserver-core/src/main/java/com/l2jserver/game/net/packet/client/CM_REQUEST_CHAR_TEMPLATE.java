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
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.game.net.packet.server.SM_CHAR_TEMPLATE;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;

/**
 * Requests the creation of a new Character. The list of character templates is
 * sent to the client, meaning that the client is authorized to create
 * characters.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_REQUEST_CHAR_TEMPLATE extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x0e;

	/**
	 * List of {@link CharacterClass} templates sent to the client
	 */
	private static final CharacterClass[] TEMPLATE_CLASSES = {
			CharacterClass.HUMAN_FIGHTER, CharacterClass.HUMAN_MYSTIC,
			CharacterClass.ELVEN_FIGHTER, CharacterClass.ELVEN_MYSTIC,
			CharacterClass.DARK_FIGHTER, CharacterClass.DARK_MYSTIC,
			CharacterClass.ORC_FIGHTER, CharacterClass.ORC_MYSTIC,
			CharacterClass.DWARVEN_FIGHTER, CharacterClass.MALE_SOLDIER,
			CharacterClass.FEMALE_SOLDIER };

	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(CM_REQUEST_CHAR_TEMPLATE.class);

	/**
	 * The {@link CharacterTemplateID} factory
	 */
	private final CharacterTemplateIDProvider idFactory;

	/**
	 * @param idFactory
	 *            the character template id provider
	 */
	@Inject
	public CM_REQUEST_CHAR_TEMPLATE(CharacterTemplateIDProvider idFactory) {
		this.idFactory = idFactory;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
	}

	@Override
	public void process(final Lineage2Client conn) {
		log.debug("Requested character templates");

		final CharacterTemplate[] templates = new CharacterTemplate[TEMPLATE_CLASSES.length];
		int i = 0;
		for (final CharacterClass charClass : TEMPLATE_CLASSES) {
			final CharacterTemplateID id = idFactory.resolveID(charClass.id);
			templates[i++] = id.getTemplate();
		}
		conn.write(new SM_CHAR_TEMPLATE(templates));
	}
}
