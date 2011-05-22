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

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.util.dimensional.Point;

/**
 * This packet notifies the server which character the player has chosen to use.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterValidatePositionPacket extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x59;

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService charService;

	private Point point;
	private int extra; // vehicle id

	@Inject
	public CharacterValidatePositionPacket(CharacterService charService) {
		this.charService = charService;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		point = Point.fromXYZA(buffer.readInt(), buffer.readInt(),
				buffer.readInt(), buffer.readInt());
		extra = buffer.readInt();
	}

	@Override
	public void process(final Lineage2Connection conn) {
		charService.receivedValidation(conn.getCharacter(), point);
	}
}
