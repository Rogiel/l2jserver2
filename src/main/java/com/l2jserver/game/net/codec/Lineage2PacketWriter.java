package com.l2jserver.game.net.codec;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.ServerPacket;

public class Lineage2PacketWriter extends OneToOneEncoder {
	public static final String HANDLER_NAME = "packet.writer";

	private static final Logger log = LoggerFactory
			.getLogger(Lineage2PacketWriter.class);

	private Lineage2Connection connection;

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ServerPacket))
			return msg;
		final ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(
				ByteOrder.LITTLE_ENDIAN, 10);
		final ServerPacket packet = (ServerPacket) msg;
		buffer.writeShort(0x0000);
		buffer.writeByte(packet.getOpcode()); // packet opcode
		packet.write(null, buffer);

		log.debug("Writing message {}", ChannelBuffers.hexDump(buffer));

		return buffer;
	}

	/**
	 * @return the connection
	 */
	public Lineage2Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Lineage2Connection connection) {
		this.connection = connection;
	}
}
