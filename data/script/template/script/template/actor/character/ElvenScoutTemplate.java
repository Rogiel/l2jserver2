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
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class ElvenScoutTemplate extends ElvenFighterTemplate {
	@Inject
	public ElvenScoutTemplate(CharacterTemplateIDProvider factory) {
		super(factory.createID(CharacterClass.ELVEN_SCOUT.id), CharacterClass.ELVEN_SCOUT,	Point.fromXYZ(45978, 41196, -3440));
		// ATTRIBUTES
		attributes.intelligence = 23;
		attributes.strength = 36;
		attributes.concentration = 36;
		attributes.mentality = 26;
		attributes.dexterity = 35;
		attributes.witness = 14;
		attributes.physicalAttack = 4;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 80;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 36;
		attributes.criticalChance = 46;
		attributes.evasionChance = 36;
		attributes.moveSpeed = 125;
		attributes.maxWeigth = 73000;
		attributes.craft = false;
	}
	
	protected ElvenScoutTemplate(CharacterTemplateID id,
			CharacterClass characterClass, Point spawnLocation) {
		super(id, characterClass, spawnLocation);
	}

	@Override
	public L2Character create() {
		final L2Character character = super.create();
		// TODO register skills
		return character;
	}
}
