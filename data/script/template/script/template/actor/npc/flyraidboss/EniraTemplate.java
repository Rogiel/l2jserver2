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
package script.template.actor.npc.flyraidboss;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.FlyRaidBossNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class EniraTemplate extends FlyRaidBossNPCTemplate {
	public static final int ID = 25625;

	@Inject
	protected EniraTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Enira";
		this.serverSideName = false;
		this.title = "Banshee Queen";
		this.serverSideTitle = false;
		this.collisionRadius = 11.00;
		this.collisionHeight = 33.50;
		this.level = 80;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 40;
		this.maxHP = 325715.957207836000000;
		this.maxMP = 1846.800000000000000;
		this.hpRegeneration = 34.943555629168600;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 3745533;
		this.sp = 1082907;
		this.aggressive = false;
		this.rightHand = null;
		this.leftHand = null;
		this.enchantLevel = 0;
		this.targetable = true;
		this.showName = true;
		this.dropHerbGroup = 0;
		this.baseAttributes = true;
		
		attributes.intelligence = 76;
		attributes.strength = 60;
		attributes.concentration = 57;
		attributes.mentality = 80;
		attributes.dexterity = 73;
		attributes.witness = 70;
		attributes.physicalAttack = 643.03355;
		attributes.magicalAttack = 132.11642;
		attributes.physicalDefense = 1005.72821;
		attributes.magicalDefense = 490.63058;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 60.00000;
		attributes.runSpeed = 300.00000;
	}
}
