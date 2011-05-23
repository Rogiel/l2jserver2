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
package script.template.actor.npc;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NephilimMercenary2Template extends NPCTemplate {
	public static final int ID = 35061;

	@Inject
	protected NephilimMercenary2Template(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Nephilim Mercenary";
		this.serverSideName = false;
		this.title = "";
		this.serverSideTitle = false;
		this.collisionRadius = 6.50;
		this.collisionHeight = 21.96;
		this.level = 82;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 40;
		this.maxHP = 585703.507797897000000;
		this.maxMP = 1561.391359593390000;
		this.hpRegeneration = 989.000000000000000;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 672;
		this.sp = 1;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(8687);
		this.leftHand = null;
		this.enchantLevel = 0;
		this.targetable = true;
		this.showName = true;
		this.dropHerbGroup = 0;
		this.baseAttributes = true;
		
		attributes.intelligence = 41;
		attributes.strength = 22;
		attributes.concentration = 27;
		attributes.mentality = 20;
		attributes.dexterity = 21;
		attributes.witness = 20;
		attributes.physicalAttack = 2704.10327;
		attributes.magicalAttack = 5539.65578;
		attributes.physicalDefense = 349.77477;
		attributes.magicalDefense = 255.95000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 1;
		attributes.walkSpeed = 33.00000;
		attributes.runSpeed = 195.00000;
	}
}
