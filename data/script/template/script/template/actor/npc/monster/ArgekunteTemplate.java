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
public class ArgekunteTemplate extends MonsterNPCTemplate {
	public static final int ID = 25635;

	@Inject
	protected ArgekunteTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Argekunte";
		this.serverSideName = false;
		this.title = "Vacuous Soul";
		this.serverSideTitle = false;
		this.collisionRadius = 24.00;
		this.collisionHeight = 40.80;
		this.level = 81;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 436294.588865472000000;
		this.maxMP = 1812.000000000000000;
		this.hpRegeneration = 186.305572541410000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 3004658;
		this.sp = 406483;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(13985);
		this.leftHand = itemProvider.createID(13986);
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
		attributes.physicalAttack = 1816.91541;
		attributes.magicalAttack = 1073.23051;
		attributes.physicalDefense = 1092.54760;
		attributes.magicalDefense = 532.98247;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 80.00000;
		attributes.runSpeed = 200.00000;
	}
}
