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
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.service.Service;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * This service manages {@link L2Character} instances
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface CharacterService extends Service {
	/**
	 * Perform all operations required to this character join the world
	 * 
	 * @param character
	 *            the character
	 */
	void enterWorld(L2Character character);

	/**
	 * Perform all operations required to this character leave the world
	 * 
	 * @param character
	 *            the character
	 */
	void leaveWorld(L2Character character);

	/**
	 * Set the target of this <tt>character</tt>
	 * 
	 * @param character
	 *            the character
	 * @param actor
	 *            the targeted actor
	 */
	void target(L2Character character, Actor actor);

	/**
	 * Moves the given <tt>character</tt> to <tt>coordinate</tt>
	 * 
	 * @param character
	 *            the character
	 * @param coordinate
	 *            the coordinate
	 */
	void move(L2Character character, Coordinate coordinate);

	/**
	 * Set the character to walking mode
	 * 
	 * @param character
	 *            the character
	 */
	void walk(L2Character character);

	/**
	 * Set the character to run mode
	 * 
	 * @param character
	 *            the character
	 */
	void run(L2Character character);
}
