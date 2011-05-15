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
package com.l2jserver.model.world.player;

import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.event.SpawnEvent;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * Event dispatcher once an player has spawned in the world
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PlayerSpawnEvent implements PlayerEvent, SpawnEvent {
	/**
	 * The spawned player
	 */
	private final Player player;
	/**
	 * The spawning coordinate
	 */
	private final Coordinate coordinate;

	/**
	 * Creates a new instance
	 * 
	 * @param player
	 *            the spawned player
	 * @param coordinate
	 *            the spawn coordinate
	 */
	public PlayerSpawnEvent(Player player, Coordinate coordinate) {
		this.player = player;
		this.coordinate = coordinate;
	}

	@Override
	public Spawnable getObject() {
		return player;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Coordinate getCoordinate() {
		return coordinate;
	}

	@Override
	public Actor getActor() {
		return player;
	}

	@Override
	public Listenable<?, ?>[] getDispatchableObjects() {
		return new Listenable<?, ?>[] { player };
	}
}
