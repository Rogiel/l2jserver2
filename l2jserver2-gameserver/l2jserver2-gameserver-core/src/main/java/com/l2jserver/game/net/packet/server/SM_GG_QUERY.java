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

import com.google.common.base.Preconditions;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;

/**
 * This packet send the GameGuard query to the client. The client will send an
 * notification, but this can be ignored if GG is not supposed to be enforced.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_GG_QUERY extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0xf9;
	/**
	 * The GG key
	 */
	private final int[] key;

	/**
	 * @param key
	 *            the game guard key
	 */
	public SM_GG_QUERY(int[] key) {
		super(OPCODE);
		Preconditions.checkArgument(key.length == 4,
				"key must by an 4-length array");
		this.key = key;
	}

	/**
	 * @param key1
	 *            the game guard key 1
	 * @param key2
	 *            the game guard key 2
	 * @param key3
	 *            the game guard key 3
	 * @param key4
	 *            the game guard key 4
	 */
	public SM_GG_QUERY(int key1, int key2, int key3, int key4) {
		super(OPCODE);
		this.key = new int[4];
		this.key[0] = key1;
		this.key[1] = key2;
		this.key[2] = key3;
		this.key[3] = key4;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		for (final int part : key) {
			buffer.writeInt(part);
		}
	}
}
