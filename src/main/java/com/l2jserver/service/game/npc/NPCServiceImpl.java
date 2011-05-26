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
package com.l2jserver.service.game.npc;

import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.db.dao.NPCDAO;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.client.CharacterActionPacket.CharacterAction;
import com.l2jserver.game.net.packet.server.ActorAttackPacket;
import com.l2jserver.model.server.AttackHit;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.controller.NPCController;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Default {@link NPCService} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCServiceImpl extends AbstractService implements NPCService {
	/**
	 * The {@link SpawnService} used to spawn the {@link NPC} instances
	 */
	private final SpawnService spawnService;
	/**
	 * The {@link NetworkService} used to discover {@link Lineage2Connection}
	 */
	private final NetworkService networkService;
	/**
	 * The {@link CharacterService}
	 */
	@SuppressWarnings("unused")
	private final CharacterService characterService;

	/**
	 * The {@link NPCDAO}
	 */
	private final NPCDAO npcDao;

	/**
	 * The {@link Injector} used to create {@link NPCController} instances
	 */
	@Inject
	private Injector injector;

	/**
	 * The map containing all active controllers
	 */
	private Map<Class<? extends NPCController>, NPCController> controllers = CollectionFactory
			.newMap();

	@Inject
	public NPCServiceImpl(SpawnService spawnService,
			NetworkService networkService, CharacterService characterService,
			NPCDAO npcDao, Injector injector) {
		this.spawnService = spawnService;
		this.networkService = networkService;
		this.characterService = characterService;
		this.npcDao = npcDao;
		this.injector = injector;
	}

	@Override
	public void action(NPC npc, L2Character character, CharacterAction action)
			throws ActionServiceException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(action, "action");

		final Lineage2Connection conn = networkService.discover(character
				.getID());
		try {
			final NPCController controller = getController(npc);
			controller.action(npc, conn, character, new String[0]);
		} catch (L2Exception e) {
			throw new ActionServiceException(e);
		}
	}

	@Override
	public void action(NPC npc, L2Character character, String... args)
			throws ActionServiceException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(character, "character");
		if (args == null)
			args = new String[0];

		final Lineage2Connection conn = networkService.discover(character
				.getID());
		try {
			final NPCController controller = getController(npc);
			controller.action(npc, conn, character, args);
		} catch (L2Exception e) {
			throw new ActionServiceException(e);
		}
	}

	@Override
	public List<NPC> spawnAll() throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException {
		final List<NPC> npcs = npcDao.loadAll();
		for (final NPC npc : npcs) {
			spawnService.spawn(npc, null);
		}
		return npcs;
	}

	@Override
	public void attack(NPC npc, Lineage2Connection conn, L2Character attacker)
			throws NotAttackableNPCServiceException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(conn, "conn");
		Preconditions.checkNotNull(attacker, "attacker");

		conn.write(new ActorAttackPacket(conn.getCharacter(), new AttackHit(
				conn.getCharacter(), npc)));
	}

	private NPCController getController(NPC npc) {
		// make sure everything's synchronized-no duplicated instances
		synchronized (controllers) {
			final Class<? extends NPCController> controllerClass = npc
					.getTemplate().getControllerClass();
			NPCController controller = controllers.get(controllerClass);
			if (controller == null) {
				controller = injector.getInstance(controllerClass);
				controllers.put(controllerClass, controller);
			}
			return controller;
		}
	}
}
