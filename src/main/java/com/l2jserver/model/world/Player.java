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
package com.l2jserver.model.world;

import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.capability.Playable;
import com.l2jserver.model.world.capability.Teleportable;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * {@link Player} is any object that can be controlled by the player. The most
 * common implementation is {@link L2Character}.
 * 
 * @author Rogiel
 */
public abstract class Player extends AbstractActor implements Playable, Actor,
		Teleportable {
	@Override
	public void teleport(Coordinate coordinate) {
		// final PlayerTeleportEvent event = new PlayerTeleportEvent(this,
		// coordinate);
		// this.setPosition(coordinate);
		// event.dispatch();
	}
}
