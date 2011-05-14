package com.l2jserver.routines;

import com.google.inject.Inject;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.network.NetworkService;

/**
 * Routine used to initialize the server. Starts all services.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class GameServerInitializationRoutine implements Routine<Boolean> {
	/**
	 * The service manager
	 */
	private final ServiceManager serviceManager;

	/**
	 * Creates a new instance
	 * 
	 * @param serviceManager
	 *            the service manager
	 */
	@Inject
	public GameServerInitializationRoutine(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public Boolean call() throws Exception {
		// serviceManager.start(LoggingService.class);
		serviceManager.start(ConfigurationService.class);
		serviceManager.start(DatabaseService.class);

		serviceManager.start(ScriptingService.class);
		serviceManager.start(TemplateService.class);

		serviceManager.start(NetworkService.class);
		return true;
	}
}
