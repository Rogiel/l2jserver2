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
package com.l2jserver.service.database.orientdb;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.ChatMessageDAO;
import com.l2jserver.model.id.ChatMessageID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.provider.ChatMessageIDProvider;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.service.database.AbstractOrientDatabaseService.CachedMapper;
import com.l2jserver.service.database.AbstractOrientDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.AbstractOrientDatabaseService.Mapper;
import com.l2jserver.service.database.AbstractOrientDatabaseService.SelectListQuery;
import com.l2jserver.service.database.AbstractOrientDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.chat.ChatMessageType;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNativeSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * {@link CharacterDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class OrientDBChatMessageDAO extends
		AbstractOrientDBDAO<ChatMessage, ChatMessageID> implements
		ChatMessageDAO {
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
	public static final String CLASS_NAME = ChatMessage.class.getSimpleName();
	// FIELDS
	public static final String MESSAGE_ID = "message_id";
	public static final String TYPE = "type";
	public static final String CHANNEL_ID = "channel_id";
	public static final String SENDER = "sender";
	public static final String DATE = "date";
	public static final String MESSAGE = "message";

	/**
	 * @param database
	 *            the database service
	 * @param idFactory
	 *            the chat message id provider
	 * @param charIdFactory
	 *            the character id provider
	 */
	@Inject
	public OrientDBChatMessageDAO(DatabaseService database,
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
		public ChatMessageID map(ODocument document) throws SQLException {
			return idFactory.resolveID((Integer) document.field(MESSAGE_ID));
		}
	};

	/**
	 * The {@link Mapper} for {@link ChatMessage}
	 */
	private final Mapper<ChatMessage> mapper = new CachedMapper<ChatMessage, ChatMessageID>(
			database, idMapper) {
		@Override
		protected ChatMessage map(ChatMessageID id, ODocument document)
				throws SQLException {
			final ChatMessage message = new ChatMessage();
			message.setID(id);

			message.setType(ChatMessageType.valueOf((String) document
					.field(TYPE)));
			switch (message.getType()) {
			case SHOUT:
				message.setTarget(charIdFactory.resolveID((Integer) document
						.field(CHANNEL_ID)));
				break;
			default:
				message.setChannelID((Integer) document.field(CHANNEL_ID));
				break;
			}
			message.setSender(charIdFactory.resolveID((Integer) document
					.field(SENDER)));
			message.setDate((Date) document.field(DATE));
			message.setMessage((String) document.field(MESSAGE));

			return message;
		}
	};

	@Override
	public ChatMessage select(final ChatMessageID id) {
		return database.query(new SelectSingleQuery<ChatMessage>() {
			@Override
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database) {
				return new ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>>(
						database, CLASS_NAME,
						new OQueryContextNativeSchema<ODocument>()) {
					private static final long serialVersionUID = 1L;

					@Override
					public boolean filter(
							OQueryContextNativeSchema<ODocument> criteria) {
						return criteria.field(MESSAGE_ID).eq(id.getID()).go();
					};
				};
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
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database) {
				return new ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>>(
						database, CLASS_NAME,
						new OQueryContextNativeSchema<ODocument>()) {
					private static final long serialVersionUID = 1L;

					@Override
					public boolean filter(
							OQueryContextNativeSchema<ODocument> criteria) {
						return true;
					};
				};
			}

			@Override
			protected Mapper<ChatMessageID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public int insertObjects(ChatMessage... messages) {
		return database.query(new InsertUpdateQuery<ChatMessage>(messages) {
			@Override
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database, ChatMessage object) {
				return null;
			}

			@Override
			protected ODocument update(ODocument document, ChatMessage object)
					throws SQLException {
				return null;
			}

			@Override
			protected ODocument insert(ODocument document, ChatMessage message)
					throws SQLException {
				document.field(TYPE, message.getType().name());
				switch (message.getType()) {
				case SHOUT:
					document.field(CHANNEL_ID, message.getTarget().getID());
					break;
				default:
					document.field(CHANNEL_ID, message.getChannelID());
					break;
				}
				document.field(SENDER, message.getTarget().getID());
				document.field(DATE, message.getDate());
				document.field(MESSAGE, message.getMessage());

				return document;
			}
		});
	}

	@Override
	public int updateObjects(ChatMessage... messages) {
		// cannot update chat message logs
		return 0;
	}

	@Override
	public int deleteObjects(ChatMessage... messages) {
		return database.query(new InsertUpdateQuery<ChatMessage>(messages) {
			@Override
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database, final ChatMessage message) {
				return new ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>>(
						database, CLASS_NAME,
						new OQueryContextNativeSchema<ODocument>()) {
					private static final long serialVersionUID = 1L;

					@Override
					public boolean filter(
							OQueryContextNativeSchema<ODocument> criteria) {
						return criteria.field(MESSAGE_ID).eq(message.getID())
								.and().go();
					};
				};
			}

			@Override
			protected ODocument update(ODocument document, ChatMessage object)
					throws SQLException {
				document.delete();
				return null;
			}

			@Override
			protected ODocument insert(ODocument document, ChatMessage object)
					throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
