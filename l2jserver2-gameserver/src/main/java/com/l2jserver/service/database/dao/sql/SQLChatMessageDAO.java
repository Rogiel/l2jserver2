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
package com.l2jserver.service.database.dao.sql;

import java.util.Collection;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.ChatMessageDAO;
import com.l2jserver.model.id.ChatMessageID;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.mapper.ChatMessageMapper;
import com.l2jserver.service.database.model.QLogChat;
import com.l2jserver.service.database.sql.AbstractSQLDAO;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.DeleteQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.InsertQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectSingleQuery;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;

/**
 * {@link CharacterDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SQLChatMessageDAO extends
		AbstractSQLDAO<ChatMessage, ChatMessageID> implements ChatMessageDAO {
	private final ChatMessageMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the mapper
	 */
	@Inject
	public SQLChatMessageDAO(DatabaseService database, ChatMessageMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public ChatMessage select(final ChatMessageID id) {
		return database
				.query(new SelectSingleQuery<ChatMessage, Integer, ChatMessageID, QLogChat>(
						QLogChat.logChat, mapper) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QLogChat e) {
						q.where(e.messageId.eq(id.getID()));
					}
				});
	}

	@Override
	public Collection<ChatMessageID> selectIDs() {
		return database
				.query(new SelectListQuery<ChatMessageID, Integer, ChatMessageID, QLogChat>(
						QLogChat.logChat, mapper.getIDMapper(QLogChat.logChat)) {
					@Override
					protected void query(AbstractSQLQuery<?> q, QLogChat e) {
					}
				});
	}

	@Override
	public int insertObjects(ChatMessage... objects) {
		return database
				.query(new InsertQuery<ChatMessage, Integer, ChatMessageID, QLogChat>(
						QLogChat.logChat, mapper, QLogChat.logChat.messageId,
						objects));
	}

	@Override
	public int updateObjects(ChatMessage... objects) {
		return 0;
	}

	@Override
	public int deleteObjects(ChatMessage... objects) {
		return database.query(new DeleteQuery<ChatMessage, QLogChat>(
				QLogChat.logChat, objects) {
			@Override
			protected void query(SQLDeleteClause q, ChatMessage o) {
				q.where(e.messageId.eq(o.getID().getID()));
			}
		});
	}

	@Override
	protected ChatMessage[] wrap(Model<?>... objects) {
		final ChatMessage[] array = new ChatMessage[objects.length];
		int i = 0;
		for (final Model<?> object : objects) {
			array[i++] = (ChatMessage) object;
		}
		return array;
	}
}
