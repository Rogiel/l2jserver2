package com.l2jserver.game.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * This encoder creates Lineage II frames. Each frame is has a header of 2 bytes
 * unsigned short.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2FrameEncoder extends OneToOneEncoder {
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer))
			return msg;
		final ChannelBuffer buffer = (ChannelBuffer) msg;
		buffer.setShort(0, buffer.readableBytes());

		return buffer;
	}
}
