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
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ClanIDProvider;
import com.l2jserver.model.world.Clan;
import com.l2jserver.service.database.dao.AbstractMapper;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.PrimaryKeyMapper;
import com.l2jserver.service.database.dao.WritableDatabaseRow;
import com.l2jserver.service.database.model.QClan;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class ClanMapper extends AbstractMapper<Clan, Integer, ClanID, QClan> {
	private final PrimaryKeyMapper<ClanID, Integer> idMapper = new PrimaryKeyMapper<ClanID, Integer>() {
		@Override
		public ClanID createID(Integer raw) {
			return idProvider.resolveID(raw);
		}
	};

	/**
	 * The {@link ClanID} provider
	 */
	private final ClanIDProvider idProvider;

	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider charIdProvider;

	/**
	 * @param idProvider
	 *            the {@link ClanID} provider
	 * @param charIdProvider
	 *            the character ID provider
	 */
	@Inject
	public ClanMapper(final ClanIDProvider idProvider,
			CharacterIDProvider charIdProvider) {
		this.idProvider = idProvider;
		this.charIdProvider = charIdProvider;
	}

	@Override
	public Clan select(QClan e, DatabaseRow row) {
		final Clan clan = new Clan();
		clan.setID(idProvider.resolveID(row.get(e.clanId)));
		clan.setID(charIdProvider.resolveID(row.get(e.characterIdLeader)));
		return clan;
	}

	@Override
	public void insert(QClan e, Clan object, WritableDatabaseRow row) {
		update(e, object, row);
	}

	@Override
	public void update(QClan e, Clan object, WritableDatabaseRow row) {
		row.set(e.clanId, object.getID().getID()).set(e.characterIdLeader,
				object.getLeaderID().getID());
	}

	@Override
	public PrimaryKeyMapper<ClanID, Integer> getPrimaryKeyMapper() {
		return idMapper;
	}
}