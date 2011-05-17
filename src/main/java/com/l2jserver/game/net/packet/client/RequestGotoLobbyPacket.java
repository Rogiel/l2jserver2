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

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.game.net.packet.server.CharacterSelectionListPacket;
import com.l2jserver.model.world.L2Character;

/**
 * Requests the list of characters to be displayed in the lobby. The list of
 * characters is sent to the client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RequestGotoLobbyPacket extends AbstractClientPacket {
	/**
	 * The packet OPCODE1
	 */
	public static final int OPCODE1 = 0xd0;
	/**
	 * The packet OPCODE2
	 */
	public static final int OPCODE2 = 0x36;

	/**
	 * The {@link CharacterDAO} implementation
	 */
	private final CharacterDAO characterDao;

	@Inject
	public RequestGotoLobbyPacket(CharacterDAO characterDao) {
		this.characterDao = characterDao;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
	}

	@Override
	public void process(final Lineage2Connection conn) {
		final List<L2Character> chars = characterDao.selectByAccount(conn
				.getSession().getAccountID());
		conn.write(CharacterSelectionListPacket.fromL2Session(
				conn.getSession(), chars.toArray(new L2Character[0])));
	}
}
