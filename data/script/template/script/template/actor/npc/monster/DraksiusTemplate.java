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
public class DraksiusTemplate extends MonsterNPCTemplate {
	public static final int ID = 25601;

	@Inject
	protected DraksiusTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Draksius";
		this.serverSideName = false;
		this.title = "Darion's Challenger";
		this.serverSideTitle = false;
		this.collisionRadius = 13.00;
		this.collisionHeight = 47.20;
		this.level = 82;
		this.sex = ActorSex.MALE;
		this.attackRange = 80;
		this.maxHP = 270971.339441210000000;
		this.maxMP = 1917.000000000000000;
		this.hpRegeneration = 44.045540472666300;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 1463241;
		this.sp = 575349;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(6722);
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
		attributes.physicalAttack = 5282.78321;
		attributes.magicalAttack = 3712.93728;
		attributes.physicalDefense = 1900.79743;
		attributes.magicalDefense = 1390.92105;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 35.00000;
		attributes.runSpeed = 185.00000;
	}
}
