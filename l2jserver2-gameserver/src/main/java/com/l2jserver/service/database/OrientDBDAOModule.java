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
import com.l2jserver.service.database.orientdb.OrientDBCharacterDAO;

/**
 * Google Guice {@link Module} for Orient Database DAOs
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class OrientDBDAOModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(CharacterDAO.class).to(OrientDBCharacterDAO.class).in(
				Scopes.SINGLETON);
		// bind(CharacterFriendDAO.class).to(OrientDBCharacterFriendDAO.class).in(
		// Scopes.SINGLETON);
		//
		// bind(NPCDAO.class).to(OrientDBNPCDAO.class).in(Scopes.SINGLETON);
		//
		// bind(ItemDAO.class).to(OrientDBItemDAO.class).in(Scopes.SINGLETON);
		// bind(ClanDAO.class).to(OrientDBClanDAO.class).in(Scopes.SINGLETON);
		//
		// // logs
		// bind(ChatMessageDAO.class).to(OrientDBChatMessageDAO.class).in(
		// Scopes.SINGLETON);

		// DAO Resolver
		bind(DAOResolver.class).to(GameServerDAOResolver.class).in(
				Scopes.SINGLETON);
	}
}
