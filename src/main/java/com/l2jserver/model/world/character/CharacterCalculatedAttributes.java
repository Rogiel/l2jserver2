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
package com.l2jserver.model.world.character;

import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.ActorAttributes;

/**
 * This {@link ActorAttributes} implementation calculates the <b>real</b>
 * character attributes based on it's {@link CharacterTemplate} and active
 * buffs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterCalculatedAttributes implements ActorAttributes {
	/**
	 * The character
	 */
	private final L2Character character;
	/**
	 * The base attributes (from {@link CharacterTemplate})
	 */
	private final ActorAttributes baseAttributes;

	public CharacterCalculatedAttributes(L2Character character) {
		this.character = character;
		this.baseAttributes = this.character.getBaseAttributes();
	}

	@Override
	public int getIntelligence() {
		return baseAttributes.getIntelligence();
	}

	@Override
	public int getStrength() {
		return baseAttributes.getStrength();
	}

	@Override
	public int getConcentration() {
		return baseAttributes.getConcentration();
	}

	@Override
	public int getMentality() {
		return baseAttributes.getMentality();
	}

	@Override
	public int getDexterity() {
		return baseAttributes.getDexterity();
	}

	@Override
	public int getWitness() {
		return baseAttributes.getWitness();
	}

	@Override
	public double getPhysicalAttack() {
		return baseAttributes.getPhysicalAttack();
	}

	@Override
	public double getMagicalAttack() {
		return baseAttributes.getMagicalAttack();
	}

	@Override
	public double getPhysicalDefense() {
		return baseAttributes.getPhysicalDefense();
	}

	@Override
	public double getMagicalDefense() {
		return baseAttributes.getMagicalDefense();
	}

	@Override
	public int getAttackSpeed() {
		return baseAttributes.getAttackSpeed();
	}

	@Override
	public int getCastSpeed() {
		return baseAttributes.getCastSpeed();
	}

	@Override
	public int getAccuracy() {
		return baseAttributes.getAccuracy();
	}

	@Override
	public int getCriticalChance() {
		return baseAttributes.getCriticalChance();
	}

	@Override
	public int getEvasionChance() {
		return baseAttributes.getEvasionChance();
	}

	@Override
	public double getRunSpeed() {
		return baseAttributes.getRunSpeed();
	}

	@Override
	public double getWalkSpeed() {
		return baseAttributes.getWalkSpeed();
	}

	@Override
	public int getMaxWeigth() {
		return baseAttributes.getMaxWeigth();
	}

	@Override
	public boolean canCraft() {
		return baseAttributes.canCraft();
	}

}
