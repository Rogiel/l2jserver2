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

import java.util.Date;

import com.google.inject.Inject;
import com.l2jserver.db.dao.ChatMessageDAO;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.service.AbstractService;

/**
 * {@link ChatLoggingService} implementation that stores logs in the database
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DatabaseChatLoggingService extends AbstractService implements
		ChatLoggingService {
	/**
	 * The {@link ChatMessage} DAO
	 */
	private final ChatMessageDAO chatMessageDao;
	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider charIdProvider;

	@Inject
	protected DatabaseChatLoggingService(ChatMessageDAO chatMessageDao,
			CharacterIDProvider charIdProvider) {
		this.chatMessageDao = chatMessageDao;
		this.charIdProvider = charIdProvider;
	}

	@Override
	public ChatMessage log(CharacterID sender, ChatChannel channel,
			String messageText) {
		final ChatMessage message = new ChatMessage();

		// message type and destination
		message.setType(channel.getMessageType());
		switch (channel.getMessageType()) {
		case SHOUT: // if type is SHOUT the ChannelID is the CharacterID
					// (target)
			message.setTarget(charIdProvider.createID(channel.getChannelID()));
			break;
		default:
			message.setChannelID(channel.getChannelID());
			break;
		}

		// message information
		message.setSender(sender);
		message.setDate(new Date());
		message.setMessage(messageText);

		// save in database
		chatMessageDao.save(message, true);

		return message;
	}
}
