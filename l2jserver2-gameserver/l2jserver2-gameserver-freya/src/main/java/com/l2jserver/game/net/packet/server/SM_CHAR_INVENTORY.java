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

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.character.CharacterInventory;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;

/**
 * This packet send the inventory to the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_INVENTORY extends AbstractServerPacket {
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

	/**
	 * @param inventory the inventory
	 */
	public SM_CHAR_INVENTORY(CharacterInventory inventory) {
		super(OPCODE);
		this.inventory = inventory;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeShort((showWindow ? 0x01 : 0x00));
		// TODO warehouse items will have an issue here!
		buffer.writeShort(inventory.getItemCount()); // item count

		// TODO implement real item slot
		int slot = 0;
		for (Item item : inventory) {
			buffer.writeInt(item.getID().getID()); // obj id
			buffer.writeInt(item.getTemplateID().getID()); // item id
			buffer.writeInt(slot); // loc slot
			buffer.writeLong(item.getCount()); // count
			buffer.writeShort(0x00); // item type2
			buffer.writeShort(0x00); // item type3
			buffer.writeShort((item.getLocation() == ItemLocation.PAPERDOLL ? 0x01
					: 0x00)); // equiped?
			buffer.writeInt((item.getPaperdoll() != null ? item.getPaperdoll().id
					: 0)); // body part
			buffer.writeShort(127); // enchant level
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

			slot++;
		}
		// TODO inventory block
		// buffer.writeShort(_inventory.getBlockItems().length);
		// writeC(_inventory.getBlockMode());
		// for (int i : _inventory.getBlockItems())
		// buffer.writeInt(i);
		buffer.writeShort(0x00);
	}
}
