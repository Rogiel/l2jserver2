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
public class YehanKlodekusTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25665;

	@Inject
	protected YehanKlodekusTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Yehan Klodekus";
		this.serverSideName = false;
		this.title = "Soul Destroyer";
		this.serverSideTitle = false;
		this.collisionRadius = 34.32;
		this.collisionHeight = 62.40;
		this.level = 81;
		this.sex = ActorSex.MALE;
		this.attackRange = 80;
		this.maxHP = 148936.332535890000000;
		this.maxMP = 1743.000000000000000;
		this.hpRegeneration = 53.219019084218600;
		this.mpRegeneration = 3.000000000000000;
		this.experience = 5044000;
		this.sp = 1152586;
		this.aggressive = false;
		this.rightHand = itemProvider.createID(13982);
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
		attributes.physicalAttack = 2153.20693;
		attributes.magicalAttack = 1624.01433;
		attributes.physicalDefense = 703.66454;
		attributes.magicalDefense = 514.91118;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 73.00000;
		attributes.runSpeed = 180.00000;
	}
}
