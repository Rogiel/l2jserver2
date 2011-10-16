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

/**
 * This packet responds to the Restart request
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_ACTION_FAILED extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x1f;

	/**
	 * The {@link SM_ACTION_FAILED} shared instance
	 */
	public static final SM_ACTION_FAILED SHARED_INSTANCE = new SM_ACTION_FAILED();

	/**
	 * Creates a new instance
	 */
	public SM_ACTION_FAILED() {
		super(OPCODE);
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
	}
}
