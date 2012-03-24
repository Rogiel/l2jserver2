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

import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.world.character.CharacterShortcutContainer;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;

/**
 * This packet sends to the client his shortcut list
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_SHORTCUT_LIST extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x45;

	/**
	 * The shortcut list
	 */
	private final CharacterShortcutContainer shortcuts;

	/**
	 * @param shortcuts
	 *            the shortcuts container
	 */
	public SM_CHAR_SHORTCUT_LIST(CharacterShortcutContainer shortcuts) {
		super(OPCODE);
		this.shortcuts = shortcuts;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(shortcuts.getShortcutCount());
		for (final CharacterShortcut shortcut : shortcuts) {
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
}
