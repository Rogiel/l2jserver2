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
import com.l2jserver.model.template.npc.SepulcherMonsterNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ShadowOfHalisha2Template extends SepulcherMonsterNPCTemplate {
	public static final int ID = 25342;

	@Inject
	protected ShadowOfHalisha2Template(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Shadow of Halisha";
		this.serverSideName = false;
		this.title = "Ruler of Sepulcher";
		this.serverSideTitle = false;
		this.collisionRadius = 23.00;
		this.collisionHeight = 60.00;
		this.level = 80;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 1100;
		this.maxHP = 213848.930921219000000;
		this.maxMP = 1725.888000000000000;
		this.hpRegeneration = 73.053846697935500;
		this.mpRegeneration = 3.030000000000000;
		this.experience = 2643173;
		this.sp = 390090;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(6720);
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
		attributes.physicalAttack = 866.44981;
		attributes.magicalAttack = 251.11046;
		attributes.physicalDefense = 1047.20215;
		attributes.magicalDefense = 510.85800;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 8;
		attributes.walkSpeed = 43.00000;
		attributes.runSpeed = 190.00000;
	}
}
