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

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.object.provider.ObjectIDResolver;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.character.CannotSetTargetServiceException;
import com.l2jserver.service.game.npc.ActionServiceException;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.util.geometry.Coordinate;

/**
 * Executes an action from an character to an NPC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_ACTION extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x1f;

	private final ObjectIDResolver idResolver;
	private final NPCService npcService;

	private int objectId;
	@SuppressWarnings("unused")
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
	public CM_CHAR_ACTION(ObjectIDResolver idResolver,
			NPCService npcService) {
		this.idResolver = idResolver;
		this.npcService = npcService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		this.objectId = buffer.readInt();
		this.origin = Coordinate.fromXYZ(buffer.readInt(), buffer.readInt(),
				buffer.readInt());
		this.action = CharacterAction.fromID(buffer.readByte());
	}

	@Override
	public void process(final Lineage2Client conn) {
		// since this is an erasure type, this is safe.
		final ObjectID<NPC> id = idResolver.resolve(objectId);
		if (!(id instanceof NPCID)) {
			conn.sendActionFailed();
			return;
		}
		final NPC npc = id.getObject();
		try {
			npcService.action(npc, conn.getCharacter(), action);
		} catch (ActionServiceException e) {
			conn.sendActionFailed();
		} catch (CannotSetTargetServiceException e) {
			conn.sendActionFailed();
		}
	}
}
