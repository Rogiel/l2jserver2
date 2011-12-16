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
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.world.Item;
import com.l2jserver.service.game.item.ItemAlreadyOnGroundServiceException;
import com.l2jserver.service.game.item.ItemService;
import com.l2jserver.service.game.item.NotEnoughItemsServiceException;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.util.geometry.Point3D;

/**
 * This packet drops items on the ground.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_ITEM_DROP extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x17;

	/**
	 * The {@link ItemService}
	 */
	private final ItemService itemService;
	private final ItemIDProvider itemIdProvider;

	/**
	 * The item ID
	 */
	private int objectId;
	/**
	 * The number of items to be dropped
	 */
	private long count;
	/**
	 * The location to be dropped
	 */
	private Point3D point;

	/**
	 * @param itemService
	 *            the item service
	 * @param itemIdProvider
	 *            the item id provider
	 */
	@Inject
	public CM_ITEM_DROP(ItemService itemService, ItemIDProvider itemIdProvider) {
		this.itemService = itemService;
		this.itemIdProvider = itemIdProvider;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		objectId = buffer.readInt();
		count = buffer.readLong();
		point = Point3D.fromXYZ(buffer.readInt(), buffer.readInt(),
				buffer.readInt());
	}

	@Override
	public void process(final Lineage2Client conn) {
		final ItemID id = itemIdProvider.resolveID(objectId);
		final Item item = id.getObject();

		if (item == null) {
			conn.sendActionFailed();
			return;
		}
		if (!conn.getCharacterID().equals(item.getOwnerID())) {
			conn.sendActionFailed();
			return;
		}

		try {
			final Item dropped = itemService.drop(item, count, point,
					conn.getCharacter());
			if (dropped.equals(item)) {
				conn.removeInventoryItems(dropped);
			} else {
				conn.updateInventoryItems(item);
			}
			if (dropped.getCount() == 1) {
				conn.sendSystemMessage(SystemMessage.YOU_DROPPED_S1, item
						.getTemplate().getName());
			} else {
				conn.sendSystemMessage(SystemMessage.DROPPED_S1_S2, item
						.getTemplate().getName(), Long.toString(dropped
						.getCount()));
			}
		} catch (ItemAlreadyOnGroundServiceException
				| AlreadySpawnedServiceException
				| SpawnPointNotFoundServiceException
				| NotEnoughItemsServiceException e) {
			conn.sendActionFailed();
		}
	}
}
