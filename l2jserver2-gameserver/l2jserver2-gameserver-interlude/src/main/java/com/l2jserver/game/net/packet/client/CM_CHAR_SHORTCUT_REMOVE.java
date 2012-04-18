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
import com.l2jserver.service.game.character.ShortcutService;
import com.l2jserver.service.game.character.ShortcutSlotEmptyServiceException;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_SHORTCUT_REMOVE extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x35;

	/**
	 * The {@link ShortcutService}
	 */
	private final ShortcutService shortcutService;

	/**
	 * The slot
	 */
	private int slot;
	/**
	 * The page
	 */
	private int page;

	/**
	 * @param shortcutService
	 *            the shortcut service
	 */
	@Inject
	private CM_CHAR_SHORTCUT_REMOVE(ShortcutService shortcutService) {
		this.shortcutService = shortcutService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		int slot = buffer.readInt();
		this.slot = slot % 12;
		this.page = slot / 12;
	}

	@Override
	public void process(final Lineage2Client conn) {
		try {
			shortcutService.remove(conn.getCharacter(), page, slot);
		} catch (ShortcutSlotEmptyServiceException e) {
			conn.sendActionFailed();
		}
	}
}
