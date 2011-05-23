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
public class TiberiasTemplate extends RaidBossNPCTemplate {
	public static final int ID = 25528;

	@Inject
	protected TiberiasTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Tiberias";
		this.serverSideName = false;
		this.title = "Prime Inspector";
		this.serverSideTitle = false;
		this.collisionRadius = 10.00;
		this.collisionHeight = 24.62;
		this.level = 22;
		this.sex = ActorSex.MALE;
		this.attackRange = 40;
		this.maxHP = 31106.789088098200000;
		this.maxMP = 218.520000000000000;
		this.hpRegeneration = 16.371994256893800;
		this.mpRegeneration = 1.500000000000000;
		this.experience = 225568;
		this.sp = 18408;
		this.aggressive = false;
		this.rightHand = null;
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
		attributes.physicalAttack = 87.89639;
		attributes.magicalAttack = 38.96473;
		attributes.physicalDefense = 185.31388;
		attributes.magicalDefense = 135.60464;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 28.00000;
		attributes.runSpeed = 150.00000;
	}
}
