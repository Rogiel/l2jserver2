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
import com.l2jserver.service.Service;
import com.l2jserver.service.game.map.MapService;
import com.l2jserver.util.geometry.Point3D;

/**
 * Service responsible for finding obstacles in the character moving line and
 * tries to create a new path plan in order to avoid those obstacles. This
 * service must work in conjunction with {@link MapService} that will map the
 * entire game world, listing all obstacles and free locations.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface PathingService extends Service {
	/**
	 * Tries to find a path for <tt>object</tt> move to <tt>point</tt>. If the
	 * algorithm could't find any viable path, <tt>null</tt> <b>must</b> be
	 * returned.
	 * 
	 * @param object
	 *            the object moving to <tt>point</tt>
	 * @param point
	 *            the destination point
	 * @return the {@link Path}
	 */
	Path findPath(PositionableObject object, Point3D point);
}
