package com.l2jserver.game.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;

public interface ServerPacket extends Packet {
	/**
	 * Writes this packet binary data.
	 * <p>
	 * Please do not write packets from this method! This is only used to test
	 * compatibility of protocols.
	 * 
	 * @param conn
	 *            the connection
	 * @param buffer
	 *            the buffer
	 */
	void write(Lineage2Connection conn, ChannelBuffer buffer);

	/**
	 * Get the opcode id of this packet
	 * 
	 * @return the opcode id
	 */
	int getOpcode();
}
