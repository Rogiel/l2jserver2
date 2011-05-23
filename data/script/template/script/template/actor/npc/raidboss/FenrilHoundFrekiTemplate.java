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
public class FenrilHoundFrekiTemplate extends RaidBossNPCTemplate {
	public static final int ID = 29033;

	@Inject
	protected FenrilHoundFrekiTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Fenril Hound Freki";
		this.serverSideName = false;
		this.title = "Family of Valakas";
		this.serverSideTitle = false;
		this.collisionRadius = 21.00;
		this.collisionHeight = 30.00;
		this.level = 84;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 105872.340000000000000;
		this.maxMP = 1708.140000000000000;
		this.hpRegeneration = 171.990000000000000;
		this.mpRegeneration = 9.800000000000000;
		this.experience = 2434088;
		this.sp = 1257012;
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
		attributes.physicalAttack = 2593.57000;
		attributes.magicalAttack = 611.11393;
		attributes.physicalDefense = 2742.23600;
		attributes.magicalDefense = 501.66200;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 30.00000;
		attributes.runSpeed = 200.00000;
	}
}
