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
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.service.game.CharacterService;

/**
 * The client is requesting a logout. Currently, when this packet is received
 * the connection is immediately closed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class EnterWorld extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x11;

	private final Logger log = LoggerFactory.getLogger(EnterWorld.class);

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService characterService;

	@Inject
	public EnterWorld(CharacterService characterService) {
		this.characterService = characterService;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.readBytes(new byte[32]); // Unknown Byte Array
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readBytes(new byte[32]); // Unknown Byte Array
		buffer.readInt(); // Unknown Value
		// TODO parse tracert
		// for (int i = 0; i < 5; i++)
		// for (int o = 0; o < 4; o++)
		// tracert[i][o] = readC();
	}

	@Override
	public void process(final Lineage2Connection conn) {
		final CharacterID id = conn.getCharacterID();
		if (id == null) {
			log.warn(
					"Client {} is trying to enter world without select a character",
					conn);
			conn.close();
			return;
		}
		characterService.enterWorld(id.getObject());
	}
}
