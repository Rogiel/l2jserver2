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
public class HelsingTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25579;

	@Inject
	protected HelsingTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Helsing";
		this.serverSideName = false;
		this.title = "Forsaken Inmate";
		this.serverSideTitle = false;
		this.collisionRadius = 14.00;
		this.collisionHeight = 31.00;
		this.level = 74;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 40;
		this.maxHP = 196619.812148594000000;
		this.maxMP = 1475.000000000000000;
		this.hpRegeneration = 84.909917163516400;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 2280801;
		this.sp = 367102;
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
		attributes.physicalAttack = 5200.74380;
		attributes.magicalAttack = 857.53707;
		attributes.physicalDefense = 943.97961;
		attributes.magicalDefense = 460.50000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 20.00000;
		attributes.runSpeed = 170.00000;
	}
}
