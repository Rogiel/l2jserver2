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

public class DuelistTemplate extends GladiatorTemplate {
	@Inject
	public DuelistTemplate(CharacterTemplateIDProvider provider) {
		super(provider.createID(CharacterClass.DUELIST.id), CharacterClass.DUELIST,	Point.fromXYZ(-71338, 258271, -3104));
		
		this.hpBase = 3061.800;
		this.hpAdd = 63.080;
		this.hpMultiplier = 0.37;
		this.mpBase = 1155.600;
		this.mpAdd = 24.900;
		this.mpMultiplier = 0.14;
		this.cpBase = 2755.600;
		this.cpAdd = 56.770;
		this.cpMultiplier = 0.22;
		this.minimumLevel = 76;
		
		// ATTRIBUTES
		attributes.intelligence = 21;
		attributes.strength = 40;
		attributes.concentration = 43;
		attributes.mentality = 25;
		attributes.dexterity = 30;
		attributes.witness = 11;
		attributes.physicalAttack = 4;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 80;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 33;
		attributes.criticalChance = 44;
		attributes.evasionChance = 33;
		attributes.runSpeed = 115;
		attributes.walkSpeed = 115;
		attributes.maxWeigth = 81900;
		attributes.craft = false;
		
		this.maleCollisionRadius = 9.0;
		this.maleCollisionHeight = 23.0;
		this.femaleCollisionRadius = 8.0;
		this.femaleCollisionHeight = 23.5;
	}
	
	protected DuelistTemplate(CharacterTemplateID id,
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
