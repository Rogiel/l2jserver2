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
package com.l2jserver.service.game.castle;

import com.l2jserver.service.Service;

/**
 * This service is responsible for keeping a track on siege events. Once a siege
 * has begun it will perform all operations that need to be executed during a
 * siege. It will also reschedule the next one.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface SiegeService extends Service {

}
