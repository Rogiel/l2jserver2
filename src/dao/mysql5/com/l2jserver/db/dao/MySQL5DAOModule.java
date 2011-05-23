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
package com.l2jserver.db.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.l2jserver.db.dao.mysql5.MySQL5CharacterDAO;
import com.l2jserver.db.dao.mysql5.MySQL5CharacterFriendDAO;
import com.l2jserver.db.dao.mysql5.MySQL5ClanDAO;
import com.l2jserver.db.dao.mysql5.MySQL5ItemDAO;
import com.l2jserver.db.dao.mysql5.MySQL5NPCDAO;

/**
 * Google Guice {@link Module} for MySQL5 DAOs
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MySQL5DAOModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(CharacterDAO.class).to(MySQL5CharacterDAO.class).in(
				Scopes.SINGLETON);
		bind(CharacterFriendDAO.class).to(MySQL5CharacterFriendDAO.class).in(
				Scopes.SINGLETON);

		bind(NPCDAO.class).to(MySQL5NPCDAO.class).in(Scopes.SINGLETON);

		bind(ItemDAO.class).to(MySQL5ItemDAO.class).in(Scopes.SINGLETON);
		bind(ClanDAO.class).to(MySQL5ClanDAO.class).in(Scopes.SINGLETON);
	}
}
