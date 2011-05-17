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
package com.l2jserver.routines;

import com.google.inject.Inject;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.blowfish.BlowfishKeygenService;
import com.l2jserver.service.cache.CacheService;
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
		serviceManager.start(CacheService.class);
		serviceManager.start(ConfigurationService.class);
		serviceManager.start(DatabaseService.class);

		serviceManager.start(ScriptingService.class);
		serviceManager.start(TemplateService.class);

		serviceManager.start(BlowfishKeygenService.class);
		serviceManager.start(NetworkService.class);
		return true;
	}
}
