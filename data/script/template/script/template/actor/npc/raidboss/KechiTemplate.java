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
public class KechiTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25532;

	@Inject
	protected KechiTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Kechi";
		this.serverSideName = false;
		this.title = "Fire";
		this.serverSideTitle = false;
		this.collisionRadius = 12.00;
		this.collisionHeight = 29.00;
		this.level = 82;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 338150.746542376000000;
		this.maxMP = 1743.000000000000000;
		this.hpRegeneration = 192.545054202338000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 2586436;
		this.sp = 1438503;
		this.aggressive = false;
		this.rightHand = null;
		this.leftHand = null;
		this.enchantLevel = 0;
		this.targetable = true;
		this.showName = true;
		this.dropHerbGroup = 0;
		this.baseAttributes = true;
		
		attributes.intelligence = 21;
		attributes.strength = 40;
		attributes.concentration = 43;
		attributes.mentality = 80;
		attributes.dexterity = 30;
		attributes.witness = 20;
		attributes.physicalAttack = 4522.43391;
		attributes.magicalAttack = 3088.23609;
		attributes.physicalDefense = 629.59459;
		attributes.magicalDefense = 338.13000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 26.00000;
		attributes.runSpeed = 180.00000;
	}
}
