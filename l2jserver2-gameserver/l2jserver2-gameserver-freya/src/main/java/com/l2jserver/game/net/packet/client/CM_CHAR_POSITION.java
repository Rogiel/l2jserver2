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

import com.google.inject.Inject;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;
import com.l2jserver.util.geometry.Point3D;

/**
 * This packet notifies the server which character the player has chosen to use.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_POSITION extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x59;

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService charService;

	/**
	 * The current position point
	 */
	private Point3D point;
	/**
	 * Extra data -> vehicle id
	 */
	@SuppressWarnings("unused")
	private int extra; // vehicle id

	/**
	 * @param charService
	 *            the character service
	 */
	@Inject
	public CM_CHAR_POSITION(CharacterService charService) {
		this.charService = charService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		point = Point3D.fromXYZA(buffer.readInt(), buffer.readInt(),
				buffer.readInt(), buffer.readInt());
		extra = buffer.readInt();
	}

	@Override
	public void process(final Lineage2Client conn) {
		charService.receivedValidation(conn.getCharacter(), point);
	}
}
