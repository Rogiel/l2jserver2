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

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.server.CharacterInformationExtraPacket;
import com.l2jserver.game.net.packet.server.CharacterInformationPacket;
import com.l2jserver.game.net.packet.server.CharacterTeleportPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterState;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.model.world.npc.event.NPCSpawnEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportedEvent;
import com.l2jserver.model.world.player.event.PlayerTeleportingEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.dimensional.Point;

/**
 * Default implementation for {@link SpawnService}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class })
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

	@Inject
	public SpawnServiceImpl(WorldService worldService,
			WorldEventDispatcher eventDispatcher, NetworkService networkService) {
		this.worldService = worldService;
		this.eventDispatcher = eventDispatcher;
		this.networkService = networkService;
	}

	@Override
	public void spawn(PositionableObject object, Point point)
			throws SpawnPointNotFoundServiceException {
		Preconditions.checkNotNull(object, "object");
		// sanitize
		if (point == null)
			// retrieving stored point
			point = object.getPoint();
		if (point == null) {
			// not point send and no point stored, aborting
			throw new SpawnPointNotFoundServiceException();
		}

		// set the spawning point
		object.setPoint(point);
		// register object in the world
		if (!worldService.add(object))
			// TODO this should throw an exception
			// object was already in world
			return;

		// create the SpawnEvent
		SpawnEvent event = null;
		if (object instanceof NPC) {
			final NPC npc = (NPC) object;
			event = new NPCSpawnEvent(npc, point);
		} else if (object instanceof L2Character) {
			event = null;
		}

		// TODO throw an exception if event is null
		if (event != null)
			// dispatch spawn event
			eventDispatcher.dispatch(event);
		// remember: broadcasting is done through events!
	}

	@Override
	public void teleport(Player player, Coordinate coordinate)
			throws CharacterAlreadyTeleportingServiceException {
		Preconditions.checkNotNull(player, "player");
		Preconditions.checkNotNull(coordinate, "coordinate");
		if (player instanceof L2Character) {
			if (((L2Character) player).isTeleporting())
				throw new CharacterAlreadyTeleportingServiceException();

			final Lineage2Connection conn = networkService
					.discover((CharacterID) player.getID());
			if (conn == null)
				// TODO throw an exception here
				return;
			conn.write(new CharacterTeleportPacket(conn.getCharacter(),
					coordinate.toPoint()));
			((L2Character) player).setState(CharacterState.TELEPORTING);
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
		final Lineage2Connection conn = networkService.discover(id);

		if (!character.isTeleporting())
			throw new CharacterNotTeleportingServiceException();

		character.setState(null);
		character.setPoint(character.getTargetLocation());

		eventDispatcher.dispatch(new PlayerTeleportedEvent(character, character
				.getTargetLocation()));

		character.setTargetLocation(null);

		conn.write(new CharacterInformationPacket(character));
		conn.write(new CharacterInformationExtraPacket(character));
	}

	@Override
	public void scheduleRespawn(PositionableObject object) {
		Preconditions.checkNotNull(object, "object");
		// TODO Auto-generated method stub

	}

	@Override
	public void unspawn(PositionableObject object) {
		Preconditions.checkNotNull(object, "object");
		// TODO Auto-generated method stub

	}
}
