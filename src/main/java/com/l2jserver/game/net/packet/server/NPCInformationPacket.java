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
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.NPC;
import com.l2jserver.util.BufferUtils;

/**
 * This packet sends to the client an actor information about an actor (except
 * players)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCInformationPacket extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x0c;

	private final NPC npc;

	public NPCInformationPacket(NPC npc) {
		super(OPCODE);
		this.npc = npc;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		final NPCTemplate template = npc.getTemplate();

		buffer.writeInt(npc.getID().getID());
		buffer.writeInt(template.getID().getID() + 1000000); // npctype id
		buffer.writeInt((template.isAttackable() ? 0x01 : 0x00));
		buffer.writeInt(npc.getPoint().getX());
		buffer.writeInt(npc.getPoint().getY());
		buffer.writeInt(npc.getPoint().getZ());
		buffer.writeInt((int) npc.getPoint().getAngle());
		buffer.writeInt(0x00); // unk
		buffer.writeInt(template.getCastSpeed());
		buffer.writeInt(template.getAttackSpeed());
		buffer.writeInt((int) template.getMoveSpeed());
		buffer.writeInt((int) template.getMoveSpeed());
		buffer.writeInt((int) template.getMoveSpeed()); // swim run speed
		buffer.writeInt((int) template.getMoveSpeed()); // swim walk speed
		buffer.writeInt((int) template.getMoveSpeed()); // swim run speed
		buffer.writeInt((int) template.getMoveSpeed()); // swim walk speed
		buffer.writeInt((int) template.getMoveSpeed()); // fly run speed
		buffer.writeInt((int) template.getMoveSpeed()); // fly run speed
		buffer.writeDouble(template.getMovementSpeedMultiplier());
		buffer.writeDouble(template.getAttackSpeedMultiplier());
		buffer.writeDouble(template.getCollisionRadius());
		buffer.writeDouble(template.getCollisionHeight());
		buffer.writeInt(0x00); // right hand weapon
		buffer.writeInt(0x00); // chest
		buffer.writeInt(0x00); // left hand weapon
		buffer.writeByte(1); // name above char 1=true ... ??
		buffer.writeByte(0x00); // is running
		buffer.writeByte(0x00); // is in combat
		buffer.writeByte(0x00); // is like dead (faking)
		buffer.writeByte(0x00); // 0=teleported 1=default 2=summoned
		BufferUtils.writeString(buffer, template.getName());
		BufferUtils.writeString(buffer, template.getTitle());
		buffer.writeInt(0x00); // Title color 0=client default
		buffer.writeInt(0x00); // pvp flag
		buffer.writeInt(0x00); // karma

		buffer.writeInt(0x00); // C2 - abnormal effect
		buffer.writeInt(0x00); // clan id
		buffer.writeInt(0x00); // crest id
		buffer.writeInt(0x00); // ally id
		buffer.writeInt(0x00); // all crest
		buffer.writeByte(0x00); // C2 - is flying
		buffer.writeByte(0x00); // title color 0=client

		buffer.writeDouble(template.getCollisionRadius());
		buffer.writeDouble(template.getCollisionHeight());
		buffer.writeInt(0x00); // C4 - enchant effect
		buffer.writeInt(0x00); // C6 -- is flying
		buffer.writeInt(0x00); // unk
		buffer.writeInt(0x00);// CT1.5 Pet form and skills, Color effect
		buffer.writeByte(0x00); // hide name
		buffer.writeByte(0x00); // hide name, again

		buffer.writeInt(0x00); // special effects
		buffer.writeInt(0x00); // display effect
	}
}
