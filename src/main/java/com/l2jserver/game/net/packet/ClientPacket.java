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
package com.l2jserver.game.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Client;

/**
 * Each implementation is an packet sent by the game client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ClientPacket extends Packet {
	/**
	 * Read binary data in the {@link ChannelBuffer}.
	 * <p>
	 * Please do not write packets from this method. If you need to close the
	 * connection or write packets do it in {@link #process(Lineage2Client)}.
	 * 
	 * @param client
	 *            the active connection
	 * @param buffer
	 *            the buffer
	 */
	void read(Lineage2Client client, ChannelBuffer buffer);

	/**
	 * Process the packet. Executes all needed operations.
	 * 
	 * @param conn
	 *            The active Lineage2Connection
	 */
	void process(Lineage2Client conn);
}
