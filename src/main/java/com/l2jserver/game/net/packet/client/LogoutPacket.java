package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;

/**
 * The client is requesting a logout. Currently, when this packet is received
 * the connection is immediately closed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class LogoutPacket extends AbstractClientPacket {
	public static final int OPCODE = 0x00;

	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(LogoutPacket.class);

	@Override
	public void read(ChannelBuffer buffer) {
	}

	@Override
	public void process(final Lineage2Connection conn) {
		log.debug("Logging out client {}", conn);
		conn.close();
	}
}
