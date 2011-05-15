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
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.L2Character;

/**
 * Template for {@link NPC}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class NPCTemplate extends ActorTemplate<L2Character> {
	protected NPCTemplate(CharacterTemplateID id, Race race, int intelligence,
			int strength, int concentration, int mentality, int dexterity,
			int witness, int physicalAttack, int magicalAttack,
			int physicalDefense, int magicalDefense, int attackSpeed,
			int castSpeed, int accuracy, int criticalChance, int evasionChance,
			int moveSpeed, int maxWeigth, boolean craft) {
		super(id, race, intelligence, strength, concentration, mentality,
				dexterity, witness, physicalAttack, magicalAttack,
				physicalDefense, magicalDefense, attackSpeed, castSpeed,
				accuracy, criticalChance, evasionChance, moveSpeed, maxWeigth,
				craft);
	}

	@Override
	public L2Character createInstance() {
		return null;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	@Override
	public NPCTemplateID getID() {
		return (NPCTemplateID) super.getID();
	}
}
