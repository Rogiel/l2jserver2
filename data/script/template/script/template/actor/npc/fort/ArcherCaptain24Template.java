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
package script.template.actor.npc.fort;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.FortCommanderNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ArcherCaptain24Template extends FortCommanderNPCTemplate {
	public static final int ID = 36064;

	@Inject
	protected ArcherCaptain24Template(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Archer Captain";
		this.serverSideName = false;
		this.title = "Archer";
		this.serverSideTitle = false;
		this.collisionRadius = 10.00;
		this.collisionHeight = 23.50;
		this.level = 78;
		this.sex = ActorSex.MALE;
		this.attackRange = 1100;
		this.maxHP = 681761.551881449000000;
		this.maxMP = 1607.400000000000000;
		this.hpRegeneration = 397.497280485560000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 3042;
		this.sp = 0;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(10212);
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
		attributes.physicalAttack = 7149.40064;
		attributes.magicalAttack = 4882.11391;
		attributes.physicalDefense = 532.32098;
		attributes.magicalDefense = 584.29409;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 8;
		attributes.walkSpeed = 60.00000;
		attributes.runSpeed = 120.00000;
	}
}
