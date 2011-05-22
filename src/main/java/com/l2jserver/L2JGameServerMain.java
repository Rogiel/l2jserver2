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

import com.google.inject.Injector;
import com.l2jserver.model.id.object.provider.NPCIDProvider;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.pathing.PathingService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.game.world.WorldIDService;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.service.network.keygen.BlowfishKeygenService;
import com.l2jserver.util.dimensional.Point;

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
			serviceManager.start(WorldIDService.class);

			serviceManager.start(ScriptingService.class);
			serviceManager.start(TemplateService.class);

			serviceManager.start(ChatService.class);

			serviceManager.start(CharacterService.class);
			serviceManager.start(PathingService.class);

			serviceManager.start(BlowfishKeygenService.class);
			serviceManager.start(NetworkService.class);

			staticSpawn(server.getInjector());
		} catch (Exception e) {
			System.out.println("GameServer could not be started!");
			e.printStackTrace();
			System.exit(0);
		}

		// Thread.sleep(60 * 60 * 1000);
	}

	/**
	 * This method does an static spawn for an object
	 * 
	 * @throws AlreadySpawnedServiceException
	 * @throws SpawnPointNotFoundServiceException
	 */
	private static void staticSpawn(Injector injector)
			throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException {
		final NPCTemplateIDProvider templateProvider = injector
				.getInstance(NPCTemplateIDProvider.class);
		final NPCIDProvider provider = injector
				.getInstance(NPCIDProvider.class);
		final SpawnService spawnService = injector
				.getInstance(SpawnService.class);

		final NPCTemplateID id = templateProvider.createID(12077);
		final NPC npc = id.getTemplate().create();

		npc.setID(provider.createID());
		// close to char spawn
		npc.setPoint(Point.fromXYZ(-71301, 258259, -3134));

		spawnService.spawn(npc, null);
	}
}
