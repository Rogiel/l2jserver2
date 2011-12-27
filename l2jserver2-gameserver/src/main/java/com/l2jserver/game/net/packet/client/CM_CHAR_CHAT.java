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
import com.l2jserver.game.net.packet.server.SM_ACTION_FAILED;
import com.l2jserver.service.game.chat.CannotChatToSelfChatServiceException;
import com.l2jserver.service.game.chat.ChatBanActiveChatServiceException;
import com.l2jserver.service.game.chat.ChatMessageType;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.chat.ChatTargetOfflineServiceException;
import com.l2jserver.service.game.chat.TargetNotFoundChatServiceException;
import com.l2jserver.util.BufferUtils;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_CHAT extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x49;

	// services and daos
	/**
	 * The {@link ChatService} implementation
	 */
	private final ChatService chatService;

	/**
	 * The message
	 */
	private String message;
	/**
	 * The message destination
	 */
	private ChatMessageType destination;
	/**
	 * The message target
	 */
	private String target;

	/**
	 * @param chatService the chat service
	 */
	@Inject
	public CM_CHAR_CHAT(ChatService chatService) {
		this.chatService = chatService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		this.message = BufferUtils.readString(buffer);
		this.destination = ChatMessageType.fromID(buffer.readInt());
		if (this.destination == ChatMessageType.TELL) { // private
														// message
			this.target = BufferUtils.readString(buffer);
		}
	}

	@Override
	public void process(final Lineage2Client conn) {
		if (message.length() == 0 || destination == null) {
			conn.write(SM_ACTION_FAILED.SHARED_INSTANCE);
			conn.close();
		}

		try {
			chatService.send(conn.getCharacterID(), destination, message,
					target);
		} catch (TargetNotFoundChatServiceException e) {
			conn.sendSystemMessage(SystemMessage.TARGET_CANT_FOUND);
		} catch (CannotChatToSelfChatServiceException e) {
			conn.sendSystemMessage(SystemMessage.CANNOT_USE_ON_YOURSELF);
		} catch (ChatBanActiveChatServiceException e) {
			conn.sendSystemMessage(SystemMessage.CHATTING_IS_CURRENTLY_PROHIBITED);
		} catch (ChatTargetOfflineServiceException e) {
			conn.sendSystemMessage(SystemMessage.S1_IS_NOT_ONLINE, target);
		}

	}
}
