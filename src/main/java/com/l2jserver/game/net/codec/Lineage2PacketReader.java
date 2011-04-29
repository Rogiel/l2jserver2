package com.l2jserver.game.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.l2jserver.game.net.packet.ClientPacket;
import com.l2jserver.game.net.packet.client.ProtocolVersionPacket;

public class Lineage2PacketReader extends OneToOneDecoder {
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer))
			return msg;
		final ChannelBuffer buffer = (ChannelBuffer) msg;
		final ClientPacket packet = getPacket(buffer);
		if (packet == null)
			return null;
		packet.read(buffer);
		return packet;
	}

	private ClientPacket getPacket(ChannelBuffer buffer) {
		final short opcode = buffer.readUnsignedByte();
		switch (opcode) {
		case ProtocolVersionPacket.OPCODE:
			return new ProtocolVersionPacket();
		case 0x2b:
			return null;
		default:
			System.out.println("Unk: " + opcode);
			break;
		}
		return null;
	}
}
