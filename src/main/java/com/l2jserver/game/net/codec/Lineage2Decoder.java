package com.l2jserver.game.net.codec;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class Lineage2Decoder extends FrameDecoder {
	private static final int HEADER_SIZE = 2;

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer oldBuffer) throws Exception {
		if (oldBuffer.readableBytes() < 2)
			return null;
		ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(oldBuffer
				.toByteBuffer().order(ByteOrder.LITTLE_ENDIAN));

		buffer.markReaderIndex();
		final int pending = buffer.readUnsignedShort() - HEADER_SIZE;
		System.out.println(ChannelBuffers.hexDump(buffer));
		if (pending == 0) {
			return null;
		}

		if (buffer.readableBytes() < pending) {
			buffer.resetReaderIndex();
			return null;
		}

		final ChannelBuffer b = buffer.copy(buffer.readerIndex(), pending);
		oldBuffer.skipBytes(pending + HEADER_SIZE);
		return ChannelBuffers.wrappedBuffer(b.toByteBuffer().order(
				ByteOrder.LITTLE_ENDIAN));
	}
}
