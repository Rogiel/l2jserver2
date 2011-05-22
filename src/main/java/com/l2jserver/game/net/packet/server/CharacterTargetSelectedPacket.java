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
import com.l2jserver.game.net.packet.server.CharacterCreateFailPacket.Reason;
import com.l2jserver.model.world.Actor;

/**
 * This packet notifies the client that the chosen character has been
 * successfully selected.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see Reason
 */
public class CharacterTargetSelectedPacket extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0xb9;

	/**
	 * The selected character
	 */
	private final Actor object;
	private int color;

	public CharacterTargetSelectedPacket(Actor object, int color) {
		super(OPCODE);
		this.object = object;
		this.color = color;
	}

	public CharacterTargetSelectedPacket(Actor object) {
		this(object, 0);
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(object.getID().getID());
		buffer.writeShort(color);
		buffer.writeInt(0x00);
	}
}
