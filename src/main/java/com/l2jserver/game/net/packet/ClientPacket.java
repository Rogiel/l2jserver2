package com.l2jserver.game.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Injector;

public interface ClientPacket extends Packet {
	/**
	 * Read binary data in the {@link ChannelBuffer}.
	 * 
	 * @param buffer
	 *            the buffer
	 */
	void read(ChannelBuffer buffer);

	/**
	 * Process the packet
	 * 
	 * @param injector
	 *            the injector
	 */
	void process(Injector injector);
}
