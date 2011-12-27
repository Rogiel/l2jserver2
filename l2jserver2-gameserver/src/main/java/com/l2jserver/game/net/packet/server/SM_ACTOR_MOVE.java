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

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.world.Actor;
import com.l2jserver.util.geometry.Coordinate;

/**
 * This packet notifies the client that the character is moving to an certain
 * point. If the {@link Actor} moving is the same as the client connected, the
 * client will send position validations at specific time intervals.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a> O
 */
public class SM_ACTOR_MOVE extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x2f;

	/**
	 * The selected character
	 */
	private final Actor actor;
	/**
	 * The destination coordinate
	 */
	private Coordinate target;

	/**
	 * @param actor the actor
	 * @param target the target
	 */
	public SM_ACTOR_MOVE(Actor actor, Coordinate target) {
		super(OPCODE);
		this.actor = actor;
		this.target = target;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(actor.getID().getID());

		// target
		buffer.writeInt(target.getX());
		buffer.writeInt(target.getY());
		buffer.writeInt(target.getZ());

		// source
		buffer.writeInt(actor.getPoint().getX());
		buffer.writeInt(actor.getPoint().getY());
		buffer.writeInt(actor.getPoint().getZ());
	}
}
