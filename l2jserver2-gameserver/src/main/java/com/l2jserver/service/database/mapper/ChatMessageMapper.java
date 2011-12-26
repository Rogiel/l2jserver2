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
import com.l2jserver.service.database.dao.AbstractMapper;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.PrimaryKeyMapper;
import com.l2jserver.service.database.dao.WritableDatabaseRow;
import com.l2jserver.service.database.model.QLogChat;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class ChatMessageMapper extends
		AbstractMapper<ChatMessage, Integer, ChatMessageID, QLogChat> {
	private final PrimaryKeyMapper<ChatMessageID, Integer> idMapper = new PrimaryKeyMapper<ChatMessageID, Integer>() {
		@Override
		public ChatMessageID createID(Integer raw) {
			return idProvider.resolveID(raw);
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
	public ChatMessage select(QLogChat e, DatabaseRow row) {
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

	@Override
	public void insert(QLogChat e, ChatMessage object, WritableDatabaseRow row) {
		row.set(e.type, object.getType())
				.set(e.sender, object.getSender().getID())
				.set(e.date, object.getDate())
				.set(e.message, object.getMessage());
		switch (object.getType()) {
		case SHOUT:
			row.set(e.channelId, object.getTarget().getID());
			break;
		default:
			row.set(e.channelId, object.getChannelID());
			break;
		}
	}

	@Override
	public void update(QLogChat e, ChatMessage object, WritableDatabaseRow row) {
	}

	@Override
	public PrimaryKeyMapper<ChatMessageID, Integer> getPrimaryKeyMapper() {
		return idMapper;
	}
}