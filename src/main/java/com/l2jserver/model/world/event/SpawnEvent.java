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
package com.l2jserver.model.world.event;

import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.service.game.world.event.WorldEvent;
import com.l2jserver.util.dimensional.Point;

/**
 * Event for objects spawning
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface SpawnEvent extends WorldEvent {
	@Override
	Spawnable getObject();

	/**
	 * @return the spawning point
	 */
	Point getPoint();
}