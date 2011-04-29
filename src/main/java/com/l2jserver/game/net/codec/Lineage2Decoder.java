package com.l2jserver.game.net.codec;

import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

public class Lineage2Decoder extends LengthFieldBasedFrameDecoder {
	// private static final int HEADER_SIZE = 2;

	public Lineage2Decoder() {
		super(16 * 1024, 0, 2);
	}

	// @Override
	// protected Object decode(ChannelHandlerContext ctx, Channel channel,
	// ChannelBuffer buffer) throws Exception {
	// if (buffer.readableBytes() < 2)
	// return null;
	// buffer.markReaderIndex();
	// final int pending = buffer.readUnsignedShort() - HEADER_SIZE;
	// if(pending == 0)
	// return null;
	//
	// if (buffer.readableBytes() < pending) {
	// buffer.resetReaderIndex();
	// return null;
	// }
	//
	// return buffer.slice(buffer.readableBytes(), pending);
	// }
}
