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

public class SoultakerTemplate extends NecromancerTemplate {
	@Inject
	public SoultakerTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.SOULTAKER.id), CharacterClass.SOULTAKER,	Point.fromXYZ(-90890, 248027, -3570));
		// ATTRIBUTES
		attributes.intelligence = 41;
		attributes.strength = 22;
		attributes.concentration = 27;
		attributes.mentality = 39;
		attributes.dexterity = 21;
		attributes.witness = 20;
		attributes.physicalAttack = 3;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 54;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 28;
		attributes.criticalChance = 40;
		attributes.evasionChance = 28;
		attributes.moveSpeed = 120;
		attributes.maxWeigth = 62500;
		attributes.craft = false;
	}
	
	protected SoultakerTemplate(CharacterTemplateID id,
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
