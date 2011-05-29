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
import com.l2jserver.game.net.packet.server.SM_CHAR_INVENTORY;
import com.l2jserver.model.world.L2Character;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_REQ_INVENTORY extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x14;

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
	}

	@Override
	public void process(final Lineage2Connection conn) {
		final L2Character character = conn.getCharacter();
		conn.write(new SM_CHAR_INVENTORY(character.getInventory()));
	}
}
