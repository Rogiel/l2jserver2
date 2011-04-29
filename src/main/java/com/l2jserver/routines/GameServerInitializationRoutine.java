package com.l2jserver.routines;

import com.google.inject.Inject;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.logging.LoggingService;
import com.l2jserver.service.network.NetworkService;

public class GameServerInitializationRoutine implements Routine<Boolean> {
	private final ServiceManager serviceManager;

	@Inject
	public GameServerInitializationRoutine(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public Boolean call() throws Exception {
		serviceManager.start(LoggingService.class);
		serviceManager.start(ConfigurationService.class);
		serviceManager.start(DatabaseService.class);
		
		serviceManager.start(NetworkService.class);
		return true;
	}
}
