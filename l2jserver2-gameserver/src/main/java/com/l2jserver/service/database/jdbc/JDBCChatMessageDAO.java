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
package com.l2jserver.service.database.jdbc;

import java.util.Collection;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.ChatMessageDAO;
import com.l2jserver.model.id.ChatMessageID;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.DeleteQuery;
import com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.InsertQuery;
import com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.SelectListQuery;
import com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.UpdateQuery;
import com.l2jserver.service.database.mapper.ChatMessageMapper;
import com.l2jserver.service.database.model.QLogChat;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

/**
 * {@link CharacterDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class JDBCChatMessageDAO extends
		AbstractJDBCDAO<ChatMessage, ChatMessageID> implements ChatMessageDAO {
	private final ChatMessageMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the mapper
	 */
	@Inject
	public JDBCChatMessageDAO(DatabaseService database, ChatMessageMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public ChatMessage select(final ChatMessageID id) {
		return database.query(new SelectSingleQuery<ChatMessage, QLogChat>(
				QLogChat.logChat, mapper) {
			@Override
			protected void query(AbstractSQLQuery<?> q, QLogChat e) {
				q.where(e.messageId.eq(id.getID()));
			}
		});
	}

	@Override
	public Collection<ChatMessageID> selectIDs() {
		return database.query(new SelectListQuery<ChatMessageID, QLogChat>(
				QLogChat.logChat, mapper.getIDMapper()) {
			@Override
			protected void query(AbstractSQLQuery<?> q, QLogChat e) {
			}
		});
	}

	@Override
	public int insertObjects(ChatMessage... objects) {
		return database.query(new InsertQuery<ChatMessage, QLogChat, Integer>(
				QLogChat.logChat, QLogChat.logChat.messageId, objects) {
			@Override
			protected void map(SQLInsertClause q, ChatMessage o) {
				q.set(e.type, o.getType()).set(e.sender, o.getSender().getID())
						.set(e.date, o.getDate())
						.set(e.message, o.getMessage());
				switch (o.getType()) {
				case SHOUT:
					q.set(e.channelId, o.getTarget().getID());
					break;
				default:
					q.set(e.channelId, o.getChannelID());
					break;
				}
			}
		});
	}

	@Override
	public int updateObjects(ChatMessage... objects) {
		return database.query(new UpdateQuery<ChatMessage, QLogChat>(
				QLogChat.logChat, objects) {
			@Override
			protected void query(SQLUpdateClause q, ChatMessage o) {
				q.where(e.messageId.eq(o.getID().getID()));
			}

			@Override
			protected void map(SQLUpdateClause q, ChatMessage o) {
				q.set(e.type, o.getType()).set(e.sender, o.getSender().getID())
						.set(e.date, o.getDate())
						.set(e.message, o.getMessage());
				switch (o.getType()) {
				case SHOUT:
					q.set(e.channelId, o.getTarget().getID());
					break;
				default:
					q.set(e.channelId, o.getChannelID());
					break;
				}
			}
		});
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
