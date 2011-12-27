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
public class SM_CHAR_RESTART extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x71;

	/**
	 * The restart state
	 */
	private boolean state;

	/**
	 * @param state
	 *            the state
	 */
	public SM_CHAR_RESTART(boolean state) {
		super(OPCODE);
		this.state = state;
	}

	/**
	 * @return an OK instance of this packet
	 */
	public static SM_CHAR_RESTART ok() {
		return new SM_CHAR_RESTART(true);
	}

	/**
	 * @return an FAILED instance of this packet
	 */
	public static SM_CHAR_RESTART denied() {
		return new SM_CHAR_RESTART(false);
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeByte((state ? 0x01 : 0x00));
	}
}
