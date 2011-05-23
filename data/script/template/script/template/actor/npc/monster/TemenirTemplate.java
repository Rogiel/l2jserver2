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
package script.template.actor.npc.monster;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.MonsterNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TemenirTemplate extends MonsterNPCTemplate {
	public static final int ID = 25600;

	@Inject
	protected TemenirTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Temenir";
		this.serverSideName = false;
		this.title = "Darion's Challenger";
		this.serverSideTitle = false;
		this.collisionRadius = 25.00;
		this.collisionHeight = 37.50;
		this.level = 82;
		this.sex = ActorSex.MALE;
		this.attackRange = 80;
		this.maxHP = 254046.904193716000000;
		this.maxMP = 1917.000000000000000;
		this.hpRegeneration = 55.059368879847200;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 1404180;
		this.sp = 488611;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(10277);
		this.leftHand = null;
		this.enchantLevel = 0;
		this.targetable = true;
		this.showName = true;
		this.dropHerbGroup = 0;
		this.baseAttributes = true;
		
		attributes.intelligence = 21;
		attributes.strength = 40;
		attributes.concentration = 43;
		attributes.mentality = 20;
		attributes.dexterity = 30;
		attributes.witness = 20;
		attributes.physicalAttack = 5869.75912;
		attributes.magicalAttack = 4125.48586;
		attributes.physicalDefense = 1520.63794;
		attributes.magicalDefense = 834.55263;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 68.00000;
		attributes.runSpeed = 180.00000;
	}
}
