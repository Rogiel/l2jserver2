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
import com.l2jserver.service.game.character.CannotSetTargetServiceException;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.SystemMessage;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;

/**
 * This packet cancels the target selection
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_TARGET_UNSELECT extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x48;

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService charService;

	/**
	 * Now known for sure
	 */
	@SuppressWarnings("unused")
	private boolean unselect;

	/**
	 * @param charService
	 *            the character service
	 */
	@Inject
	public CM_CHAR_TARGET_UNSELECT(CharacterService charService) {
		this.charService = charService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		this.unselect = (buffer.readByte() == 1 ? true : false);
	}

	@Override
	public void process(final Lineage2Client conn) {
		try {
			charService.target(conn.getCharacter(), null);
		} catch (CannotSetTargetServiceException e) {
			conn.sendSystemMessage(SystemMessage.FAILED_DISABLE_TARGET);
		}
	}
}
