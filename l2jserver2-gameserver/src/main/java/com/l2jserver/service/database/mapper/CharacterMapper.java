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
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.Mapper;
import com.l2jserver.service.database.model.QCharacter;
import com.l2jserver.util.geometry.Point3D;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class CharacterMapper implements Mapper<L2Character, QCharacter> {
	private final Mapper<CharacterID, QCharacter> idMapper = new Mapper<CharacterID, QCharacter>() {
		@Override
		public CharacterID map(QCharacter e, DatabaseRow row) {
			return idProvider.resolveID(row.get(e.characterId));
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
	public L2Character map(QCharacter e, DatabaseRow row) {
		final CharacterClass charClass = row.get(e.characterClass);
		final CharacterTemplateID templateId = templateIdProvider
				.resolveID(charClass.id);
		final CharacterTemplate template = templateId.getTemplate();

		final L2Character character = template.create();

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
	
	public Mapper<CharacterID, QCharacter> getIDMapper() {
		return idMapper;
	}
}