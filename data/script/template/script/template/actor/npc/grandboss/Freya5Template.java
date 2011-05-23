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
package script.template.actor.npc.grandboss;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.GrandBossNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Freya5Template extends GrandBossNPCTemplate {
	public static final int ID = 29179;

	@Inject
	protected Freya5Template(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Freya";
		this.serverSideName = false;
		this.title = "Ice Queen";
		this.serverSideTitle = false;
		this.collisionRadius = 22.00;
		this.collisionHeight = 55.00;
		this.level = 85;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 40;
		this.maxHP = 2662001.195295380000000;
		this.maxMP = 184680.000000000000000;
		this.hpRegeneration = 612.130336050510000;
		this.mpRegeneration = 300.000000000000000;
		this.experience = 51569937;
		this.sp = 6164678;
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
		attributes.physicalAttack = 9002.55327;
		attributes.magicalAttack = 3120.59162;
		attributes.physicalDefense = 1193.90086;
		attributes.magicalDefense = 582.42800;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 54.00000;
		attributes.runSpeed = 180.00000;
	}
}
