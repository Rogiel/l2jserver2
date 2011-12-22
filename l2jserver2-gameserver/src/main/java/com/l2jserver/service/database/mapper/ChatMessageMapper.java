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
package com.l2jserver.service.database.mapper;

import com.google.inject.Inject;
import com.l2jserver.model.id.ChatMessageID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.provider.ChatMessageIDProvider;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.Mapper;
import com.l2jserver.service.database.model.QLogChat;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class ChatMessageMapper implements Mapper<ChatMessage, QLogChat> {
	private final Mapper<ChatMessageID, QLogChat> idMapper = new Mapper<ChatMessageID, QLogChat>() {
		@Override
		public ChatMessageID map(QLogChat e, DatabaseRow row) {
			return idProvider.resolveID(row.get(e.messageId));
		}
	};

	/**
	 * The {@link ChatMessageID} provider
	 */
	private final ChatMessageIDProvider idProvider;

	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider charIdProvider;

	/**
	 * @param idProvider
	 *            the {@link CharacterID} provider
	 * @param charIdProvider
	 *            the character ID provider
	 * 
	 */
	@Inject
	public ChatMessageMapper(final ChatMessageIDProvider idProvider,
			CharacterIDProvider charIdProvider) {
		this.idProvider = idProvider;
		this.charIdProvider = charIdProvider;
	}

	@Override
	public ChatMessage map(QLogChat e, DatabaseRow row) {
		final ChatMessage message = new ChatMessage();
		message.setID(idProvider.resolveID(row.get(e.messageId)));

		message.setType(row.get(e.type));
		switch (message.getType()) {
		case SHOUT:
			message.setTarget(charIdProvider.resolveID(row.get(e.channelId)));
			break;
		default:
			message.setChannelID(row.get(e.channelId));
			break;
		}
		message.setSender(charIdProvider.resolveID(row.get(e.sender)));
		message.setDate(row.get(e.date));
		message.setMessage(row.get(e.message));

		return message;
	}

	public Mapper<ChatMessageID, QLogChat> getIDMapper() {
		return idMapper;
	}
}