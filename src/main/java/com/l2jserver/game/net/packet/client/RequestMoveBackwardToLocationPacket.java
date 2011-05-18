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

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.game.net.packet.server.CharacterStopMovePacket;
import com.l2jserver.game.net.packet.server.CharacterTeleportPacket;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * This packet notifies the server which character the player has chosen to use.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RequestMoveBackwardToLocationPacket extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x0f;

	// packet
	private Coordinate target;
	private Coordinate origin;
	private int moveMovement;

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		this.target = Coordinate.fromXYZ(buffer.readInt(), buffer.readInt(),
				buffer.readInt());
		this.origin = Coordinate.fromXYZ(buffer.readInt(), buffer.readInt(),
				buffer.readInt());
		// 0 keyboard, 1 mouse
		this.moveMovement = buffer.readInt(); // seems that L2Walker does not
												// send this
	}

	@Override
	public void process(final Lineage2Connection conn) {
		if (target.equals(origin)) {
			conn.write(new CharacterStopMovePacket(conn.getCharacter()));
			return;
		}
		if (target.getDistance(origin) >= 98010000) {
			// TODO send action failed message!
			return;
		}
		final L2Character character = conn.getCharacter();
		character.setPosition(target);
		
		conn.broadcast(new CharacterTeleportPacket(character));
	}
}
