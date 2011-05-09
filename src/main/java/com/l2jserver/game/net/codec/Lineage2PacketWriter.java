package com.l2jserver.game.net.codec;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.game.net.packet.ServerPacket;

public class Lineage2PacketWriter extends OneToOneEncoder {
	private static final Logger log = LoggerFactory
			.getLogger(Lineage2PacketWriter.class);

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
		packet.write(buffer);

		log.debug("Writing message {}", ChannelBuffers.hexDump(buffer));

		return buffer;
	}
}
