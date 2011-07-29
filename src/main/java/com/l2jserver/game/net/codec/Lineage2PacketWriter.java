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
package com.l2jserver.game.net.codec;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.ServerPacket;

/**
 * This encoder writes the frame content and encodes the packet in it. Each
 * packet has an fixed single opcode byte. Once the packet opcode has been
 * written, packet data is written by the {@link ServerPacket} class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2PacketWriter extends OneToOneEncoder {
	/**
	 * The handler name
	 */
	public static final String HANDLER_NAME = "packet.writer";

	/**
	 * The active Lineage 2 connection
	 */
	private Lineage2Client connection;

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ServerPacket))
			return msg;
		final ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(
				ByteOrder.LITTLE_ENDIAN, 10);
		final ServerPacket packet = (ServerPacket) msg;
		buffer.writeShort(0);
		buffer.writeByte(packet.getOpcode()); // packet opcode
		packet.write(connection, buffer);

		return buffer;
	}

	/**
	 * @return the connection
	 */
	public Lineage2Client getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Lineage2Client connection) {
		this.connection = connection;
	}
}
