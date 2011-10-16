/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.service.network.gameguard.GameGuardService;

/**
 * The client is requesting a logout. Currently, when this packet is received
 * the connection is immediately closed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_GG_KEY extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0xcb;

	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory.getLogger(CM_GG_KEY.class);

	/**
	 * The {@link GameGuardService}
	 */
	private final GameGuardService ggService;

	/**
	 * The Game guard authentication key
	 */
	private byte[] key = new byte[8];

	@Inject
	public CM_GG_KEY(GameGuardService ggService) {
		this.ggService = ggService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		byte[] part1 = buffer.readBytes(4).array();
		buffer.readInt();
		byte[] part2 = buffer.readBytes(4).array();

		// create a single key array
		System.arraycopy(part1, 0, key, 0, 4);
		System.arraycopy(part2, 0, key, 4, 4);
	}

	@Override
	public void process(final Lineage2Client conn) {
		log.debug("Received GG key");
		switch (ggService.key(conn, key)) {
		case INVALID:
			log.warn("Client {} sent an invalid GG key", conn);
			// if key is invalid, disconnect client
			conn.close();
			return;
		}
	}
}
