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
import com.l2jserver.model.world.Actor.ActorRace;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.stat.Stats;
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

	protected double hpBase;
	protected double hpAdd;
	protected double hpMultiplier;

	protected double mpBase;
	protected double mpAdd;
	protected double mpMultiplier;

	protected double cpBase;
	protected double cpAdd;
	protected double cpMultiplier;

	protected int minimumLevel;

	protected Integer attackDamage = null;
	protected AttackType attackType;

	public enum AttackType {
		FIST;
	}

	/**
	 * The collision radius for male instances
	 */
	protected double maleCollisionRadius = 0;
	/**
	 * The collision height for male instances
	 */
	protected double maleCollisionHeight = 0;
	/**
	 * The collision radius for female instances
	 */
	protected double femaleCollisionRadius = 0;
	/**
	 * The collision height for female instances
	 */
	protected double femaleCollisionHeight = 0;

	protected CharacterTemplate(CharacterTemplateID id,
			CharacterClass characterClass, Point spawnLocation) {
		super(id);
		this.characterClass = characterClass;
		this.spawnLocation = spawnLocation;
	}

	@Override
	public L2Character createInstance() {
		final L2Character character = new L2Character(this.getID());

		character.setRace(getRace());
		character.setCharacterClass(characterClass);
		character.setPoint(spawnLocation);

		return character;
	}

	@Override
	public Stats getTemplateStat() {
		return null;
	}

	/**
	 * @return the race
	 */
	public ActorRace getRace() {
		return characterClass.race;
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

	/**
	 * @return the hpBase
	 */
	public double getHpBase() {
		return hpBase;
	}

	/**
	 * @return the hpAdd
	 */
	public double getHpAdd() {
		return hpAdd;
	}

	/**
	 * @return the hpMultiplier
	 */
	public double getHpMultiplier() {
		return hpMultiplier;
	}

	/**
	 * @return the mpBase
	 */
	public double getMpBase() {
		return mpBase;
	}

	/**
	 * @return the mpAdd
	 */
	public double getMpAdd() {
		return mpAdd;
	}

	/**
	 * @return the mpMultiplier
	 */
	public double getMpMultiplier() {
		return mpMultiplier;
	}

	/**
	 * @return the cpBase
	 */
	public double getCpBase() {
		return cpBase;
	}

	/**
	 * @return the cpAdd
	 */
	public double getCpAdd() {
		return cpAdd;
	}

	/**
	 * @return the cpMultiplier
	 */
	public double getCpMultiplier() {
		return cpMultiplier;
	}

	/**
	 * @return the minimumLevel
	 */
	public int getMinimumLevel() {
		return minimumLevel;
	}

	/**
	 * @return the attackDamage
	 */
	public Integer getAttackDamage() {
		return attackDamage;
	}

	/**
	 * @return the attackType
	 */
	public AttackType getAttackType() {
		return attackType;
	}

	/**
	 * @return the male collision radius
	 */
	public double getMaleCollisionRadius() {
		return maleCollisionRadius;
	}

	/**
	 * @return the male collision height
	 */
	public double getMaleCollisionHeight() {
		return maleCollisionHeight;
	}

	/**
	 * @return the female collision radius
	 */
	public double getFemaleCollisionRadius() {
		return femaleCollisionRadius;
	}

	/**
	 * @return the female collision height
	 */
	public double getFemaleCollisionHeight() {
		return femaleCollisionHeight;
	}

	@Override
	public CharacterTemplateID getID() {
		return (CharacterTemplateID) super.getID();
	}
}
