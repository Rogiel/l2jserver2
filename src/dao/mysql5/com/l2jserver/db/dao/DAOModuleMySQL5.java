package com.l2jserver.db.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.l2jserver.db.dao.mysql5.MySQL5CharacterDAO;
import com.l2jserver.db.dao.mysql5.MySQL5ItemDAO;

public class DAOModuleMySQL5 extends AbstractModule {
	@Override
	protected void configure() {
		bind(CharacterDAO.class).to(MySQL5CharacterDAO.class).in(
				Scopes.SINGLETON);
		bind(ItemDAO.class).to(MySQL5ItemDAO.class).in(Scopes.SINGLETON);
	}
}
