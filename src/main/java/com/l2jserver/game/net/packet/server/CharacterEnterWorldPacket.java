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
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.ActorExperience;
import com.l2jserver.util.BufferUtils;

/**
 * An packet informing that the character was created with success.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterEnterWorldPacket extends AbstractServerPacket {
	public static final int OPCODE = 0x0b;

	private final L2Character character;
	private final int sessionId;

	public CharacterEnterWorldPacket(L2Character character, int sessionId) {
		super(OPCODE);
		this.character = character;
		this.sessionId = sessionId;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		BufferUtils.writeString(buffer, character.getName());
		buffer.writeInt(character.getID().getID());
		BufferUtils.writeString(buffer, "Hello world!");
		buffer.writeInt(sessionId);
		buffer.writeInt(0x00); // clan id
		buffer.writeInt(0x00); // ??
		buffer.writeInt(character.getSex().option);
		buffer.writeInt(character.getRace().id);
		buffer.writeInt(character.getCharacterClass().id);
		buffer.writeInt(0x01); // active ??
		buffer.writeInt(character.getPosition().getX());
		buffer.writeInt(character.getPosition().getY());
		buffer.writeInt(character.getPosition().getZ());

		buffer.writeDouble(100);
		buffer.writeDouble(100);
		buffer.writeInt(0x00);
		buffer.writeLong(ActorExperience.LEVEL_1.experience);
		buffer.writeInt(ActorExperience.LEVEL_1.level);
		buffer.writeInt(0x00); // karma
		buffer.writeInt(0x00); // pk
		buffer.writeInt(character.getAttributes().getIntelligence()); // INT
		buffer.writeInt(character.getAttributes().getStrength()); // STR
		buffer.writeInt(character.getAttributes().getConcentration()); // CON
		buffer.writeInt(character.getAttributes().getMentality()); // MEN
		buffer.writeInt(character.getAttributes().getDexterity()); // DEX
		buffer.writeInt(character.getAttributes().getWitness()); // WIT

		buffer.writeInt(250); // game time
		buffer.writeInt(0x00);

		buffer.writeInt(character.getCharacterClass().id);

		buffer.writeInt(0x00);
		buffer.writeInt(0x00);
		buffer.writeInt(0x00);
		buffer.writeInt(0x00);

		buffer.writeBytes(new byte[64]);
		buffer.writeInt(0x00);
	}
}
