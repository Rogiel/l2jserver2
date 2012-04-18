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
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;

/**
 * This packet sends an item that is dropped on the ground
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_ITEM_GROUND extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x16;
	/**
	 * The item that is on the ground
	 */
	private final Item item;

	/**
	 * @param item
	 *            the item that is on the ground
	 */
	public SM_ITEM_GROUND(Item item) {
		super(OPCODE);
		this.item = item;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt((item.getOwnerID() != null ? item.getOwnerID().getID()
				: 0)); // char who dropped
		buffer.writeInt(item.getID().getID()); // item obj id
		buffer.writeInt(item.getTemplateID().getID()); // item template id

		buffer.writeInt(item.getPoint().getX()); // x
		buffer.writeInt(item.getPoint().getY()); // y
		buffer.writeInt(item.getPoint().getZ()); // z
		// only show item count if it is a stackable item
		buffer.writeInt(0x01); // show count
		buffer.writeLong(item.getCount()); // count

		buffer.writeInt(0); // unknown
	}
}
