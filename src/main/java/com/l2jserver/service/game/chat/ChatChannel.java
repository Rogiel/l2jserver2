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
package com.l2jserver.service.game.chat;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.server.ChatMessage;

/**
 * The {@link ChatChannel} object is used to send messages to a channel.
 * <p>
 * Note that this is a base for all types of channel.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see PrivateChatChannel
 * @see PublicChatChannel
 */
public interface ChatChannel {
	/**
	 * Sends a message to this channel.
	 * <p>
	 * Unless otherwise stated, all messages sent will be automatically logged
	 * using {@link ChatLoggingService}.
	 * 
	 * @param sender
	 *            the character sending the message
	 * @param message
	 *            the message to be sent
	 * @throws ChatBanActiveChatServiceException
	 *             if <tt>sender</tt> is banned from chatting
	 * @throws ChatTargetOfflineServiceException
	 *             if the target is offline. Will be be thrown in
	 *             {@link PrivateChatChannel}.
	 * @return the created {@link ChatMessage}. The object will be created by
	 *         {@link ChatLoggingService}.
	 */
	ChatMessage send(CharacterID sender, String message)
			throws ChatBanActiveChatServiceException,
			ChatTargetOfflineServiceException;

	/**
	 * Adds a {@link ChatChannelListener} that will be notified once a message
	 * has been received.
	 * 
	 * @param listener
	 *            the listener
	 */
	void addChatChannelListener(ChatChannelListener listener);

	/**
	 * Removes a {@link ChatChannelListener}.
	 * 
	 * @param listener
	 *            the listener
	 */
	void removeChatChannelListener(ChatChannelListener listener);

	/**
	 * Adds a {@link ChatChannelFilter} that will be used to filter each message
	 * sent in the channel.
	 * 
	 * @param filter
	 *            the filter
	 */
	void addChatChannelFilter(ChatChannelFilter filter);

	/**
	 * Removes a {@link ChatChannelListener}.
	 * 
	 * @param filter
	 *            the filter
	 */
	void removeChatChannelFilter(ChatChannelFilter filter);

	/**
	 * @return the chat channel numeric ID
	 */
	int getChannelID();

	/**
	 * @return the chat message type accepted by this channel
	 */
	ChatMessageType getMessageType();

	/**
	 * @return the chat channel name
	 */
	String getChannelName();
}
