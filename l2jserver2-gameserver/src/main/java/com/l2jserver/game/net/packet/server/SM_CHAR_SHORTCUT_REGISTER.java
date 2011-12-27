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

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.game.CharacterShortcut;

/**
 * This packet informs the client that a new shortcut has been created
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_SHORTCUT_REGISTER extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x44;

	/**
	 * The shortcut
	 */
	private final CharacterShortcut shortcut;

	/**
	 * @param shortcut
	 *            the shortcut registered
	 */
	public SM_CHAR_SHORTCUT_REGISTER(CharacterShortcut shortcut) {
		super(OPCODE);
		this.shortcut = shortcut;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(shortcut.getType().id);
		buffer.writeInt(shortcut.getPage() * 12 + shortcut.getSlot());
		switch (shortcut.getType()) {
		case ITEM:
			buffer.writeInt(shortcut.getItemID().getID());
			buffer.writeInt(0x01); // unk1f
			buffer.writeInt(-1); // reuse group
			buffer.writeInt(0x00); // unk2
			buffer.writeInt(0x00); // unk3
			buffer.writeShort(0x00); // unk4
			buffer.writeShort(0x00); // unk5
			break;
		}
	}
}
