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
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.model.dao.CharacterFriendDAO;
import com.l2jserver.model.game.CharacterFriend;
import com.l2jserver.model.id.FriendID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.provider.FriendIDProvider;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterFriendList;
import com.l2jserver.service.database.AbstractOrientDatabaseService.CachedMapper;
import com.l2jserver.service.database.AbstractOrientDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.AbstractOrientDatabaseService.Mapper;
import com.l2jserver.service.database.AbstractOrientDatabaseService.SelectListQuery;
import com.l2jserver.service.database.AbstractOrientDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.jdbc.JDBCCharacterDAO;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNativeSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * {@link CharacterFriendDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class OrientDBCharacterFriendDAO extends
		AbstractOrientDBDAO<CharacterFriend, FriendID> implements
		CharacterFriendDAO {
	/**
	 * The {@link FriendID} provider
	 */
	private final FriendIDProvider idProvider;
	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider charIdProvider;

	/**
	 * Character table name
	 */
	public static final String CLASS_NAME = CharacterFriend.class
			.getSimpleName();
	// FIELDS
	public static final String CHAR_ID = JDBCCharacterDAO.CHAR_ID;
	public static final String CHAR_ID_FRIEND = JDBCCharacterDAO.CHAR_ID
			+ "_friend";

	/**
	 * @param database
	 *            the database service
	 * @param idProvider
	 *            the frind id provider
	 * @param charIdProvider
	 *            the character id provider
	 */
	@Inject
	public OrientDBCharacterFriendDAO(DatabaseService database,
			final FriendIDProvider idProvider,
			CharacterIDProvider charIdProvider) {
		super(database);
		this.idProvider = idProvider;
		this.charIdProvider = charIdProvider;
	}

	/**
	 * The {@link Mapper} for {@link FriendID}
	 */
	private final Mapper<FriendID> idMapper = new Mapper<FriendID>() {
		@Override
		public FriendID map(ODocument document) throws SQLException {
			final CharacterID characterId = charIdProvider
					.resolveID((Integer) document.field(CHAR_ID));
			final CharacterID friendId = charIdProvider
					.resolveID((Integer) document.field(CHAR_ID_FRIEND));
			return idProvider.createID(characterId, friendId);
		}
	};

	/**
	 * The {@link Mapper} for {@link CharacterFriend}
	 */
	private final Mapper<CharacterFriend> mapper = new CachedMapper<CharacterFriend, FriendID>(
			database, idMapper) {
		@Override
		protected CharacterFriend map(FriendID id, ODocument document)
				throws SQLException {
			return new CharacterFriend(id);
		}
	};

	@Override
	public CharacterFriend select(final FriendID id) {
		return database.query(new SelectSingleQuery<CharacterFriend>() {
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
						return criteria.field(CHAR_ID).eq(id.getID1().getID())
								.field(CHAR_ID_FRIEND).eq(id.getID2().getID())
								.go();
					};
				};
			}

			@Override
			protected Mapper<CharacterFriend> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public void load(final L2Character character) {
		final List<CharacterFriend> list = database
				.query(new SelectListQuery<CharacterFriend>() {
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
								return criteria.field(CHAR_ID)
										.eq(character.getID().getID()).go();
							};
						};
					}

					@Override
					protected Mapper<CharacterFriend> mapper() {
						return mapper;
					}
				});
		character.getFriendList().load(list);
	}

	@Override
	public List<FriendID> selectIDs() {
		return database.query(new SelectListQuery<FriendID>() {
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
			protected Mapper<FriendID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public int insertObjects(CharacterFriend... friends) {
		return database.query(new InsertUpdateQuery<CharacterFriend>(friends) {
			@Override
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database, CharacterFriend object) {
				return null;
			}

			@Override
			protected ODocument update(ODocument document,
					CharacterFriend object) throws SQLException {
				return null;
			}

			@Override
			protected ODocument insert(ODocument document,
					CharacterFriend friend) throws SQLException {
				document.field(CHAR_ID, friend.getCharacterID());
				document.field(CHAR_ID_FRIEND, friend.getFriendID());
				return document;
			}
		});
	}

	@Override
	public int updateObjects(CharacterFriend... friends) {
		// it is not possible update friend objects, because they are only a ID
		// pair and IDs are immutable
		return 0;
	}

	@Override
	public int deleteObjects(CharacterFriend... friends) {
		return database.query(new InsertUpdateQuery<CharacterFriend>(friends) {
			@Override
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database, final CharacterFriend friend) {
				return new ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>>(
						database, CLASS_NAME,
						new OQueryContextNativeSchema<ODocument>()) {
					private static final long serialVersionUID = 1L;

					@Override
					public boolean filter(
							OQueryContextNativeSchema<ODocument> criteria) {
						return criteria.field(CHAR_ID)
								.eq(friend.getCharacterID().getID()).and()
								.field(CHAR_ID_FRIEND)
								.eq(friend.getFriendID().getID()).go();
					};
				};
			}

			@Override
			protected ODocument update(ODocument document,
					CharacterFriend object) throws SQLException {
				document.delete();
				return null;
			}

			@Override
			protected ODocument insert(ODocument document,
					CharacterFriend friend) throws SQLException {
				return null;
			}
		});
	}

	@Override
	public boolean save(final CharacterFriendList friends) {
		for (final CharacterFriend friend : friends) {
			if (!save(friend))
				return false;
		}
		return true;
	}

	@Override
	public boolean delete(final CharacterFriendList friends) {
		for (final CharacterFriend friend : friends) {
			if (!delete(friend))
				return false;
		}
		return true;
	}
}
