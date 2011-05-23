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
public class VarkaSilenosOfficerTemplate extends MonsterNPCTemplate {
	public static final int ID = 18646;

	@Inject
	protected VarkaSilenosOfficerTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Varka Silenos Officer";
		this.serverSideName = false;
		this.title = "";
		this.serverSideTitle = false;
		this.collisionRadius = 18.00;
		this.collisionHeight = 42.50;
		this.level = 73;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 6660.845271940680000;
		this.maxMP = 1540.800000000000000;
		this.hpRegeneration = 17.000000000000000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 37623;
		this.sp = 3932;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(2504);
		this.leftHand = itemProvider.createID(6918);
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
		attributes.physicalAttack = 412.21000;
		attributes.magicalAttack = 140.74335;
		attributes.physicalDefense = 323.78455;
		attributes.magicalDefense = 236.93149;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 1;
		attributes.walkSpeed = 25.00000;
		attributes.runSpeed = 175.00000;
	}
}
