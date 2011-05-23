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
public class RoaringLordKastorTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25226;

	@Inject
	protected RoaringLordKastorTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Roaring Lord Kastor";
		this.serverSideName = false;
		this.title = "Raid Boss";
		this.serverSideTitle = false;
		this.collisionRadius = 22.50;
		this.collisionHeight = 54.00;
		this.level = 62;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 322915.008583856000000;
		this.maxMP = 1097.000000000000000;
		this.hpRegeneration = 105.482285672223000;
		this.mpRegeneration = 2.700000000000000;
		this.experience = 4417539;
		this.sp = 712205;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(186);
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
		attributes.physicalAttack = 1606.18889;
		attributes.magicalAttack = 538.72140;
		attributes.physicalDefense = 772.02801;
		attributes.magicalDefense = 376.62000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 1;
		attributes.walkSpeed = 70.00000;
		attributes.runSpeed = 180.00000;
	}
}
