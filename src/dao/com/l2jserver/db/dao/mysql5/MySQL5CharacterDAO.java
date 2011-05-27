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
package com.l2jserver.db.dao.mysql5;

import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.db.dao.jdbc.JDBCCharacterDAO;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ClanIDProvider;
import com.l2jserver.model.id.provider.AccountIDProvider;
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.service.database.DatabaseService;

/**
 * {@link CharacterDAO} implementation for MySQL5
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MySQL5CharacterDAO extends JDBCCharacterDAO implements
		CharacterDAO {
	public MySQL5CharacterDAO(DatabaseService database,
			CharacterIDProvider idFactory,
			CharacterTemplateIDProvider templateIdFactory,
			AccountIDProvider accountIdFactory, ClanIDProvider clanIdFactory) {
		super(database, idFactory, templateIdFactory, accountIdFactory,
				clanIdFactory);
	}
}
