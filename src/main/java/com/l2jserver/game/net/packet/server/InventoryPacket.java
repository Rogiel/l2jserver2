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

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.character.CharacterInventory;

/**
 * This packet send the inventory to the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class InventoryPacket extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x11;

	/**
	 * The character inventory
	 */
	private CharacterInventory inventory;
	/**
	 * Whether or not to open the inventory window
	 */
	private boolean showWindow = false;

	public InventoryPacket(CharacterInventory inventory) {
		super(OPCODE);
		this.inventory = inventory;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeByte((showWindow ? 0x01 : 0x00));
		buffer.writeInt(inventory.getItemCount()); // item count
		for (Item item : inventory) {
			buffer.writeInt(item.getID().getID());
			buffer.writeInt(item.getTemplateID().getID());
			buffer.writeInt(0x00); // loc slot
			buffer.writeLong(0x00); // count
			buffer.writeShort(0x00); // item type2
			buffer.writeShort(0x00); // item type3
			buffer.writeShort(0x00); // equiped?
			buffer.writeInt(0x00); // body part
			buffer.writeShort(0x00); // enchant level
			// race tickets
			buffer.writeShort(0x00); // item type4 (custom type 2)
			buffer.writeInt(0x00); // augument
			buffer.writeInt(0x00); // mana
			buffer.writeInt(-9999); // time
			buffer.writeShort(0x00); // attack element type
			buffer.writeShort(0x00); // attack element power
			for (byte i = 0; i < 6; i++) {
				buffer.writeShort(0x00); // element def attrib
			}
			// Enchant Effects
			buffer.writeShort(0x00);
			buffer.writeShort(0x00);
			buffer.writeShort(0x00);
		}
		// TODO inventory block
		// buffer.writeShort(_inventory.getBlockItems().length);
		// writeC(_inventory.getBlockMode());
		// for (int i : _inventory.getBlockItems())
		// buffer.writeInt(i);
		buffer.writeShort(0x00);
	}
}
