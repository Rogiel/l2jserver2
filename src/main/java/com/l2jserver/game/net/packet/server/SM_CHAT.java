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
package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.game.chat.ChatMessageDestination;
import com.l2jserver.util.BufferUtils;

/**
 * This packet notifies the client that the chosen character has been
 * successfully selected.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAT extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x4a;

	/**
	 * The sending actor
	 */
	private final Actor actor;
	/**
	 * The message destination
	 */
	private ChatMessageDestination destination;
	/**
	 * The message
	 */
	private String message = null;
	/**
	 * The message ID
	 */
	private int messageID = 0;

	public SM_CHAT(Actor character, ChatMessageDestination destination,
			String message) {
		super(OPCODE);
		this.actor = character;
		this.destination = destination;
		this.message = message;
	}

	public SM_CHAT(Actor actor, ChatMessageDestination destination,
			int messageID) {
		super(OPCODE);
		this.actor = actor;
		this.destination = destination;
		this.messageID = messageID;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(actor.getID().getID());
		buffer.writeInt(destination.id);
		if (actor instanceof L2Character) {
			BufferUtils.writeString(buffer, ((L2Character) actor).getName());
		} else {
			buffer.writeInt(actor.getID().getID());
		}
		if (message != null) {
			BufferUtils.writeString(buffer, message);
		} else {
			buffer.writeInt(messageID);
		}
	}
}
