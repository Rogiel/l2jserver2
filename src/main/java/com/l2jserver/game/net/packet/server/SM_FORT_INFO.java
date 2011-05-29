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
import com.l2jserver.util.BufferUtils;

/**
 * This packet send the manor list to the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_FORT_INFO extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0xfe;

	public SM_FORT_INFO() {
		super(OPCODE);
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeShort(0x15);
		buffer.writeInt(21);
		int i = 101;
		for (; i < 122; i++) {
			buffer.writeInt(i); // fort id
			BufferUtils.writeString(buffer, ""); // clan name
			buffer.writeInt(0x00); // is in siege
			buffer.writeInt(0x00); // Time of possession
		}

		// TODO implement fort service
	}
}
