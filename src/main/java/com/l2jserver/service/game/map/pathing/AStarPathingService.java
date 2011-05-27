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
package com.l2jserver.service.game.map.pathing;

import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.util.geometry.Point3D;

/**
 * This {@link PathingService} implementation uses the A* algorithm to determine
 * the best path possible.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ CharacterService.class, WorldService.class })
public class AStarPathingService extends AbstractService implements
		PathingService {
	@Override
	public Path findPath(PositionableObject object, Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

}
