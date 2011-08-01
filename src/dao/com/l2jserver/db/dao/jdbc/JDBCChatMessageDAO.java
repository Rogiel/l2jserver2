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
package com.l2jserver.db.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.db.dao.ChatMessageDAO;
import com.l2jserver.model.id.ChatMessageID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.provider.ChatMessageIDProvider;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.JDBCDatabaseService.CachedMapper;
import com.l2jserver.service.database.JDBCDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.JDBCDatabaseService.Mapper;
import com.l2jserver.service.database.JDBCDatabaseService.SelectListQuery;
import com.l2jserver.service.database.JDBCDatabaseService.SelectSingleQuery;
import com.l2jserver.service.game.chat.ChatMessageType;

/**
 * {@link CharacterDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class JDBCChatMessageDAO extends
		AbstractJDBCDAO<ChatMessage, ChatMessageID> implements ChatMessageDAO {
	/**
	 * The {@link ChatMessageID} factory
	 */
	private final ChatMessageIDProvider idFactory;
	/**
	 * The {@link CharacterID} factory
	 */
	private final CharacterIDProvider charIdFactory;

	/**
	 * Character table name
	 */
	public static final String TABLE = "log_chat";
	// FIELDS
	public static final String MESSAGE_ID = "message_id";
	public static final String TYPE = "type";
	public static final String CHANNEL_ID = "channel_id";
	public static final String SENDER = "sender";
	public static final String DATE = "date";
	public static final String MESSAGE = "message";

	@Inject
	public JDBCChatMessageDAO(DatabaseService database,
			ChatMessageIDProvider idFactory,
			final CharacterIDProvider charIdFactory) {
		super(database);
		this.idFactory = idFactory;
		this.charIdFactory = charIdFactory;
	}

	/**
	 * The {@link Mapper} for {@link ChatMessageID}
	 */
	private final Mapper<ChatMessageID> idMapper = new Mapper<ChatMessageID>() {
		@Override
		public ChatMessageID map(ResultSet rs) throws SQLException {
			return idFactory.resolveID(rs.getInt(MESSAGE_ID));
		}
	};
	
	/**
	 * The {@link Mapper} for {@link ChatMessageID} as a PRIMARY KEY
	 */
	private final Mapper<ChatMessageID> primaryKeyMapper = new Mapper<ChatMessageID>() {
		@Override
		public ChatMessageID map(ResultSet rs) throws SQLException {
			return idFactory.resolveID(rs.getInt(1));
		}
	};

	/**
	 * The {@link Mapper} for {@link ChatMessage}
	 */
	private final Mapper<ChatMessage> mapper = new CachedMapper<ChatMessage, ChatMessageID>(
			database, idMapper) {
		@Override
		protected ChatMessage map(ChatMessageID id, ResultSet rs)
				throws SQLException {
			final ChatMessage message = new ChatMessage();
			message.setID(id);

			message.setType(ChatMessageType.valueOf(rs.getString(TYPE)));
			switch (message.getType()) {
			case SHOUT:
				message.setTarget(charIdFactory.resolveID(rs.getInt(CHANNEL_ID)));
				break;
			default:
				message.setChannelID(rs.getInt(CHANNEL_ID));
				break;
			}
			message.setSender(charIdFactory.resolveID(rs.getInt(SENDER)));
			message.setDate(new Date(rs.getTimestamp(DATE).getTime()));
			message.setMessage(rs.getString(MESSAGE));

			return message;
		}
	};

	@Override
	public ChatMessage select(final ChatMessageID id) {
		return database.query(new SelectSingleQuery<ChatMessage>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + MESSAGE_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, id.getID());
			}

			@Override
			protected Mapper<ChatMessage> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<ChatMessageID> selectIDs() {
		return database.query(new SelectListQuery<ChatMessageID>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "`";
			}

			@Override
			protected Mapper<ChatMessageID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public boolean insert(ChatMessage message) {
		return database.query(new InsertUpdateQuery<ChatMessage>(message) {
			@Override
			protected String query() {
				return "INSERT INTO `" + TABLE + "` (`" + TYPE + "`,`"
						+ CHANNEL_ID + "`,`" + SENDER + "`,`" + DATE + "`,`"
						+ MESSAGE + "`) VALUES(?,?,?,?,?)";
			}

			@Override
			protected void parametize(PreparedStatement st, ChatMessage message)
					throws SQLException {
				int i = 1;
				st.setString(i++, message.getType().name());
				switch (message.getType()) {
				case SHOUT:
					st.setInt(i++, message.getTarget().getID());
					break;
				default:
					st.setInt(i++, message.getChannelID());
					break;
				}
				st.setInt(i++, message.getSender().getID());
				st.setTimestamp(i++, new Timestamp(message.getDate().getTime()));
				st.setString(i++, message.getMessage());
			}

			@Override
			protected Mapper<ChatMessageID> keyMapper() {
				return primaryKeyMapper;
			}
		}) > 0;
	}

	@Override
	public boolean update(ChatMessage message) {
		// cannot update chat message logs
		return false;
	}

	@Override
	public boolean delete(ChatMessage message) {
		return database.query(new InsertUpdateQuery<ChatMessage>(message) {
			@Override
			protected String query() {
				return "DELETE FROM `" + TABLE + "` WHERE `" + MESSAGE_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st, ChatMessage message)
					throws SQLException {
				st.setInt(1, message.getID().getID());
			}
		}) > 0;
	}
}
