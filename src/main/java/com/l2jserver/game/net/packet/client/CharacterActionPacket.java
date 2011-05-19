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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.object.provider.ObjectIDResolver;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterActionPacket extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x1f;

	private final ObjectIDResolver idResolver;

	private int objectId;
	private Coordinate origin;
	private CharacterAction action;

	public enum CharacterAction {
		CLICK(0),
		/**
		 * This is the right click action.
		 */
		RIGHT_CLICK(1);

		public final int id;

		CharacterAction(int id) {
			this.id = id;
		}

		public static CharacterAction fromID(int id) {
			for (final CharacterAction action : values())
				if (action.id == id)
					return action;
			return null;
		}
	}

	@Inject
	public CharacterActionPacket(ObjectIDResolver idResolver) {
		this.idResolver = idResolver;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		this.objectId = buffer.readInt();
		this.origin = Coordinate.fromXYZ(buffer.readInt(), buffer.readInt(),
				buffer.readInt());
		this.action = CharacterAction.fromID(buffer.readByte());
	}

	@Override
	public void process(final Lineage2Connection conn) {
		// since this is an erasure type, this is safe.
		System.out.println(objectId);
		final ObjectID<NPC> id = idResolver.resolve(objectId);
		if (!(id instanceof NPCID)) {
			System.out.println("Incorrect type: " + id);
			conn.sendActionFailed();
			return;
		}
		final NPC npc = id.getObject();
		npc.action(conn.getCharacter(), action);
	}
}
