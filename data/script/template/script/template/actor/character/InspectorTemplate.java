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

public class InspectorTemplate extends WarderTemplate {
	@Inject
	public InspectorTemplate(CharacterTemplateIDProvider provider) {
		super(provider.createID(CharacterClass.INSPECTOR.id), CharacterClass.INSPECTOR,	Point.fromXYZ(-125533, 38114, 1142));
		
		this.hpBase = 1135.870;
		this.hpAdd = 53.300;
		this.hpMultiplier = 0.37;
		this.mpBase = 493.480;
		this.mpAdd = 26.000;
		this.mpMultiplier = 0.14;
		this.cpBase = 567.935;
		this.cpAdd = 26.650;
		this.cpMultiplier = 0.22;
		this.minimumLevel = 40;
		
		// ATTRIBUTES
		attributes.intelligence = 28;
		attributes.strength = 39;
		attributes.concentration = 30;
		attributes.mentality = 27;
		attributes.dexterity = 35;
		attributes.witness = 11;
		attributes.physicalAttack = 4;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 80;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 35;
		attributes.criticalChance = 45;
		attributes.evasionChance = 35;
		attributes.runSpeed = 122;
		attributes.walkSpeed = 122;
		attributes.maxWeigth = 87000;
		attributes.craft = false;
		
		this.maleCollisionRadius = 8.0;
		this.maleCollisionHeight = 25.2;
		this.femaleCollisionRadius = 7.0;
		this.femaleCollisionHeight = 22.6;
	}
	
	protected InspectorTemplate(CharacterTemplateID id,
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
