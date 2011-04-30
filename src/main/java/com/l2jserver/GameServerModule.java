package com.l2jserver;


import com.google.inject.AbstractModule;
import com.l2jserver.db.dao.DAOModuleMySQL5;
import com.l2jserver.model.id.factory.IDFactoryModule;
import com.l2jserver.routines.GameServerInitializationRoutine;
import com.l2jserver.service.BasicServiceModule;
import com.l2jserver.service.ServiceModule;

public class GameServerModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new BasicServiceModule());
		install(new ServiceModule());
		install(new IDFactoryModule());
		install(new DAOModuleMySQL5());

		// routines
		bind(GameServerInitializationRoutine.class);
	}
}
