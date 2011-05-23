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
package script.template.actor.npc.defender;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.DefenderNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RebelCommander17Template extends DefenderNPCTemplate {
	public static final int ID = 36223;

	@Inject
	protected RebelCommander17Template(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Rebel Commander";
		this.serverSideName = false;
		this.title = "Western Fortress";
		this.serverSideTitle = false;
		this.collisionRadius = 10.00;
		this.collisionHeight = 26.99;
		this.level = 80;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 679374.315371996000000;
		this.maxMP = 1674.800000000000000;
		this.hpRegeneration = 391.417777464402000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 3200;
		this.sp = 0;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(2500);
		this.leftHand = itemProvider.createID(6377);
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
		attributes.physicalAttack = 7476.50801;
		attributes.magicalAttack = 5105.48575;
		attributes.physicalDefense = 546.20000;
		attributes.magicalDefense = 599.52818;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 29.80000;
		attributes.runSpeed = 120.00000;
	}
}
