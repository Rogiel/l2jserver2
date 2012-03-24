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
import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ClanIDProvider;
import com.l2jserver.model.id.provider.AccountIDProvider;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.dao.AbstractMapper;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.PrimaryKeyMapper;
import com.l2jserver.service.database.dao.WritableDatabaseRow;
import com.l2jserver.service.database.model.QCharacter;
import com.l2jserver.util.geometry.Point3D;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class CharacterMapper extends
		AbstractMapper<L2Character, Integer, CharacterID, QCharacter> {
	/**
	 * The primary key mapper
	 */
	private final PrimaryKeyMapper<CharacterID, Integer> pk = new PrimaryKeyMapper<CharacterID, Integer>() {
		@Override
		public CharacterID createID(Integer raw) {
			return idProvider.resolveID(raw);
		}
	};

	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider idProvider;
	/**
	 * The {@link CharacterTemplateID} provider
	 */
	private final CharacterTemplateIDProvider templateIdProvider;

	/**
	 * The {@link AccountID} provider
	 */
	private final AccountIDProvider accountIdProvider;
	/**
	 * The {@link ClanID} provider
	 */
	private final ClanIDProvider clanIdProvider;

	/**
	 * @param idProvider
	 *            the {@link CharacterID} provider
	 * @param templateIdProvider
	 *            the {@link CharacterTemplateID} provider
	 * @param accountIdProvider
	 *            the {@link AccountID} provider
	 * @param clanIdProvider
	 *            the {@link ClanID} provider
	 */
	@Inject
	public CharacterMapper(final CharacterIDProvider idProvider,
			CharacterTemplateIDProvider templateIdProvider,
			AccountIDProvider accountIdProvider, ClanIDProvider clanIdProvider) {
		this.idProvider = idProvider;
		this.templateIdProvider = templateIdProvider;
		this.accountIdProvider = accountIdProvider;
		this.clanIdProvider = clanIdProvider;
	}

	@Override
	public L2Character select(QCharacter e, DatabaseRow row) {
		final CharacterClass charClass = row.get(e.characterClass);
		final CharacterTemplateID templateId = templateIdProvider
				.resolveID(charClass.id);
		final CharacterTemplate template = templateId.getTemplate();

		final L2Character character = new L2Character(template);

		character.setID(idProvider.resolveID(row.get(e.characterId)));
		character
				.setAccountID(accountIdProvider.resolveID(row.get(e.accountId)));
		if (!row.isNull(e.clanId))
			character.setClanID(clanIdProvider.resolveID(row.get(e.clanId)));

		character.setName(row.get(e.name));

		character.setRace(row.get(e.race));
		character.setCharacterClass(row.get(e.characterClass));
		character.setSex(row.get(e.sex));

		character.setLevel(row.get(e.level));
		character.setExperience(row.get(e.experience));
		character.setSP(row.get(e.sp));

		character.setHP(row.get(e.hp));
		character.setMP(row.get(e.mp));
		character.setCP(row.get(e.cp));

		character.setPoint(Point3D.fromXYZA(row.get(e.pointX),
				row.get(e.pointY), row.get(e.pointZ), row.get(e.pointAngle)));

		// appearance
		character.getAppearance().setHairStyle(row.get(e.appearanceHairStyle));
		character.getAppearance().setHairColor(row.get(e.appearanceHairColor));
		character.getAppearance().setFace(row.get(e.apperanceFace));

		return character;
	}

	@Override
	public void insert(QCharacter e, L2Character object, WritableDatabaseRow row) {
		// same as update, reuse it
		update(e, object, row);
	}

	@Override
	public void update(QCharacter e, L2Character object, WritableDatabaseRow row) {
		row.set(e.characterId, object.getID().getID())
				.set(e.accountId, object.getAccountID().getID())
				.set(e.clanId,
						(object.getClanID() != null ? object.getClanID()
								.getID() : null))
				.set(e.name, object.getName())
				.set(e.race, object.getRace())
				.set(e.characterClass, object.getCharacterClass())
				.set(e.sex, object.getSex())
				.set(e.level, object.getLevel())
				.set(e.experience, object.getExperience())
				.set(e.sp, object.getSP())
				.set(e.hp, object.getHP())
				.set(e.mp, object.getMP())
				.set(e.cp, object.getCP())
				.set(e.pointX, object.getPoint().getX())
				.set(e.pointY, object.getPoint().getY())
				.set(e.pointZ, object.getPoint().getZ())
				.set(e.pointAngle, object.getPoint().getAngle())
				.set(e.appearanceHairStyle,
						object.getAppearance().getHairStyle())
				.set(e.appearanceHairColor,
						object.getAppearance().getHairColor())
				.set(e.apperanceFace, object.getAppearance().getFace());
	}

	@Override
	public PrimaryKeyMapper<CharacterID, Integer> getPrimaryKeyMapper() {
		return pk;
	}
}