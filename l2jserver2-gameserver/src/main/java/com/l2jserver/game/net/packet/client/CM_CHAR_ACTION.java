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
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.SystemMessage;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.object.provider.ObjectIDResolver;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.character.CannotSetTargetServiceException;
import com.l2jserver.service.game.item.ItemNotOnGroundServiceException;
import com.l2jserver.service.game.item.ItemService;
import com.l2jserver.service.game.npc.ActionServiceException;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
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

	/**
	 * The {@link ObjectID} resolver
	 */
	private final ObjectIDResolver idResolver;
	/**
	 * The {@link NPC} Service
	 */
	private final NPCService npcService;
	/**
	 * The {@link Item} Service
	 */
	private final ItemService itemService;

	/**
	 * The object id
	 */
	private int objectId;
	@SuppressWarnings("unused")
	private Coordinate origin;
	/**
	 * The character action type (aka mouse button)
	 */
	private CharacterAction action;

	public enum CharacterAction {
		/**
		 * If the player has clicked with the left mouse button.
		 */
		CLICK(0),
		/**
		 * If the player has clicked with the right mouse button.
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
	public CM_CHAR_ACTION(ObjectIDResolver idResolver, NPCService npcService,
			ItemService itemService) {
		this.idResolver = idResolver;
		this.npcService = npcService;
		this.itemService = itemService;
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
		final ObjectID<?> id = idResolver.resolve(objectId);
		if (id instanceof NPCID) {
			final NPC npc = ((NPCID) id).getObject();
			try {
				npcService.action(npc, conn.getCharacter(), action);
			} catch (ActionServiceException e) {
				conn.sendActionFailed();
			} catch (CannotSetTargetServiceException e) {
				conn.sendActionFailed();
			}
		} else if (id instanceof ItemID) {
			final Item item = ((ItemID) id).getObject();
			try {
				final Item stackItem = itemService.action(item,
						conn.getCharacter(), action);
				if (stackItem.equals(item)) { // adds
					conn.addInventoryItems(stackItem);
				} else { // update only
					conn.updateInventoryItems(stackItem);
				}
				conn.sendSystemMessage(SystemMessage.YOU_PICKED_UP_S1_S2, Long
						.toString(item.getCount()), item.getTemplate()
						.getName());
				conn.sendActionFailed();
			} catch (ItemNotOnGroundServiceException
					| NotSpawnedServiceException e) {
				conn.sendSystemMessage(SystemMessage.FAILED_TO_PICKUP_S1, item
						.getTemplate().getName());
				conn.sendActionFailed();
			}
		} else {
			conn.sendActionFailed();
			return;
		}
	}
}
