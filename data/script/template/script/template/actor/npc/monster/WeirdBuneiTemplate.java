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
public class WeirdBuneiTemplate extends MonsterNPCTemplate {
	public static final int ID = 18564;

	@Inject
	protected WeirdBuneiTemplate(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Weird Bunei";
		this.serverSideName = false;
		this.title = "Kaneus";
		this.serverSideTitle = false;
		this.collisionRadius = 55.00;
		this.collisionHeight = 80.50;
		this.level = 46;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 40;
		this.maxHP = 165222.579863740000000;
		this.maxMP = 3189.000000000000000;
		this.hpRegeneration = 28.302015995177700;
		this.mpRegeneration = 11.550000000000000;
		this.experience = 848114;
		this.sp = 69276;
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
		attributes.physicalAttack = 470.81863;
		attributes.magicalAttack = 321.50809;
		attributes.physicalDefense = 291.50106;
		attributes.magicalDefense = 319.96174;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 60.00000;
		attributes.runSpeed = 160.00000;
	}
}
