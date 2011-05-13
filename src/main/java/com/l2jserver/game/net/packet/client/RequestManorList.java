package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.game.net.packet.server.ManorListPacket;

/**
 * The client is requesting a logout. Currently, when this packet is received
 * the connection is immediately closed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RequestManorList extends AbstractClientPacket {
	public static final int OPCODE1 = 0xd0;
	public static final int OPCODE2 = 0x01;

	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(RequestManorList.class);

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
	}

	@Override
	public void process(final Lineage2Connection conn) {
		conn.write(new ManorListPacket("gludio", "dion", "giran", "oren",
				"aden", "innadril", "goddard", "rune", "schuttgart"));
	}
}
