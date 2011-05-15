
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

public class ShillienTemplarTemplate extends ShillienKnightTemplate {
	@Inject
	public ShillienTemplarTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.SHILLIEN_TEMPLAR.id),
				CharacterClass.SHILLIEN_TEMPLAR,
				// ATTRIBUTES
				25,// INT
				41,// STR
				32,// CON
				26,// MEN
				34,// DEX
				12,// WIT
				4,// physical attack
				6,// magical attack
				80,// physical def
				41,// magical def
				300,// attack speed
				333,// cast speed
				35,// accuracy
				45,// critical
				35,// evasion
				122,// move speed
				69000,// max inventory weight
				false,// can craft
				Point.fromXYZ(28377, 10916, -4224)// spawn location
		);
	}

	protected ShillienTemplarTemplate(CharacterTemplateID id,
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
