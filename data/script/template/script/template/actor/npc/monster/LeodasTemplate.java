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
public class LeodasTemplate extends MonsterNPCTemplate {
	public static final int ID = 22448;

	@Inject
	protected LeodasTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Leodas";
		this.serverSideName = false;
		this.title = "Resistance Commander";
		this.serverSideTitle = false;
		this.collisionRadius = 8.00;
		this.collisionHeight = 23.50;
		this.level = 86;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 194672.691398289000000;
		this.maxMP = 1881.800000000000000;
		this.hpRegeneration = 108.314129048672000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 944308;
		this.sp = 93684;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(6611);
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
		attributes.physicalAttack = 4621.00533;
		attributes.magicalAttack = 3155.54760;
		attributes.physicalDefense = 584.99232;
		attributes.magicalDefense = 642.10800;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 50.00000;
		attributes.runSpeed = 100.00000;
	}
}
