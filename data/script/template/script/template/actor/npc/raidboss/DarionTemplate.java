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
package script.template.actor.npc.raidboss;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.RaidBossNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DarionTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25603;

	@Inject
	protected DarionTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Darion";
		this.serverSideName = false;
		this.title = "";
		this.serverSideTitle = false;
		this.collisionRadius = 50.00;
		this.collisionHeight = 81.22;
		this.level = 84;
		this.sex = ActorSex.MALE;
		this.attackRange = 80;
		this.maxHP = 970000.000000000000000;
		this.maxMP = 1917.000000000000000;
		this.hpRegeneration = 100.000000000000000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 4712138;
		this.sp = 598881;
		this.aggressive = false;
		this.rightHand = null;
		this.leftHand = itemProvider.createID(10548);
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
		attributes.physicalAttack = 3152.92775;
		attributes.magicalAttack = 1289.68198;
		attributes.physicalDefense = 1127.29373;
		attributes.magicalDefense = 549.93387;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 44.00000;
		attributes.runSpeed = 220.00000;
	}
}
