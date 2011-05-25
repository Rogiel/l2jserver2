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

import java.util.Map;
import java.util.Map.Entry;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.game.net.packet.server.CharacterCreateFailPacket.Reason;
import com.l2jserver.model.world.Actor;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This packet notifies the client that the chosen character has been
 * successfully selected.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see Reason
 */
public class ActorStatusUpdatePacket extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x18;

	/**
	 * The stats the can be updated with the packet
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum Stat {
		LEVEL(0x01), EXPERIENCE(0x02), STR(0x03), DEX(0x04), CON(0x05), INT(
				0x06), WIT(0x07), MEN(0x08),

		HP(0x09), MAX_HP(0x0a), MP(0x0b), MAX_MP(0x0c),

		SP(0x0d), LOAD(0x0e), MAX_LOAD(0x0f),

		PHYSICAL_ATK(0x11), ATTACK_SPEED(0x12), PHYSICAL_DEFENSE(0x13), EVASION(
				0x14), ACCURACY(0x15), CRITICAL(0x16), MAGICAL_ATTACK(0x17), CAST_SPEED(
				0x18), MAGICAL_DEFENSE(0x19), PVP_FLAG(0x1a), KARMA(0x1b),

		CP(0x21), MAX_CP(0x22);

		public final int id;

		Stat(int id) {
			this.id = id;
		}
	}

	private final Map<Stat, Integer> update = CollectionFactory.newMap();
	private final Actor actor;

	public ActorStatusUpdatePacket(Actor actor) {
		super(OPCODE);
		this.actor = actor;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(actor.getID().getID());
		buffer.writeInt(update.size());

		for (Entry<Stat, Integer> entry : update.entrySet()) {
			buffer.writeInt(entry.getKey().id);
			buffer.writeInt(entry.getValue());
		}
	}

	public ActorStatusUpdatePacket add(Stat stat, int value) {
		update.put(stat, value);
		return this;
	}
}
