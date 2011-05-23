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
public class Tiat2Template extends GrandBossNPCTemplate {
	public static final int ID = 29175;

	@Inject
	protected Tiat2Template(NPCTemplateIDProvider provider, ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));
		this.name = "Tiat";
		this.serverSideName = false;
		this.title = "Witch of the Dragon of Darkness";
		this.serverSideTitle = false;
		this.collisionRadius = 55.00;
		this.collisionHeight = 104.00;
		this.level = 82;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 40;
		this.maxHP = 5523846.144589830000000;
		this.maxMP = 184680.000000000000000;
		this.hpRegeneration = 548.283697654583000;
		this.mpRegeneration = 1000.000000000000000;
		this.experience = 11480335;
		this.sp = 4460470;
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
		attributes.physicalAttack = 3093.77457;
		attributes.magicalAttack = 2112.64698;
		attributes.physicalDefense = 530.62261;
		attributes.magicalDefense = 352.98781;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 80.00000;
		attributes.runSpeed = 200.00000;
	}
}
