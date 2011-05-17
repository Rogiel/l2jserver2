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
package com.l2jserver;

import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.blowfish.BlowfishKeygenService;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.network.NetworkService;

public class L2JGameServerMain {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		final L2JGameServer server = new L2JGameServer();
		try {
			final ServiceManager serviceManager = server.getInjector()
					.getInstance(ServiceManager.class);

			serviceManager.start(CacheService.class);
			serviceManager.start(ConfigurationService.class);
			serviceManager.start(DatabaseService.class);

			serviceManager.start(ScriptingService.class);
			serviceManager.start(TemplateService.class);

			serviceManager.start(BlowfishKeygenService.class);
			serviceManager.start(NetworkService.class);
		} catch (Exception e) {
			System.out.println("GameServer could not be started!");
			e.printStackTrace();
		}

		Thread.sleep(60 * 60 * 1000);
	}

}
