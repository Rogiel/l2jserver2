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
public class Aenkinel6Template extends RaidBossNPCTemplate {
	public static final int ID = 25695;

	@Inject
	protected Aenkinel6Template(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Aenkinel";
		this.serverSideName = false;
		this.title = "Invader of Dream";
		this.serverSideTitle = false;
		this.collisionRadius = 21.00;
		this.collisionHeight = 40.00;
		this.level = 84;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 449514.751202684000000;
		this.maxMP = 19170.000000000000000;
		this.hpRegeneration = 191.950817694987000;
		this.mpRegeneration = 30.000000000000000;
		this.experience = 42720366;
		this.sp = 1206690;
		this.aggressive = false;
		this.rightHand = null;
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
		attributes.physicalAttack = 1839.50786;
		attributes.magicalAttack = 1082.55400;
		attributes.physicalDefense = 1108.07850;
		attributes.magicalDefense = 540.56000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 50.00000;
		attributes.runSpeed = 140.00000;
	}
}
