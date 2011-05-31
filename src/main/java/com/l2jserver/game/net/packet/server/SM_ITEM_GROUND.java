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

	public SM_ITEM_GROUND() {
		super(OPCODE);
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(268437456); // char who dropped
		buffer.writeInt(268635461); // item obj id
		buffer.writeInt(57); // item template id

		buffer.writeInt(-84341); // x
		buffer.writeInt(244623); // y
		buffer.writeInt(-3728); // z
		// only show item count if it is a stackable item
		buffer.writeInt(0x01); // show count
		buffer.writeLong(4001); // count

		buffer.writeInt(1); // unknown
	}
}
