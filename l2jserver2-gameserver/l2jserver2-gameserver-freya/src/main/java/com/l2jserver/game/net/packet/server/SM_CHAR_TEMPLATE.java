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
package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;

/**
 * An packet that sends all character templates to the client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_TEMPLATE extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x0d;

	/**
	 * The character template list
	 */
	private CharacterTemplate[] templates;

	/**
	 * @param templates
	 *            the character templates
	 */
	public SM_CHAR_TEMPLATE(CharacterTemplate... templates) {
		super(OPCODE);
		this.templates = templates;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(templates.length);
		for (final CharacterTemplate template : templates) {
			buffer.writeInt(template.getID().getCharacterClass().race.id);
			buffer.writeInt(template.getID().getCharacterClass().id);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getStats().getBase().getStr());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getStats().getBase().getDex());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getStats().getBase().getDex());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getStats().getBase().getInt());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getStats().getBase().getWit());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getStats().getBase().getMen());
			buffer.writeInt(0x0a);
		}
	}
}
