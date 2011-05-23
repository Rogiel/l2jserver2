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
public class GiantMarpanakTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25162;

	@Inject
	protected GiantMarpanakTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Giant Marpanak";
		this.serverSideName = false;
		this.title = "Raid Boss";
		this.serverSideTitle = false;
		this.collisionRadius = 42.00;
		this.collisionHeight = 91.00;
		this.level = 60;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 614249.151468482000000;
		this.maxMP = 1036.800000000000000;
		this.hpRegeneration = 253.244358973511000;
		this.mpRegeneration = 2.400000000000000;
		this.experience = 4154684;
		this.sp = 587467;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(3937);
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
		attributes.physicalAttack = 2937.05045;
		attributes.magicalAttack = 1851.60109;
		attributes.physicalDefense = 742.95024;
		attributes.magicalDefense = 362.44000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 15.00000;
		attributes.runSpeed = 180.00000;
	}
}
