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

public class PhantomSummonerTemplate extends DarkWizardTemplate {
	@Inject
	public PhantomSummonerTemplate(CharacterTemplateIDProvider provider) {
		super(provider.createID(CharacterClass.PHANTOM_SUMMONER.id), CharacterClass.PHANTOM_SUMMONER,	Point.fromXYZ(28295, 11063, -4224));
		
		this.hpBase = 1074.300;
		this.hpAdd = 52.100;
		this.hpMultiplier = 0.37;
		this.mpBase = 478.800;
		this.mpAdd = 26.100;
		this.mpMultiplier = 0.14;
		this.cpBase = 644.500;
		this.cpAdd = 31.300;
		this.cpMultiplier = 0.22;
		this.minimumLevel = 40;
		
		// ATTRIBUTES
		attributes.intelligence = 44;
		attributes.strength = 23;
		attributes.concentration = 24;
		attributes.mentality = 37;
		attributes.dexterity = 23;
		attributes.witness = 19;
		attributes.physicalAttack = 3;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 54;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 29;
		attributes.criticalChance = 41;
		attributes.evasionChance = 29;
		attributes.runSpeed = 122;
		attributes.walkSpeed = 122;
		attributes.maxWeigth = 61000;
		attributes.craft = false;
		
		this.maleCollisionRadius = 7.5;
		this.maleCollisionHeight = 24.0;
		this.femaleCollisionRadius = 7.0;
		this.femaleCollisionHeight = 23.5;
	}
	
	protected PhantomSummonerTemplate(CharacterTemplateID id,
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
