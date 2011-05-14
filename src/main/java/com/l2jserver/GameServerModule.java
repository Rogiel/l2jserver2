package com.l2jserver;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.l2jserver.db.dao.DAOModuleMySQL5;
import com.l2jserver.model.id.factory.IDFactoryModule;
import com.l2jserver.routines.GameServerInitializationRoutine;
import com.l2jserver.service.ServiceModule;

/**
 * The game server Google Guice {@link Module}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class GameServerModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new ServiceModule());
		install(new IDFactoryModule());
		install(new DAOModuleMySQL5());

		// routines
		bind(GameServerInitializationRoutine.class);
	}
}
