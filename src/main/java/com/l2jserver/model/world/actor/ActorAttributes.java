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
package com.l2jserver.model.world.actor;

import com.l2jserver.model.template.ActorTemplate;

/**
 * Defines attributes of the character. Implementations can use an static value
 * (i.e. from {@link ActorTemplate}) or can use an calculator to define values,
 * composed from many attributes objects.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public interface ActorAttributes {
	/**
	 * @return the intelligence
	 */
	public int getIntelligence();

	/**
	 * @return the strength
	 */
	public int getStrength();

	/**
	 * @return the concentration
	 */
	public int getConcentration();

	/**
	 * @return the mentality
	 */
	public int getMentality();

	/**
	 * @return the dexterity
	 */
	public int getDexterity();

	/**
	 * @return the witness
	 */
	public int getWitness();

	/**
	 * @return the physicalAttack
	 */
	public double getPhysicalAttack();

	/**
	 * @return the magicalAttack
	 */
	public double getMagicalAttack();

	/**
	 * @return the physicalDefense
	 */
	public double getPhysicalDefense();

	/**
	 * @return the magicalDefense
	 */
	public double getMagicalDefense();

	/**
	 * @return the attackSpeed
	 */
	public int getAttackSpeed();

	/**
	 * @return the castSpeed
	 */
	public int getCastSpeed();

	/**
	 * @return the accuracy
	 */
	public int getAccuracy();

	/**
	 * @return the criticalChance
	 */
	public int getCriticalChance();

	/**
	 * @return the evasionChance
	 */
	public int getEvasionChance();

	/**
	 * @return the run speed
	 */
	public double getRunSpeed();
	
	/**
	 * @return the walking speed
	 */
	public double getWalkSpeed();

	/**
	 * @return the maxWeigth
	 */
	public int getMaxWeigth();

	/**
	 * @return the craft
	 */
	public boolean canCraft();
}
