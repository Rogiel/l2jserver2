package com.l2jserver.game.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.packet.ClientPacket;
import com.l2jserver.game.net.packet.client.AuthLoginPacket;
import com.l2jserver.game.net.packet.client.ProtocolVersionPacket;

public class Lineage2PacketReader extends OneToOneDecoder {
	private final Injector injector;
	private final Logger logger = LoggerFactory
			.getLogger(Lineage2PacketReader.class);

	@Inject
	public Lineage2PacketReader(Injector injector) {
		this.injector = injector;
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer))
			return msg;
		final ChannelBuffer buffer = (ChannelBuffer) msg;
		final ClientPacket packet = createPacket(getPacketClass(buffer));
		if (packet == null)
			return null;
		packet.read(buffer);
		return packet;
	}

	private ClientPacket createPacket(Class<? extends ClientPacket> type) {
		if (type == null)
			return null;
		return injector.getInstance(type);
	}

	private Class<? extends ClientPacket> getPacketClass(ChannelBuffer buffer) {
		final short opcode = buffer.readUnsignedByte();
		switch (opcode) {
		case ProtocolVersionPacket.OPCODE:
			return ProtocolVersionPacket.class;
		case AuthLoginPacket.OPCODE:
			return AuthLoginPacket.class;
		default:
			logger.info("Unknown opcode: " + Integer.toHexString(opcode));
			break;
		}
		return null;
	}
}
