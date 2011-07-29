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
package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.template.CharacterTemplate;

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

	public SM_CHAR_TEMPLATE(CharacterTemplate... templates) {
		super(OPCODE);
		this.templates = templates;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(templates.length);
		for (final CharacterTemplate template : templates) {
			buffer.writeInt(template.getRace().id);
			buffer.writeInt(template.getCharacterClass().id);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseStrength());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseDexterity());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseConcentration());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseIntelligence());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseWitness());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseMentality());
			buffer.writeInt(0x0a);
		}
	}
}
