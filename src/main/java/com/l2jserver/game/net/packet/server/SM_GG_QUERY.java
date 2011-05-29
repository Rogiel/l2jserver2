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
 * This packet send the GameGuard query to the client. The client will send an
 * notification, but this can be ignored if GG is not supposed to be enforced.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_GG_QUERY extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x74;

	public SM_GG_QUERY() {
		super(OPCODE);

	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(0x27533DD9);
		buffer.writeInt(0x2E72A51D);
		buffer.writeInt(0x2017038B);
		buffer.writeInt(0xC35B1EA3);
	}
}
