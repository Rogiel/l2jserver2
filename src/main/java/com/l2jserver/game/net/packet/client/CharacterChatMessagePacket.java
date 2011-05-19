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
import com.l2jserver.game.net.packet.server.ActionFailedPacket;
import com.l2jserver.service.game.chat.ChatMessageDestination;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.chat.ChatService.CannotChatToSelfChatServiceException;
import com.l2jserver.service.game.chat.ChatService.ChatBanActiveChatServiceException;
import com.l2jserver.service.game.chat.ChatService.TargetNotFoundChatServiceException;
import com.l2jserver.util.BufferUtils;
import com.l2jserver.util.exception.L2ChatServiceException;

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
	private ChatMessageDestination destination;

	private String target;

	@Inject
	public CharacterChatMessagePacket(ChatService chatService) {
		this.chatService = chatService;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		this.message = BufferUtils.readString(buffer);
		this.destination = ChatMessageDestination.fromID(buffer.readInt());
		if (this.destination == ChatMessageDestination.TELL) { // private
																// message
			this.target = BufferUtils.readString(buffer);
		}
	}

	@Override
	public void process(final Lineage2Connection conn) {
		if (message.length() == 0 || destination == null) {
			conn.write(ActionFailedPacket.SHARED_INSTANCE);
			conn.close();
		}

		try {
			chatService.send(conn.getCharacterID(), destination, message, target);
		} catch (TargetNotFoundChatServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotChatToSelfChatServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChatBanActiveChatServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
