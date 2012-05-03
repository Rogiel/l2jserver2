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

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This packet send the inventory to the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_INVENTORY_UPDATE extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x27;

	/**
	 * List of items to be updated in the client
	 */
	private final Map<Item, InventoryUpdateType> items = CollectionFactory
			.newMap();

	/**
	 * The type of operation to be performed.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum InventoryUpdateType {
		/**
		 * Flags the item to be added to the inventory list
		 */
		ADD(1),
		/**
		 * Flags the item to be updated in the inventory list
		 */
		UPDATE(2),
		/**
		 * Flags the item to be removed in the inventory list
		 */
		REMOVE(3);

		/**
		 * The operation ID
		 */
		public final int id;

		/**
		 * @param id
		 *            the operation ID
		 */
		private InventoryUpdateType(int id) {
			this.id = id;
		}
	}

	/**
	 * Creates an empty instance. Items can be added, removed or update using
	 * {@link #add(Item)}, {@link #remove(Item)} and {@link #update(Item)}.
	 */
	public SM_CHAR_INVENTORY_UPDATE() {
		super(OPCODE);
	}

	/**
	 * Creates a new instance with several items performing
	 * {@link InventoryUpdateType} <code>type</code> operation.
	 * 
	 * @param type
	 *            the operation type
	 * @param items
	 *            the items
	 */
	public SM_CHAR_INVENTORY_UPDATE(InventoryUpdateType type, Item... items) {
		super(OPCODE);
		switch (type) {
		case ADD:
			add(items);
			break;
		case REMOVE:
			remove(items);
			break;
		case UPDATE:
			update(items);
			break;
		}
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeShort(items.size()); // item count

		for (Entry<Item, InventoryUpdateType> e : items.entrySet()) {
			final Item item = e.getKey();
			final InventoryUpdateType type = e.getValue();

			buffer.writeShort(type.id); // change type
			buffer.writeInt(item.getID().getID()); // obj id
			buffer.writeInt(item.getTemplateID().getID()); // item id
			buffer.writeInt(0x00); // loc slot
			buffer.writeLong(item.getCount()); // count
			buffer.writeShort(0x00); // item type2
			buffer.writeShort(0x00); // item type3
			buffer.writeShort((item.getLocation() == ItemLocation.PAPERDOLL ? 0x01
					: 0x00)); // equiped?
			buffer.writeInt((item.getPaperdoll() != null ? item.getPaperdoll().id
					: 0)); // body part
			buffer.writeShort(127); // enchant level
			// race tickets
			buffer.writeShort(0x00); // item type4 (custom type 2)
			buffer.writeInt(0x00); // augument
			buffer.writeInt(0x00); // mana
//			buffer.writeInt(-9999); // time
//			buffer.writeShort(0x00); // attack element type
//			buffer.writeShort(0x00); // attack element power
//			for (byte i = 0; i < 6; i++) {
//				buffer.writeShort(0x00); // element def attrib
//			}
//			// Enchant Effects
//			buffer.writeShort(0x00);
//			buffer.writeShort(0x00);
//			buffer.writeShort(0x00);
		}
	}

	/**
	 * Removes a single item
	 * 
	 * @param item
	 *            the item to be removed
	 * @return this instance
	 */
	public SM_CHAR_INVENTORY_UPDATE remove(Item item) {
		items.put(item, InventoryUpdateType.REMOVE);
		return this;
	}

	/**
	 * Removes several items
	 * 
	 * @param items
	 *            the items to be removed
	 * @return this instance
	 */
	public SM_CHAR_INVENTORY_UPDATE remove(Item... items) {
		for (final Item item : items) {
			remove(item);
		}
		return this;
	}

	/**
	 * Adds a single item
	 * 
	 * @param item
	 *            the item to be added
	 * @return this instance
	 */
	public SM_CHAR_INVENTORY_UPDATE add(Item item) {
		items.put(item, InventoryUpdateType.ADD);
		return this;
	}

	/**
	 * Adds several items
	 * 
	 * @param items
	 *            the item to be added
	 * @return this instance
	 */
	public SM_CHAR_INVENTORY_UPDATE add(Item... items) {
		for (final Item item : items) {
			add(item);
		}
		return this;
	}

	/**
	 * Updates a single item
	 * 
	 * @param item
	 *            the item to be updated
	 * @return this instance
	 */
	public SM_CHAR_INVENTORY_UPDATE update(Item item) {
		items.put(item, InventoryUpdateType.UPDATE);
		return this;
	}

	/**
	 * Updates several items
	 * 
	 * @param items
	 *            the item to be updated
	 * @return this instance
	 */
	public SM_CHAR_INVENTORY_UPDATE update(Item... items) {
		for (final Item item : items) {
			update(item);
		}
		return this;
	}
}
