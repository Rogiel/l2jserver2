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
public class PatriarchKuroborosTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25365;

	@Inject
	protected PatriarchKuroborosTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Patriarch Kuroboros";
		this.serverSideName = false;
		this.title = "Raid Boss";
		this.serverSideTitle = false;
		this.collisionRadius = 12.00;
		this.collisionHeight = 34.50;
		this.level = 26;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 90072.419899490300000;
		this.maxMP = 273.000000000000000;
		this.hpRegeneration = 47.427471472136600;
		this.mpRegeneration = 1.500000000000000;
		this.experience = 2487618;
		this.sp = 84828;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(93);
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
		attributes.physicalAttack = 161.16949;
		attributes.magicalAttack = 20.28816;
		attributes.physicalDefense = 315.08454;
		attributes.magicalDefense = 153.72000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 52.00000;
		attributes.runSpeed = 190.00000;
	}
}
