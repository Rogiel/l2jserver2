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
import com.l2jserver.model.server.AttackHit;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;

/**
 * This packet makes an character pick up an item
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see AttackHit
 */
public class SM_ITEM_PICK extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x17;

	/**
	 * The {@link Item} being picked up
	 */
	private final Item item;
	/**
	 * The {@link L2Character} picking the item
	 */
	private final L2Character character;

	/**
	 * @param character
	 *            the character that is picking the <code>item</code>
	 * @param item
	 *            the item that is being picked
	 */
	public SM_ITEM_PICK(L2Character character, Item item) {
		super(OPCODE);
		this.item = item;
		this.character = character;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(character.getID().getID());
		buffer.writeInt(item.getID().getID());

		buffer.writeInt(item.getPoint().getX());
		buffer.writeInt(item.getPoint().getY());
		buffer.writeInt(item.getPoint().getZ());
	}
}
