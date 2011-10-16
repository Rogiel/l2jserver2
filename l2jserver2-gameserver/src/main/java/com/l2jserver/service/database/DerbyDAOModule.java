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
package com.l2jserver.service.database;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.CharacterFriendDAO;
import com.l2jserver.model.dao.ChatMessageDAO;
import com.l2jserver.model.dao.ClanDAO;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.dao.NPCDAO;
import com.l2jserver.service.database.jdbc.derby.DerbyCharacterDAO;
import com.l2jserver.service.database.jdbc.derby.DerbyCharacterFriendDAO;
import com.l2jserver.service.database.jdbc.derby.DerbyChatMessageDAO;
import com.l2jserver.service.database.jdbc.derby.DerbyClanDAO;
import com.l2jserver.service.database.jdbc.derby.DerbyItemDAO;
import com.l2jserver.service.database.jdbc.derby.DerbyNPCDAO;

/**
 * Google Guice {@link Module} for Derby DAOs
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DerbyDAOModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(CharacterDAO.class).to(DerbyCharacterDAO.class).in(
				Scopes.SINGLETON);
		bind(CharacterFriendDAO.class).to(DerbyCharacterFriendDAO.class).in(
				Scopes.SINGLETON);

		bind(NPCDAO.class).to(DerbyNPCDAO.class).in(Scopes.SINGLETON);

		bind(ItemDAO.class).to(DerbyItemDAO.class).in(Scopes.SINGLETON);
		bind(ClanDAO.class).to(DerbyClanDAO.class).in(Scopes.SINGLETON);

		// logs
		bind(ChatMessageDAO.class).to(DerbyChatMessageDAO.class).in(
				Scopes.SINGLETON);
	}
}
