package com.l2jserver.game.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;

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
	 * @param conn
	 *            The active Lineage2Connection
	 */
	void process(Lineage2Connection conn);
}
