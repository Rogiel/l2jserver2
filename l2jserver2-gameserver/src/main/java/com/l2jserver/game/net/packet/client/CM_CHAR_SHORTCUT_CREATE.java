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
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.model.game.CharacterShortcut.ShortcutActorType;
import com.l2jserver.model.game.CharacterShortcut.ShortcutType;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.game.character.ShortcutService;
import com.l2jserver.service.game.character.ShortcutSlotNotFreeServiceException;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_SHORTCUT_CREATE extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x3d;

	/**
	 * The {@link ShortcutService}
	 */
	private final ShortcutService shortcutService;
	/**
	 * The {@link ItemID} provider
	 */
	private final ItemIDProvider itemIdProvider;

	/**
	 * The shortcut type
	 */
	private ShortcutType type;
	/**
	 * The shortcut object ID (depends on type)
	 */
	private int objectId;
	/**
	 * The slot
	 */
	private int slot;
	/**
	 * The page
	 */
	private int page;
	/**
	 * The skill level
	 */
	@SuppressWarnings("unused")
	private int level;
	/**
	 * Whether the shortcut is an for an character(1) or a pet(2)
	 */
	@SuppressWarnings("unused")
	private ShortcutActorType actorType;

	/**
	 * @param shortcutService
	 *            the shortcut service
	 * @param itemIdProvider
	 *            the item id provider
	 */
	@Inject
	private CM_CHAR_SHORTCUT_CREATE(ShortcutService shortcutService,
			ItemIDProvider itemIdProvider) {
		this.shortcutService = shortcutService;
		this.itemIdProvider = itemIdProvider;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		type = ShortcutType.fromID(buffer.readInt());
		int slot = buffer.readInt();
		objectId = buffer.readInt();
		level = buffer.readInt();
		actorType = ShortcutActorType.fromID(buffer.readInt());

		this.slot = slot % 12;
		this.page = slot / 12;
	}

	@Override
	public void process(final Lineage2Client conn) {
		final L2Character character = conn.getCharacter();
		try {
			switch (type) {
			case ITEM:
				final ItemID itemID = itemIdProvider.resolveID(objectId);
				final Item item = itemID.getObject();
				if (item == null) {
					conn.sendActionFailed();
					return;
				}
				shortcutService.create(character, item, page, slot);
				break;
			}
		} catch (ShortcutSlotNotFreeServiceException e) {
			conn.sendActionFailed();
		}
	}
}
