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
public class DopagenTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25698;

	@Inject
	protected DopagenTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Dopagen";
		this.serverSideName = false;
		this.title = "Kokracon";
		this.serverSideTitle = false;
		this.collisionRadius = 45.00;
		this.collisionHeight = 75.00;
		this.level = 85;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 631293.503534084000000;
		this.maxMP = 18468.000000000000000;
		this.hpRegeneration = 242.622420161294000;
		this.mpRegeneration = 300.000000000000000;
		this.experience = 3233771;
		this.sp = 403115;
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
		attributes.physicalAttack = 11355.95863;
		attributes.magicalAttack = 1212.53791;
		attributes.physicalDefense = 17479.90252;
		attributes.magicalDefense = 8527.32835;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 50.00000;
		attributes.runSpeed = 120.00000;
	}
}
