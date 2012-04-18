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

import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;

/**
 * This is an message informing the client of extra informations from a player
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_INFO_EXTRA extends AbstractServerPacket {
	/**
	 * The packet OPCODE1
	 */
	public static final int OPCODE1 = 0xfe;
	/**
	 * The packet OPCODE2
	 */
	public static final int OPCODE2 = 0xcf;

	/**
	 * The character
	 */
	private L2Character character;

	/**
	 * @param character
	 *            the character
	 */
	public SM_CHAR_INFO_EXTRA(L2Character character) {
		super(OPCODE1);
		this.character = character;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeShort(OPCODE2); // opcode2
		buffer.writeInt(character.getID().getID()); // object ID of Player
		buffer.writeInt(0x00); // event effect id
	}
}
