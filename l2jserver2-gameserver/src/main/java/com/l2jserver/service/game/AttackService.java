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
package com.l2jserver.service.game;

import com.l2jserver.model.server.AttackHit;
import com.l2jserver.model.world.Actor;
import com.l2jserver.service.Service;
import com.l2jserver.service.core.threading.AsyncFuture;

/**
 * This service handles attacking. It can schedule auto-attack events and also
 * deals the damage.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface AttackService extends Service {
	/**
	 * Schedules an attack command. The attack order will be put in queue and
	 * the will be executed as soon as possible.
	 * 
	 * @param attacker
	 *            the actor attacking <tt>target</tt>
	 * @param target
	 *            the actor receiving the attack from <tt>attacker</tt>
	 * @return the {@link AsyncFuture} that can be used to retrieve
	 *         {@link AttackHit} object, once the attack has been executed.
	 */
	AsyncFuture<AttackHit> attack(Actor attacker, Actor target);
}
