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
package com.l2jserver.service.database.jdbc.mysql5;

import com.google.inject.Inject;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.jdbc.JDBCItemDAO;

/**
 * {@link ItemDAO} implementation for MySQL5
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MySQL5ItemDAO extends JDBCItemDAO implements ItemDAO {
	/**
	 * @param database the database service
	 * @param idFactory the item id provider
	 * @param templateIdFactory the item template id provider
	 * @param charIdFactory the character id provider
	 */
	@Inject
	public MySQL5ItemDAO(DatabaseService database, ItemIDProvider idFactory,
			ItemTemplateIDProvider templateIdFactory,
			CharacterIDProvider charIdFactory) {
		super(database, idFactory, templateIdFactory, charIdFactory);
	}
}
