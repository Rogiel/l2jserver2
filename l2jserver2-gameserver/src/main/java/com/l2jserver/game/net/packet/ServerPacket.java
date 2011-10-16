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
package com.l2jserver.game.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Client;

/**
 * Each implementation is an packet sent by the game server.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ServerPacket extends Packet {
	/**
	 * Writes this packet binary data.
	 * <p>
	 * Please do not write packets from this method! This is only used to test
	 * compatibility of protocols.
	 * 
	 * @param client
	 *            the connection
	 * @param buffer
	 *            the buffer
	 */
	void write(Lineage2Client client, ChannelBuffer buffer);

	/**
	 * Get the opcode id of this packet
	 * 
	 * @return the opcode id
	 */
	int getOpcode();
}
