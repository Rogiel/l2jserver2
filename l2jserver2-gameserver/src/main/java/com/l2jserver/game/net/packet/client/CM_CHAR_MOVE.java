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
import com.l2jserver.game.net.packet.server.SM_CHAR_STOP;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.util.geometry.Coordinate;

/**
 * This packet notifies the server which character the player has chosen to use.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_MOVE extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x0f;

	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService charService;

	// packet
	private Coordinate target;
	private Coordinate origin;
	@SuppressWarnings("unused")
	private MovementType type;

	public enum MovementType {
		MOUSE(0x01), KEYBOARD(0x00);

		public final int id;

		MovementType(int id) {
			this.id = id;
		}

		public static MovementType fromID(int id) {
			for (final MovementType type : values()) {
				if (type.id == id)
					return type;
			}
			return null;
		}
	}

	@Inject
	public CM_CHAR_MOVE(CharacterService charService) {
		this.charService = charService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		this.target = Coordinate.fromXYZ(buffer.readInt(), buffer.readInt(),
				buffer.readInt());
		this.origin = Coordinate.fromXYZ(buffer.readInt(), buffer.readInt(),
				buffer.readInt());
		// seems that L2Walker does not send this
		if (buffer.readableBytes() >= 4) {
			// 0 keyboard, 1 mouse
			this.type = MovementType.fromID(buffer.readInt());
		}
	}

	@Override
	public void process(final Lineage2Client conn) {
		if (target.equals(origin)) {
			log.debug("Target is same as origin. Stopping character.");
			conn.write(new SM_CHAR_STOP(conn.getCharacter()));
			return;
		}
		if (target.getDistance(origin) >= 98010000) {
			log.warn(
					"Character {} is trying to move a really big distance: {}",
					conn.getCharacter(), target.getDistance(origin));
			// TODO send action failed message!
			return;
		}
		final L2Character character = conn.getCharacter();
		log.debug("Character {} is moving from {} to {}", new Object[] {
				character, origin, target });
		charService.move(character, target);
	}
}
