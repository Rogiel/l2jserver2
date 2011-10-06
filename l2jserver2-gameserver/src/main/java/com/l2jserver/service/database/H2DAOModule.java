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
import com.l2jserver.service.database.jdbc.h2.H2CharacterDAO;
import com.l2jserver.service.database.jdbc.h2.H2CharacterFriendDAO;
import com.l2jserver.service.database.jdbc.h2.H2ChatMessageDAO;
import com.l2jserver.service.database.jdbc.h2.H2ClanDAO;
import com.l2jserver.service.database.jdbc.h2.H2ItemDAO;
import com.l2jserver.service.database.jdbc.h2.H2NPCDAO;

/**
 * Google Guice {@link Module} for H2 DAOs
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class H2DAOModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(CharacterDAO.class).to(H2CharacterDAO.class).in(Scopes.SINGLETON);
		bind(CharacterFriendDAO.class).to(H2CharacterFriendDAO.class).in(
				Scopes.SINGLETON);

		bind(NPCDAO.class).to(H2NPCDAO.class).in(Scopes.SINGLETON);

		bind(ItemDAO.class).to(H2ItemDAO.class).in(Scopes.SINGLETON);
		bind(ClanDAO.class).to(H2ClanDAO.class).in(Scopes.SINGLETON);

		// logs
		bind(ChatMessageDAO.class).to(H2ChatMessageDAO.class).in(
				Scopes.SINGLETON);
	}
}
