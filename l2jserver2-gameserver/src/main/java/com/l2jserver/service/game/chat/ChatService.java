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
package com.l2jserver.service.game.chat;

import com.l2jserver.game.net.packet.client.CM_CHAT;
import com.l2jserver.game.net.packet.server.SM_CHAT;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;

/**
 * This service provides chatting in the server. There can be several
 * implementations for this service, however only one of them can be active at a
 * time. For example, implementations can use an backing IRC-Server that will
 * provide flood control, channel control, reliability and external
 * accessibility, people can talk to others even if not logged into the game.
 * <p>
 * Chat are divided into {@link ChatChannel channels}, each of those are
 * respective to an determined section of the game chatting capabilities, i.e.
 * trade chat (+) will be provided by the TradeChat channel. This is the concept
 * of {@link PublicChatChannel} because in this type of channels, when one
 * player writes an message it can possibly, however not forced to, be
 * broadcasted to several other players, including itself in certain occasions
 * (i.e. announcement channel).
 * <p>
 * There is also {@link PrivateChatChannel} that provide messaging capabilities
 * among two players only. One will be the sender and one will be the receiver.
 * In most situations, messages sent will not be sent back to the sender. In
 * this type of channel, the message is guarantee to be received by the other
 * player, if he is not available {@link ChatTargetOfflineServiceException} will
 * be thrown.
 * <p>
 * All messages sent in any channel must be logged by {@link ChatLoggingService}
 * unless it is refused by an {@link ChatChannelFilter}.
 * 
 * <h1>Chat ban</h1>
 * If the sender is chat banned and tries to send a message
 * {@link ChatBanActiveChatServiceException} will be thrown.
 * 
 * <h1>Packets</h1>
 * Messages are received (from the clients) with {@link CM_CHAT} and sent (to
 * the clients) with {@link SM_CHAT}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ChatService extends Service {
	/**
	 * Sends a message to a public chat channel.
	 * <p>
	 * Messages sent will be automatically logged using
	 * {@link ChatLoggingService}.
	 * 
	 * @param sender
	 *            the sender
	 * @param chat
	 *            the chat type
	 * @param message
	 *            the message
	 * @param extra
	 *            the the extra message field
	 * @throws TargetNotFoundChatServiceException
	 *             if target object not found
	 * @throws CannotChatToSelfChatServiceException
	 *             if trying to send a private message to self
	 * @throws ChatBanActiveChatServiceException
	 *             if there is chat ban active
	 * @throws ChatTargetOfflineServiceException
	 *             if the chat target is offline
	 * @return the created {@link ChatMessage}. The object will be created by
	 *         {@link ChatLoggingService}.
	 */
	ChatMessage send(CharacterID sender, ChatMessageType chat, String message,
			String extra) throws TargetNotFoundChatServiceException,
			CannotChatToSelfChatServiceException,
			ChatBanActiveChatServiceException,
			ChatTargetOfflineServiceException;

	/**
	 * Get the Global {@link ChatChannel}. Messages sent in this chat are
	 * broadcasted to everyone online.
	 * 
	 * @return the global {@link ChatChannel}
	 */
	PublicChatChannel getGlobalChannel();

	/**
	 * Get the Trade {@link ChatChannel}. Messages sent in this chat are
	 * broadcasted to everyone online.
	 * 
	 * @return the trade {@link ChatChannel}
	 */
	PublicChatChannel getTradeChannel();

	/**
	 * Get the Announcement {@link ChatChannel}. Messages sent in this chat are
	 * broadcasted to everyone online.
	 * 
	 * @return the announcement {@link ChatChannel}
	 */
	PublicChatChannel getAnnouncementChannel();

	/**
	 * Get the Region {@link ChatChannel}. Messages sent in this chat are
	 * broadcasted to everyone nearby.
	 * 
	 * @param character
	 *            the character in the region
	 * @return the global {@link ChatChannel}
	 */
	PublicChatChannel getRegionChannel(L2Character character);

	/**
	 * Get an private {@link ChatChannel} to {@link CharacterID}. Messages sent
	 * in this channel are sent only to <tt>character</tt>.
	 * 
	 * @param character
	 *            the target character
	 * @return the private {@link ChatChannel}
	 */
	PrivateChatChannel getChannel(CharacterID character);

	/**
	 * Get the Clan {@link ChatChannel}. Messages sent in this channel are
	 * broadcast to all clan members online.
	 * 
	 * @param clan
	 *            the clan
	 * @return the public clan {@link ChatChannel}
	 */
	PublicChatChannel getChannel(ClanID clan);
}
