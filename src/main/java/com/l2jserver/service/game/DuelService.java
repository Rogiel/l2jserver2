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

import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;

/**
 * This service handles duel events.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface DuelService extends Service {
	/**
	 * Start the duel between the given two characters
	 * 
	 * @param character1
	 *            the first character
	 * @param character2
	 *            the second character
	 */
	void start(L2Character character1, L2Character character2);

	/**
	 * Finishes the duel between the given two characters.<br>
	 * <b>This must be called before the character is revived.</b>
	 * 
	 * @param character1
	 *            the first character
	 * @param character2
	 *            the second character
	 */
	void stop(L2Character character1, L2Character character2);
}
