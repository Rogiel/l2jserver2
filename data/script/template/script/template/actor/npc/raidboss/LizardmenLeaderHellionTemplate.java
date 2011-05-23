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
public class LizardmenLeaderHellionTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25170;

	@Inject
	protected LizardmenLeaderHellionTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Lizardmen Leader Hellion";
		this.serverSideName = false;
		this.title = "Raid Boss";
		this.serverSideTitle = false;
		this.collisionRadius = 30.00;
		this.collisionHeight = 63.00;
		this.level = 38;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 82089.052445417200000;
		this.maxMP = 447.960000000000000;
		this.hpRegeneration = 30.872687307300600;
		this.mpRegeneration = 1.800000000000000;
		this.experience = 1957608;
		this.sp = 163199;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(223);
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
		attributes.physicalAttack = 204.55422;
		attributes.magicalAttack = 16.14769;
		attributes.physicalDefense = 445.32861;
		attributes.magicalDefense = 217.24000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 8;
		attributes.walkSpeed = 53.00000;
		attributes.runSpeed = 170.00000;
	}
}
