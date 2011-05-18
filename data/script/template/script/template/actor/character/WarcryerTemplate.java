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
package script.template.actor.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class WarcryerTemplate extends OrcShamanTemplate {
	@Inject
	public WarcryerTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.WARCRYER.id),
				CharacterClass.WARCRYER,
				// ATTRIBUTES
				31,// INT
				27,// STR
				31,// CON
				42,// MEN
				24,// DEX
				15,// WIT
				3,// physical attack
				6,// magical attack
				54,// physical def
				41,// magical def
				300,// attack speed
				333,// cast speed
				30,// accuracy
				41,// critical
				30,// evasion
				121,// move speed
				68000,// max inventory weight
				false,// can craft
				Point.fromXYZ(-56682, -113730, -690)// spawn location
		);
	}

	protected WarcryerTemplate(CharacterTemplateID id,
			CharacterClass characterClass, int intelligence, int strength,
			int concentration, int mentality, int dexterity, int witness,
			int physicalAttack, int magicalAttack, int physicalDefense,
			int magicalDefense, int attackSpeed, int castSpeed, int accuracy,
			int criticalChance, int evasionChance, int moveSpeed,
			int maxWeigth, boolean craft, Point spawnLocation) {
		super(id, characterClass, intelligence, strength, concentration,
				mentality, dexterity, witness, physicalAttack, magicalAttack,
				physicalDefense, magicalDefense, attackSpeed, castSpeed,
				accuracy, criticalChance, evasionChance, moveSpeed, maxWeigth,
				craft, spawnLocation);
	}

	@Override
	public L2Character create() {
		final L2Character character = super.create();
		// TODO register skills
		return character;
	}
}
