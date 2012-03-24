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
package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.model.world.Actor;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;

/**
 * This packet notifies the client that the chosen character has been
 * successfully selected.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_ACTOR_POSITION extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x79;

	/**
	 * The selected character
	 */
	private final Actor actor;

	/**
	 * @param actor the actor
	 */
	public SM_ACTOR_POSITION(Actor actor) {
		super(OPCODE);
		this.actor = actor;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(actor.getID().getID());
		buffer.writeInt(actor.getPoint().getX());
		buffer.writeInt(actor.getPoint().getY());
		buffer.writeInt(actor.getPoint().getZ());
		buffer.writeInt((int) actor.getPoint().getAngle());
	}
}
