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
package com.l2jserver.service.database.jdbc.derby;

import com.google.inject.Inject;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.NPCDAO;
import com.l2jserver.model.id.object.provider.NPCIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.jdbc.JDBCNPCDAO;

/**
 * {@link CharacterDAO} implementation for MySQL5
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DerbyNPCDAO extends JDBCNPCDAO implements NPCDAO {
	/**
	 * @param database
	 *            the database service
	 * @param idProvider
	 *            the npc id provider
	 * @param templateIdProvider
	 *            the npc template id provider
	 */
	@Inject
	public DerbyNPCDAO(DatabaseService database, NPCIDProvider idProvider,
			NPCTemplateIDProvider templateIdProvider) {
		super(database, idProvider, templateIdProvider);
	}
}
