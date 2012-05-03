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

import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.ActorExperience;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.ProtocolVersion;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;
import com.l2jserver.util.BufferUtils;

/**
 * This packet notifies the client that the chosen character has been
 * successfully selected.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_SELECTED extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x15;

	/**
	 * The selected character
	 */
	private final L2Character character;

	/**
	 * @param character
	 *            the character
	 */
	public SM_CHAR_SELECTED(L2Character character) {
		super(OPCODE);
		this.character = character;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		BufferUtils.writeString(buffer, character.getName());
		buffer.writeInt(character.getID().getID());
		BufferUtils.writeString(buffer, "It works!"); // title
		buffer.writeInt(conn.getSession().getPlayKey1());
		buffer.writeInt((character.getClanID() != null ? character.getClanID()
				.getID() : 0));
		buffer.writeInt(0x00); // ??
		buffer.writeInt(character.getSex().option);
		buffer.writeInt(character.getRace().id);
		buffer.writeInt(character.getCharacterClass().id);
		buffer.writeInt(0x01); // active ??
		buffer.writeInt(character.getPoint().getX());
		buffer.writeInt(character.getPoint().getY());
		buffer.writeInt(character.getPoint().getZ());

		buffer.writeDouble(20); // cur hp
		buffer.writeDouble(20); // cur mp
		buffer.writeInt(0); // sp
		buffer.writeLong(ActorExperience.LEVEL_1.experience);
		buffer.writeInt(ActorExperience.LEVEL_1.level);
		buffer.writeInt(0); // karma
		buffer.writeInt(0); // pk
		buffer.writeInt(character.getStats().getIntelligence());
		buffer.writeInt(character.getStats().getStrength());
		buffer.writeInt(character.getStats().getConcentration());
		buffer.writeInt(character.getStats().getMentality());
		buffer.writeInt(character.getStats().getDexterity());
		buffer.writeInt(character.getStats().getWitness());

		// TODO this really needs some refining!
		if (conn.supports(ProtocolVersion.FREYA)) {
			buffer.writeInt(0); // game time
			buffer.writeInt(0x00); // unk

			buffer.writeInt(character.getCharacterClass().id);

			buffer.writeInt(0x00);// unk
			buffer.writeInt(0x00);// unk
			buffer.writeInt(0x00);// unk
			buffer.writeInt(0x00);// unk

			buffer.writeBytes(new byte[64]); // unk
			buffer.writeInt(0x00);// unk
		} else {
			for (int i = 0; i < 30; i++) {
				buffer.writeInt(0x00);
			}

			buffer.writeInt(0); // unk
			buffer.writeInt(0x00); // unk

			buffer.writeInt(0); // game time

			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
		}
	}
}
