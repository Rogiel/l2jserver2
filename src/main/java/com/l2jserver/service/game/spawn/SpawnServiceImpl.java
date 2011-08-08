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
package com.l2jserver.service.game.spawn;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.server.SM_CHAR_INFO;
import com.l2jserver.game.net.packet.server.SM_CHAR_INFO_EXTRA;
import com.l2jserver.game.net.packet.server.SM_TELEPORT;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Actor.ActorState;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.model.world.event.UnspawnEvent;
import com.l2jserver.model.world.npc.event.NPCSpawnEvent;
import com.l2jserver.model.world.npc.event.NPCUnspawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportedEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportingEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.core.threading.AsyncFuture;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.geometry.Point3D;

/**
 * Default implementation for {@link SpawnService}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class, NetworkService.class, ThreadService.class })
public class SpawnServiceImpl extends AbstractService implements SpawnService {
	/**
	 * The {@link WorldService}
	 */
	private final WorldService worldService;
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;
	/**
	 * The {@link NetworkService}
	 */
	private final NetworkService networkService;
	/**
	 * The {@link ThreadService}
	 */
	private final ThreadService threadService;

	@Inject
	public SpawnServiceImpl(WorldService worldService,
			WorldEventDispatcher eventDispatcher,
			NetworkService networkService, ThreadService threadService) {
		this.worldService = worldService;
		this.eventDispatcher = eventDispatcher;
		this.networkService = networkService;
		this.threadService = threadService;
	}

	@Override
	public void spawn(PositionableObject object, Point3D point)
			throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException {
		Preconditions.checkNotNull(object, "object");
		// only set the new position if needed, this could cause a lot of
		// database updates if update is done unnecessarily
		boolean updatePoint = true;
		// sanitize
		if (point == null) {
			// retrieving stored point
			point = object.getPoint();
			updatePoint = false;
		}
		if (point == null) {
			// not point in argument and no point stored, aborting
			throw new SpawnPointNotFoundServiceException();
		}

		// set the spawning point
		if (updatePoint)
			object.setPoint(point);
		// reset actor state
		if (object instanceof Actor) {
			((Actor) object).setState(null);
		}
		// register object in the world
		if (!worldService.add(object))
			throw new AlreadySpawnedServiceException();

		// create the SpawnEvent
		SpawnEvent event = null;
		if (object instanceof NPC) {
			final NPC npc = (NPC) object;
			event = new NPCSpawnEvent(npc, point);
		} else if (object instanceof L2Character) {
			// TODO character spawn event
			event = null;
		}

		// TODO throw an exception if event is null
		if (event != null)
			// dispatch spawn event
			eventDispatcher.dispatch(event);
		// remember: broadcasting is done through events!
	}

	@Override
	public <T extends PositionableObject> AsyncFuture<T> spawn(final T object,
			final Point3D point, long time, TimeUnit unit) {
		Preconditions.checkNotNull(object, "object");
		Preconditions.checkArgument(time > 0, "time < 0");
		Preconditions.checkNotNull(unit, "unit");
		return threadService.async(time, unit, new Callable<T>() {
			@Override
			public T call() throws Exception {
				spawn(object, point);
				return object;
			}
		});
	}

	@Override
	public void unspawn(PositionableObject object)
			throws NotSpawnedServiceException {
		Preconditions.checkNotNull(object, "object");

		if (object.getPoint() == null)
			throw new NotSpawnedServiceException();

		// unregister object in the world
		if (!worldService.remove(object))
			throw new NotSpawnedServiceException();

		final Point3D point = object.getPoint();

		// create the SpawnEvent
		UnspawnEvent event = null;
		if (object instanceof NPC) {
			final NPC npc = (NPC) object;
			event = new NPCUnspawnEvent(npc, point);
		} else if (object instanceof L2Character) {
			// TODO character unspawn event
			event = null;
		}

		// TODO throw an exception if event is null
		if (event != null)
			// dispatch unspawn event
			eventDispatcher.dispatch(event);
	}

	@Override
	public <T extends PositionableObject> AsyncFuture<T> unspawn(
			final T object, long time, TimeUnit unit) {
		Preconditions.checkNotNull(object, "object");
		Preconditions.checkArgument(time > 0, "time <= 0");
		Preconditions.checkNotNull(unit, "unit");
		return threadService.async(time, unit, new Callable<T>() {
			@Override
			public T call() throws Exception {
				unspawn(object);
				return object;
			}
		});
	}

	@Override
	public void teleport(Player player, Coordinate coordinate)
			throws CharacterAlreadyTeleportingServiceException {
		Preconditions.checkNotNull(player, "player");
		Preconditions.checkNotNull(coordinate, "coordinate");
		if (player instanceof L2Character) {
			if (((L2Character) player).isTeleporting())
				throw new CharacterAlreadyTeleportingServiceException();

			final Lineage2Client conn = networkService
					.discover((CharacterID) player.getID());
			if (conn == null)
				// TODO throw an exception here
				return;
			conn.write(new SM_TELEPORT(conn.getCharacter(), coordinate
					.toPoint()));
			((L2Character) player).setState(ActorState.TELEPORTING);
			((L2Character) player).setTargetLocation(coordinate.toPoint());
		} else {
			player.setPosition(coordinate);
		}
		// dispatch teleport event
		eventDispatcher.dispatch(new PlayerTeleportingEvent(player, coordinate
				.toPoint()));
		// remember: broadcasting is done through events!
	}

	@Override
	public void finishTeleport(L2Character character)
			throws CharacterNotTeleportingServiceException {
		Preconditions.checkNotNull(character, "character");
		final CharacterID id = character.getID();
		final Lineage2Client conn = networkService.discover(id);

		if (!character.isTeleporting())
			throw new CharacterNotTeleportingServiceException();

		character.setState(null);
		character.setPoint(character.getTargetLocation());

		eventDispatcher.dispatch(new PlayerTeleportedEvent(character, character
				.getTargetLocation()));

		character.setTargetLocation(null);

		conn.write(new SM_CHAR_INFO(character));
		conn.write(new SM_CHAR_INFO_EXTRA(character));
	}
}
