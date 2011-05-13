package com.l2jserver.game.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;

public interface ClientPacket extends Packet {
	/**
	 * Read binary data in the {@link ChannelBuffer}.
	 * <p>
	 * Please do not write packets from this method. If you need to close the
	 * connection or write packets do it in {@link #process(Lineage2Connection)}.
	 * 
	 * @param conn
	 *            the active connection
	 * @param buffer
	 *            the buffer
	 */
	void read(Lineage2Connection conn, ChannelBuffer buffer);

	/**
	 * Process the packet. Executes all needed operations.
	 * 
	 * @param conn
	 *            The active Lineage2Connection
	 */
	void process(Lineage2Connection conn);
}
