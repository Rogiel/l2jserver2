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
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This decoder parses Lineage II frames. Each frame is has a header of 2 bytes
 * unsigned short.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2FrameDecoder extends FrameDecoder {
	private static final int HEADER_SIZE = 2;

	private static final Logger logger = LoggerFactory
			.getLogger(Lineage2FrameDecoder.class);

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer oldBuffer) throws Exception {
		if (oldBuffer.readableBytes() < 2)
			return null;
		ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(oldBuffer
				.toByteBuffer().order(ByteOrder.LITTLE_ENDIAN));

		buffer.markReaderIndex();
		final int pending = buffer.readUnsignedShort() - HEADER_SIZE;
		if (pending == 0)
			return null;
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
