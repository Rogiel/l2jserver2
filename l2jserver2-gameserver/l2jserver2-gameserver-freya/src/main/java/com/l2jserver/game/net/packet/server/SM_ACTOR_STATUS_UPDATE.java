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

import java.util.Map;
import java.util.Map.Entry;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.model.world.Actor;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This packet notifies the client that the chosen character has been
 * successfully selected.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_ACTOR_STATUS_UPDATE extends AbstractServerPacket {
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
		/**
		 * Updates the character level
		 */
		LEVEL(0x01),
		/**
		 * Updates the character experience
		 */
		EXPERIENCE(0x02),
		/**
		 * Updates the character strength
		 */
		STR(0x03),
		/**
		 * Updates the character dexterity
		 */
		DEX(0x04),

		/**
		 * Updates the character concentration
		 */
		CON(0x05),
		/**
		 * Updates the character intelligence
		 */
		INT(0x06),
		/**
		 * Updates the character witness
		 */
		WIT(0x07),
		/**
		 * Updates the character mentality
		 */
		MEN(0x08),
		/**
		 * Updates the character hp
		 */
		HP(0x09),
		/**
		 * Updates the character maximum hp
		 */
		MAX_HP(0x0a),
		/**
		 * Updates the character hp
		 */
		MP(0x0b),
		/**
		 * Updates the character maximum mp
		 */
		MAX_MP(0x0c),
		/**
		 * Updates the character sp
		 */
		SP(0x0d),
		/**
		 * Updates the character load
		 */
		LOAD(0x0e),
		/**
		 * Updates the character maximum load
		 */
		MAX_LOAD(0x0f),
		/**
		 * Updates the character physical attack
		 */
		PHYSICAL_ATK(0x11),
		/**
		 * Updates the character attack speed
		 */
		ATTACK_SPEED(0x12),
		/**
		 * Updates the character physical defense
		 */
		PHYSICAL_DEFENSE(0x13),
		/**
		 * Updates the character evasion
		 */
		EVASION(0x14),
		/**
		 * Updates the character accuracy
		 */
		ACCURACY(0x15),
		/**
		 * Updates the character critical
		 */
		CRITICAL(0x16),
		/**
		 * Updates the character magical attack
		 */
		MAGICAL_ATTACK(0x17),
		/**
		 * Updates the character cast speed
		 */
		CAST_SPEED(0x18),
		/**
		 * Updates the character magical defense
		 */
		MAGICAL_DEFENSE(0x19),

		/**
		 * Updates the character pvp flag
		 */
		PVP_FLAG(0x1a),

		/**
		 * Updates the character karma
		 */
		KARMA(0x1b),

		/**
		 * Updates the character cp
		 */
		CP(0x21),

		/**
		 * Updates the character max cp
		 */
		MAX_CP(0x22);

		/**
		 * The stat id
		 */
		public final int id;

		/**
		 * @param id
		 *            the stat id
		 */
		Stat(int id) {
			this.id = id;
		}
	}

	/**
	 * The set of updates to be sent
	 */
	private final Map<Stat, Integer> update = CollectionFactory.newMap();
	/**
	 * The actor to be updated
	 */
	private final Actor actor;

	/**
	 * @param actor
	 *            the actor
	 */
	public SM_ACTOR_STATUS_UPDATE(Actor actor) {
		super(OPCODE);
		this.actor = actor;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(actor.getID().getID());
		buffer.writeInt(update.size());

		for (Entry<Stat, Integer> entry : update.entrySet()) {
			buffer.writeInt(entry.getKey().id);
			buffer.writeInt(entry.getValue());
		}
	}

	/**
	 * @param stat
	 *            the stat
	 * @param value
	 *            the stat value
	 * @return this instances
	 */
	public SM_ACTOR_STATUS_UPDATE add(Stat stat, int value) {
		update.put(stat, value);
		return this;
	}
}
