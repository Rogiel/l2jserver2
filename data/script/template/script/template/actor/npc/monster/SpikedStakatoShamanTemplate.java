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
public class SpikedStakatoShamanTemplate extends MonsterNPCTemplate {
	public static final int ID = 22121;

	@Inject
	protected SpikedStakatoShamanTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Spiked Stakato Shaman";
		this.serverSideName = false;
		this.title = "";
		this.serverSideTitle = false;
		this.collisionRadius = 24.00;
		this.collisionHeight = 43.60;
		this.level = 78;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 40;
		this.maxHP = 2937.983805600000000;
		this.maxMP = 1708.296000000000000;
		this.hpRegeneration = 8.670000000000000;
		this.mpRegeneration = 3.060000000000000;
		this.experience = 30969;
		this.sp = 3310;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(221);
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
		attributes.physicalAttack = 912.65371;
		attributes.magicalAttack = 635.68866;
		attributes.physicalDefense = 348.20250;
		attributes.magicalDefense = 254.79948;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 8;
		attributes.walkSpeed = 25.00000;
		attributes.runSpeed = 170.00000;
	}
}
