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

/**
 * An packet authorizing the client to open the map
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterOpenMap extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0xa3;

	/**
	 * The map ID
	 */
	private final int mapID;

	public CharacterOpenMap(int mapID) {
		super(OPCODE);
		this.mapID = mapID;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(mapID);
		buffer.writeByte(0x00); // seven signs period
	}
}
