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
package com.l2jserver.model.world.character.event;

import java.util.Date;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.Player;
import com.l2jserver.model.world.WorldObject;

/**
 * Event triggered once a character logs-in.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterEnterWorldEvent implements CharacterEvent {
	/**
	 * The character that is logging in
	 */
	private final L2Character character;
	/**
	 * The time that this character has logged in
	 */
	private final Date date;

	/**
	 * Creates a new instance
	 * 
	 * @param character
	 *            the character
	 * @param date
	 *            the login date
	 */
	public CharacterEnterWorldEvent(L2Character character, Date date) {
		this.character = character;
		this.date = date;
	}

	/**
	 * Creates a new instance. Login date is set to now.
	 * 
	 * @param character
	 *            the character
	 */
	public CharacterEnterWorldEvent(L2Character character) {
		this(character, new Date());
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	@Override
	public Player getPlayer() {
		return character;
	}

	@Override
	public Actor getActor() {
		return character;
	}

	@Override
	public WorldObject getObject() {
		return character;
	}

	@Override
	public L2Character getCharacter() {
		return character;
	}

	@Override
	public ObjectID<?>[] getDispatchableObjects() {
		return new ObjectID<?>[] { character.getID() };
	}
}
