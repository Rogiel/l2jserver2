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
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;

/**
 * This service chatting in the server
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ChatService extends Service {
	/**
	 * Sends a message to a public chat channel.
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
	 */
	void send(CharacterID sender, ChatMessageDestination chat, String message,
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
