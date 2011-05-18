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

public class DominatorTemplate extends OverlordTemplate {
	@Inject
	public DominatorTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.DOMINATOR.id), CharacterClass.DOMINATOR,	Point.fromXYZ(-56682, -113730, -690));
		// ATTRIBUTES
		attributes.intelligence = 31;
		attributes.strength = 27;
		attributes.concentration = 31;
		attributes.mentality = 42;
		attributes.dexterity = 24;
		attributes.witness = 15;
		attributes.physicalAttack = 3;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 54;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 30;
		attributes.criticalChance = 41;
		attributes.evasionChance = 30;
		attributes.moveSpeed = 121;
		attributes.maxWeigth = 68000;
		attributes.craft = false;
	}
	
	protected DominatorTemplate(CharacterTemplateID id,
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
