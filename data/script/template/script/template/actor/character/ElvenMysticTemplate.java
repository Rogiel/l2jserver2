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

public class ElvenMysticTemplate extends AbstractElfCharacterTemplate {
	@Inject
	public ElvenMysticTemplate(CharacterTemplateIDProvider provider) {
		super(provider.createID(CharacterClass.ELVEN_MYSTIC.id), CharacterClass.ELVEN_MYSTIC,	Point.fromXYZ(46182, 41198, -3440));
		
		this.hpBase = 104.000;
		this.hpAdd = 15.570;
		this.hpMultiplier = 0.37;
		this.mpBase = 40.000;
		this.mpAdd = 7.380;
		this.mpMultiplier = 0.14;
		this.cpBase = 52.000;
		this.cpAdd = 7.840;
		this.cpMultiplier = 0.22;
		this.minimumLevel = 1;
		
		// ATTRIBUTES
		attributes.intelligence = 37;
		attributes.strength = 21;
		attributes.concentration = 25;
		attributes.mentality = 40;
		attributes.dexterity = 24;
		attributes.witness = 23;
		attributes.physicalAttack = 3;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 54;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 30;
		attributes.criticalChance = 41;
		attributes.evasionChance = 30;
		attributes.runSpeed = 122;
		attributes.walkSpeed = 122;
		attributes.maxWeigth = 62400;
		attributes.craft = false;
		
		this.maleCollisionRadius = 7.5;
		this.maleCollisionHeight = 24.0;
		this.femaleCollisionRadius = 7.5;
		this.femaleCollisionHeight = 23.0;
	}
	
	protected ElvenMysticTemplate(CharacterTemplateID id,
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
