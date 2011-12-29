/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.npc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.packet.client.CM_CHAR_ACTION.CharacterAction;
import com.l2jserver.model.dao.NPCDAO;
import com.l2jserver.model.template.npc.NPCTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Actor.ActorState;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.NPCController;
import com.l2jserver.model.world.npc.NPCController.NPCControllerException;
import com.l2jserver.model.world.npc.event.NPCDieEvent;
import com.l2jserver.model.world.npc.event.NPCTalkEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.core.threading.AbstractTask;
import com.l2jserver.service.core.threading.AsyncFuture;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.AttackService;
import com.l2jserver.service.game.character.CannotSetTargetServiceException;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcherService;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.geometry.Point3D;
import com.l2jserver.util.html.markup.HtmlTemplate;

/**
 * Default {@link NPCService} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ SpawnService.class, CharacterService.class, ThreadService.class,
		AttackService.class, DatabaseService.class })
public class NPCServiceImpl extends AbstractService implements NPCService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link SpawnService} used to spawn the {@link NPC} instances
	 */
	private final SpawnService spawnService;
	/**
	 * The {@link CharacterService}
	 */
	@SuppressWarnings("unused")
	private final CharacterService characterService;
	/**
	 * The {@link ThreadService}
	 */
	private final ThreadService threadService;
	/**
	 * The {@link AttackService}
	 */
	private final AttackService attackService;

	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcherService eventDispatcher;
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
	/**
	 * List of all loaded NPCs
	 */
	private Collection<NPC> npcs;

	/**
	 * @param spawnService
	 *            the spawn service
	 * @param characterService
	 *            the character service
	 * @param threadService
	 *            the thread service
	 * @param attackService
	 *            the attack service
	 * @param eventDispatcher
	 *            the world service event dispatcher
	 * @param npcDao
	 *            the npc DAO
	 * @param injector
	 *            the {@link Guice} {@link Injector}
	 */
	@Inject
	public NPCServiceImpl(SpawnService spawnService,
			CharacterService characterService, ThreadService threadService,
			AttackService attackService, WorldEventDispatcherService eventDispatcher,
			NPCDAO npcDao, Injector injector) {
		this.spawnService = spawnService;
		this.characterService = characterService;
		this.threadService = threadService;
		this.attackService = attackService;
		this.eventDispatcher = eventDispatcher;
		this.npcDao = npcDao;
		this.injector = injector;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		npcs = npcDao.loadAll();
		try {
			for (final NPC npc : npcs) {
				spawnService.spawn(npc, null);
			}
			log.info("Loaded {} NPC instances", npcs.size());
		} catch (SpawnPointNotFoundServiceException e) {
			throw new ServiceStartException(e);
		} catch (AlreadySpawnedServiceException e) {
			throw new ServiceStartException(e);
		}
	}

	@Override
	public void action(NPC npc, L2Character character, CharacterAction action)
			throws ActionServiceException, CannotSetTargetServiceException,
			NPCControllerException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(action, "action");

		log.debug("{} interacting with {} (action={})", new Object[] {
				character, npc, action });

		try {
			final NPCController controller = getController(npc);
			controller.action(npc, character, new String[0]);
		} catch (NPCControllerException e) {
			throw e;
		} catch (L2Exception e) {
			throw new ActionServiceException(e);
		}
	}

	@Override
	public void action(NPC npc, L2Character character, String... args)
			throws ActionServiceException, CannotSetTargetServiceException, NPCControllerException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(character, "character");
		if (args == null)
			args = new String[0];

		log.debug("{} interacting with {} (args={})", new Object[] {
				character, npc, Arrays.toString(args) });

		try {
			final NPCController controller = getController(npc);
			controller.action(npc, character, args);
		} catch (NPCControllerException e) {
			throw e;
		} catch (L2Exception e) {
			throw new ActionServiceException(e);
		}
	}

	@Override
	public void talk(NPC npc, L2Character character, HtmlTemplate template) {
		Preconditions.checkNotNull(template, "template");
		talk(npc, character, template.toHtmlString());
	}

	@Override
	public void talk(NPC npc, L2Character character, String html) {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(html, "html");
		eventDispatcher.dispatch(new NPCTalkEvent(npc, character, html));
	}

	@Override
	public void die(NPC npc, Actor killer) {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(killer, "killer");

		log.debug("{} was killed by {}", npc, killer);

		// set npc as dead
		npc.setState(ActorState.DEAD);

		// dispatch die event
		eventDispatcher.dispatch(new NPCDieEvent(npc, killer));

		// schedule corpse removal -- npc will be kept in the world until then
		spawnService.unspawn(npc, 5, TimeUnit.SECONDS);
		// schedule an respawn
		spawnService.spawn(npc, null, npc.getRespawnInterval(),
				TimeUnit.MILLISECONDS);

		// reset hp and cp
		npc.setHP(npc.getStats().getMaxHP());
		npc.setMP(npc.getStats().getMaxMP());
	}

	@Override
	public AsyncFuture<Boolean> move(final NPC npc, final Point3D point) {
		if (!npc.isIdle())
			// TODO throw an exception
			return null;
		npc.setState(ActorState.MOVING);

		log.debug("{} is moving to {}", npc, point);

		// calculate walking time
		final Point3D start = npc.getPoint();
		final double distance = start.getDistance(point);
		final double seconds = distance / npc.getTemplate().getRunSpeed();
		// TODO this is an dirty implementation!
		return threadService.async((int) (seconds * 1000),
				TimeUnit.MILLISECONDS, new AbstractTask<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						npc.setState(null);
						npc.setPoint(point);
						return false;
					}
				});
	}

	@Override
	public void attack(NPC npc, L2Character attacker)
			throws NotAttackableNPCServiceException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(attacker, "attacker");

		log.debug("{} is being attacked by {}", npc, attacker);

		final NPCTemplate template = npc.getTemplate();
		if (!template.isAttackable()) {
			throw new NotAttackableNPCServiceException();
		}

		attackService.attack(attacker, npc);
	}

	/**
	 * @param npc
	 *            the {@link NPC}
	 * @return the {@link NPCController} for the given {@link NPC}
	 */
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
