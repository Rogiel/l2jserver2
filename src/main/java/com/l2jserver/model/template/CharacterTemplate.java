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
package com.l2jserver.model.template;

import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

/**
 * Template for {@link L2Character}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class CharacterTemplate extends ActorTemplate<L2Character> {
	/**
	 * The character class
	 */
	protected final CharacterClass characterClass;
	/**
	 * The initial location for the character to be spawned
	 */
	protected final Point spawnLocation;

	protected Integer attackDamage = null;
	protected AttackType attackType;

	public enum AttackType {
		FIST;
	}

	protected CharacterTemplate(CharacterTemplateID id,
			CharacterClass characterClass, Point spawnLocation) {
		super(id, characterClass.race);
		this.characterClass = characterClass;
		this.spawnLocation = spawnLocation;
	}

	@Override
	public L2Character createInstance() {
		final L2Character character = new L2Character(attributes);

		character.setRace(race);
		character.setCharacterClass(characterClass);
		character.setPoint(spawnLocation);

		return character;
	}

	/**
	 * @return the characterClass
	 */
	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	/**
	 * @return the initial spawn location
	 */
	public Point getSpawnLocation() {
		return spawnLocation;
	}

	@Override
	public CharacterTemplateID getID() {
		return (CharacterTemplateID) super.getID();
	}
}
