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
public class CorsairCaptainKylonTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25404;

	@Inject
	protected CorsairCaptainKylonTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Corsair Captain Kylon";
		this.serverSideName = false;
		this.title = "Raid Boss";
		this.serverSideTitle = false;
		this.collisionRadius = 17.00;
		this.collisionHeight = 28.00;
		this.level = 33;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 62398.146521093900000;
		this.maxMP = 372.960000000000000;
		this.hpRegeneration = 24.517094130860700;
		this.mpRegeneration = 1.800000000000000;
		this.experience = 1932560;
		this.sp = 127255;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(231);
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
		attributes.physicalAttack = 140.55753;
		attributes.magicalAttack = 11.19451;
		attributes.physicalDefense = 387.69285;
		attributes.magicalDefense = 189.14000;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 8;
		attributes.walkSpeed = 14.00000;
		attributes.runSpeed = 190.00000;
	}
}
