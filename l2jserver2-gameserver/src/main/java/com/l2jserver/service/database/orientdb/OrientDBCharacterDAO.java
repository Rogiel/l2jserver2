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
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ClanIDProvider;
import com.l2jserver.model.id.provider.AccountIDProvider;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterRace;
import com.l2jserver.model.template.character.CharacterTemplate;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterAppearance;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.service.database.AbstractOrientDatabaseService.CachedMapper;
import com.l2jserver.service.database.AbstractOrientDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.AbstractOrientDatabaseService.Mapper;
import com.l2jserver.service.database.AbstractOrientDatabaseService.SelectListQuery;
import com.l2jserver.service.database.AbstractOrientDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.util.geometry.Point3D;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNativeSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class OrientDBCharacterDAO extends
		AbstractOrientDBDAO<L2Character, CharacterID> implements CharacterDAO {
	/**
	 * The {@link CharacterID} factory
	 */
	private final CharacterIDProvider idFactory;
	/**
	 * The {@link CharacterTemplateID} factory
	 */
	private final CharacterTemplateIDProvider templateIdFactory;
	/**
	 * The {@link AccountID} factory
	 */
	private final AccountIDProvider accountIdFactory;
	/**
	 * The {@link ClanID} factory
	 */
	private final ClanIDProvider clanIdFactory;

	/**
	 * Character table name
	 */
	public static final String CLASS_NAME = L2Character.class.getSimpleName();
	// FIELDS
	public static final String CHAR_ID = "character_id";
	public static final String ACCOUNT_ID = "account_id";
	public static final String CLAN_ID = "clan_id";
	public static final String NAME = "name";

	public static final String RACE = "race";
	public static final String CLASS = "class";
	public static final String SEX = "sex";

	public static final String LEVEL = "level";
	public static final String EXPERIENCE = "experience";
	public static final String SP = "sp";

	public static final String HP = "hp";
	public static final String MP = "mp";
	public static final String CP = "cp";

	public static final String POINT_X = "point_x";
	public static final String POINT_Y = "point_y";
	public static final String POINT_Z = "point_z";
	public static final String POINT_ANGLE = "point_angle";

	public static final String APPEARANCE_HAIR_STYLE = "appearance_hair_style";
	public static final String APPEARANCE_HAIR_COLOR = "appearance_hair_color";
	public static final String APPEARANCE_FACE = "apperance_face";

	/**
	 * The mapper for {@link CharacterID}
	 */
	private final Mapper<CharacterID> idMapper = new Mapper<CharacterID>() {
		@Override
		public CharacterID map(ODocument document) throws SQLException {
			return idFactory.resolveID((Integer) document.field(CHAR_ID));
		}
	};

	/**
	 * The {@link Mapper} for {@link L2Character}
	 */
	private final Mapper<L2Character> mapper = new CachedMapper<L2Character, CharacterID>(
			database, idMapper) {
		@Override
		protected L2Character map(CharacterID id, ODocument document)
				throws SQLException {
			final CharacterClass charClass = (CharacterClass) document
					.field(CLASS);
			final CharacterTemplateID templateId = templateIdFactory
					.resolveID(charClass.id);
			final CharacterTemplate template = templateId.getTemplate();

			final L2Character character = template.create();

			character.setID(id);
			character.setAccountID(accountIdFactory.resolveID((String) document
					.field(ACCOUNT_ID)));
			if (document.containsField(CLAN_ID))
				character.setClanID(clanIdFactory.resolveID((Integer) document
						.field(CLAN_ID)));

			character.setName((String) document.field(NAME));

			character.setRace((CharacterRace) document.field(RACE));
			character.setCharacterClass((CharacterClass) document.field(CLASS));
			character.setSex((ActorSex) document.field(SEX));

			character.setLevel((Integer) document.field(LEVEL));
			character.setExperience((Long) document.field(EXPERIENCE));
			character.setSP((Integer) document.field(SP));

			character.setHP((Double) document.field(HP));
			character.setMP((Double) document.field(MP));
			character.setCP((Double) document.field(CP));

			character.setPoint(Point3D.fromXYZA(
					(Integer) document.field(POINT_X),
					(Integer) document.field(POINT_Y),
					(Integer) document.field(POINT_Z),
					(Double) document.field(POINT_ANGLE)));

			// appearance
			character.getAppearance().setHairStyle(
					(CharacterHairStyle) document.field(APPEARANCE_HAIR_STYLE));
			character.getAppearance().setHairColor(
					(CharacterHairColor) document.field(APPEARANCE_HAIR_COLOR));
			character.getAppearance().setFace(
					(CharacterFace) document.field(APPEARANCE_FACE));

			return character;
		}
	};

	/**
	 * @param database
	 *            the database service
	 * @param idFactory
	 *            the character id provider
	 * @param templateIdFactory
	 *            the template id provider
	 * @param accountIdFactory
	 *            the account id provider
	 * @param clanIdFactory
	 *            the clan id provider
	 */
	@Inject
	protected OrientDBCharacterDAO(DatabaseService database,
			final CharacterIDProvider idFactory,
			CharacterTemplateIDProvider templateIdFactory,
			AccountIDProvider accountIdFactory, ClanIDProvider clanIdFactory) {
		super(database);
		this.idFactory = idFactory;
		this.templateIdFactory = templateIdFactory;
		this.accountIdFactory = accountIdFactory;
		this.clanIdFactory = clanIdFactory;
	}

	@Override
	public L2Character select(final CharacterID id) {
		return database.query(new SelectSingleQuery<L2Character>() {
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
						return criteria.field(CHAR_ID).eq(id.getID()).go();
					};
				};
			}

			@Override
			protected Mapper<L2Character> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public Collection<CharacterID> selectIDs() {
		return database.query(new SelectListQuery<CharacterID>() {
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
			protected Mapper<CharacterID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public int insertObjects(L2Character... objects) {
		return database.query(new InsertUpdateQuery<L2Character>(objects) {
			@Override
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database, L2Character object) {
				return null;
			}

			@Override
			protected ODocument update(ODocument document, L2Character character)
					throws SQLException {
				return null;
			}

			@Override
			protected ODocument insert(ODocument document, L2Character character)
					throws SQLException {
				final CharacterAppearance appearance = character
						.getAppearance();

				document.field(CHAR_ID, character.getID().getID());
				document.field(ACCOUNT_ID, character.getAccountID().getID());
				if (character.getClanID() != null)
					document.field(CLAN_ID, character.getClanID().getID());

				document.field(NAME, character.getName());

				document.field(RACE, character.getRace().name());
				document.field(CLASS, character.getCharacterClass().name());
				document.field(SEX, character.getSex().name());

				document.field(LEVEL, character.getLevel());
				document.field(EXPERIENCE, character.getExperience());
				document.field(SP, character.getSP());

				document.field(HP, character.getHP());
				document.field(MP, character.getMP());
				document.field(CP, character.getCP());

				document.field(POINT_X, character.getPoint().getX());
				document.field(POINT_Y, character.getPoint().getY());
				document.field(POINT_Z, character.getPoint().getZ());
				document.field(POINT_ANGLE, character.getPoint().getAngle());

				// appearance
				document.field(APPEARANCE_HAIR_STYLE, appearance.getHairStyle()
						.name());
				document.field(APPEARANCE_HAIR_COLOR, appearance.getHairColor()
						.name());
				document.field(APPEARANCE_FACE, appearance.getFace().name());

				return document;
			}
		});
	}

	@Override
	public int updateObjects(final L2Character... characters) {
		return database.query(new InsertUpdateQuery<L2Character>(characters) {
			@Override
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database, final L2Character character) {
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
			protected ODocument update(ODocument document, L2Character character)
					throws SQLException {
				final CharacterAppearance appearance = character
						.getAppearance();

				document.field(ACCOUNT_ID, character.getAccountID().getID());
				if (character.getClanID() != null)
					document.field(CLAN_ID, character.getClanID().getID());

				document.field(NAME, character.getName());

				document.field(RACE, character.getRace().name());
				document.field(CLASS, character.getCharacterClass().name());
				document.field(SEX, character.getSex().name());

				document.field(LEVEL, character.getLevel());
				document.field(EXPERIENCE, character.getExperience());
				document.field(SP, character.getSP());

				document.field(HP, character.getHP());
				document.field(MP, character.getMP());
				document.field(CP, character.getCP());

				document.field(POINT_X, character.getPoint().getX());
				document.field(POINT_Y, character.getPoint().getY());
				document.field(POINT_Z, character.getPoint().getZ());
				document.field(POINT_ANGLE, character.getPoint().getAngle());

				// appearance
				document.field(APPEARANCE_HAIR_STYLE, appearance.getHairStyle()
						.name());
				document.field(APPEARANCE_HAIR_COLOR, appearance.getHairColor()
						.name());
				document.field(APPEARANCE_FACE, appearance.getFace().name());

				return document;
			}

			@Override
			protected ODocument insert(ODocument document, L2Character character)
					throws SQLException {
				return null;
			}
		});
	}

	@Override
	public int deleteObjects(L2Character... objects) {
		return database.query(new InsertUpdateQuery<L2Character>(objects) {
			@Override
			protected ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
					ODatabaseDocumentTx database, final L2Character character) {
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
			protected ODocument update(ODocument document, L2Character character)
					throws SQLException {
				document.delete();
				return null;
			}

			@Override
			protected ODocument insert(ODocument document, L2Character character)
					throws SQLException {
				return null;
			}
		});
	}

	@Override
	public void load(Clan clan) {

	}

	@Override
	public L2Character selectByName(final String name) {
		return database.query(new SelectSingleQuery<L2Character>() {
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
						return criteria.field(NAME).eq(name).go();
					};
				};
			}

			@Override
			protected Mapper<L2Character> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<L2Character> selectByAccount(final AccountID account) {
		return database.query(new SelectListQuery<L2Character>() {
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
						return criteria.field(ACCOUNT_ID).eq(account.getID()).go();
					};
				};
			}

			@Override
			protected Mapper<L2Character> mapper() {
				return mapper;
			}
		});
	}
}
