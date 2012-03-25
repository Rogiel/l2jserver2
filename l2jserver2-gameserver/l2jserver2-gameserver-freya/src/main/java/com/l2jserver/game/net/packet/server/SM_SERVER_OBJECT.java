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

import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;
import com.l2jserver.util.BufferUtils;

/**
 * This packet sends to the client an actor information about an actor (except
 * players)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_SERVER_OBJECT extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x92;
	/**
	 * The {@link NPC}
	 */
	private final NPC npc;

	/**
	 * @param npc
	 *            the npc
	 */
	public SM_SERVER_OBJECT(NPC npc) {
		super(OPCODE);
		this.npc = npc;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		final NPCTemplate template = npc.getTemplate();

		buffer.writeInt(npc.getID().getID()); // obj id
		buffer.writeInt(npc.getTemplateID().getID() + 1000000); // template id
		BufferUtils
				.writeString(buffer, template.getInfo().getName().getValue()); // name
		buffer.writeInt((template.getInfo().isAttackable() ? 0x01 : 0x00)); // attackable
		buffer.writeInt(npc.getPoint().getX()); // x
		buffer.writeInt(npc.getPoint().getY()); // y
		buffer.writeInt(npc.getPoint().getZ()); // z
		buffer.writeInt((int) npc.getPoint().getAngle()); // angle
		buffer.writeDouble(0x01); // move mult
		buffer.writeDouble(0x01); // attack spd mult
		buffer.writeDouble(template.getInfo().getCollision().getRadius());
		buffer.writeDouble(template.getInfo().getCollision().getHeigth());
		buffer.writeInt((int) (template.getInfo().isAttackable() ? npc.getHP()
				: 0x00));
		buffer.writeInt((int) (template.getInfo().isAttackable() ? template
				.getInfo().getStats().getHp() : 0x00));
		buffer.writeInt(0x01); // object type
		buffer.writeInt(0x00); // special effects
	}
}
