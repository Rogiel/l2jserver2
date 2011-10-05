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
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.map.pathing.PathingService;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.game.world.WorldIDService;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.service.network.keygen.BlowfishKeygenService;

/**
 * Main class
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class L2JGameServerMain {
	/**
	 * Main method
	 * 
	 * @param args
	 *            no arguments are used
	 */
	public static void main(String[] args) {
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
			serviceManager.start(NPCService.class);

			serviceManager.start(CharacterService.class);
			serviceManager.start(PathingService.class);

			serviceManager.start(BlowfishKeygenService.class);
			serviceManager.start(NetworkService.class);

			// final long free = Runtime.getRuntime().freeMemory();
			// final long allocated = Runtime.getRuntime().totalMemory();
			// final long maximum = Runtime.getRuntime().maxMemory();
			// final long processors =
			// Runtime.getRuntime().availableProcessors();
		} catch (Exception e) {
			System.out.println("GameServer could not be started!");
			e.printStackTrace();
			System.exit(0);
		}
	}
	//
	// /**
	// * This method does an static spawn for an object
	// *
	// * @throws AlreadySpawnedServiceException
	// * @throws SpawnPointNotFoundServiceException
	// */
	// private static void staticSpawn(Injector injector)
	// throws SpawnPointNotFoundServiceException,
	// AlreadySpawnedServiceException {
	// final NPCTemplateIDProvider templateProvider = injector
	// .getInstance(NPCTemplateIDProvider.class);
	// final NPCIDProvider provider = injector
	// .getInstance(NPCIDProvider.class);
	// final SpawnService spawnService = injector
	// .getInstance(SpawnService.class);
	//
	// final NPCTemplateID id = templateProvider.createID(12077);
	// final NPC npc = id.getTemplate().create();
	//
	// npc.setID(provider.createID());
	// // close to char spawn
	// npc.setPoint(Point.fromXYZ(-71301, 258259, -3134));
	//
	// spawnService.spawn(npc, null);
	//
	// // close spawn gatekepper
	// final NPCTemplateID gid = templateProvider.createID(30006);
	// final NPC gatekeeper = gid.getTemplate().create();
	// gatekeeper.setID(provider.createID());
	// gatekeeper.setPoint(Point.fromXYZ(-71301, 258559, -3134));
	// spawnService.spawn(gatekeeper, null);
	//
	// // spawn tamil - orc village
	// final NPCTemplateID tamilId = templateProvider.createID(30576);
	// final NPC tamil = tamilId.getTemplate().create();
	// tamil.setID(provider.createID());
	// tamil.setPoint(Point.fromXYZ(-45264, -112512, -240));
	// spawnService.spawn(tamil, null);
	// }
}
