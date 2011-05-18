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
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.chat.channel.PublicChatChannel;
import com.l2jserver.util.BufferUtils;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterChatMessagePacket extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x49;

	// services and daos
	/**
	 * The {@link ChatService} implementation
	 */
	private final ChatService chatService;

	private String message;
	private MessageDestination destination;

	public enum MessageDestination {
		/**
		 * Everyone
		 */
		ALL(0),
		/**
		 * !
		 */
		SHOUT(1),
		/**
		 * Private message
		 */
		TELL(2),
		/**
		 * #
		 */
		PARTY(3),
		/**
		 * @
		 */
		CLAN(4), GM(5), PETITION_PLAYER(6), PETITION_GM(7),
		/**
		 * +
		 */
		TRADE(8),
		/**
		 * $
		 */
		ALLIANCE(9), ANNOUNCEMENT(10), BOAT(11), L2FRIEND(12), MSNCHAT(13), PARTYMATCH_ROOM(
				14), PARTYROOM_COMMANDER(15), PARTYROOM_ALL(16), HERO_VOICE(17), CRITICAL_ANNOUNCE(
				18), SCREEN_ANNOUNCE(19), BATTLEFIELD(20), MPCC_ROOM(21);

		public final int id;

		MessageDestination(int id) {
			this.id = id;
		}

		public static MessageDestination fromID(int id) {
			for (final MessageDestination dest : values()) {
				if (dest.id == id)
					return dest;
			}
			return null;
		}
	}

	private String target;

	@Inject
	public CharacterChatMessagePacket(ChatService chatService) {
		this.chatService = chatService;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		this.message = BufferUtils.readString(buffer);
		this.destination = MessageDestination.fromID(buffer.readInt());
		if (this.destination == MessageDestination.TELL) { // private message
			this.target = BufferUtils.readString(buffer);
		}
	}

	@Override
	public void process(final Lineage2Connection conn) {
		final PublicChatChannel channel = chatService.getGlobalChannel();
		// TODO handle chat destination!!!
		channel.send(conn.getCharacterID(), message);
	}
}
