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
package script.template.actor.npc.teleporter;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.TeleporterNPCTemplate;
import com.l2jserver.model.world.Actor.ActorSex;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TamilGatekeeperTemplate extends TeleporterNPCTemplate {
	public static final int ID = 30576;

	@Inject
	protected TamilGatekeeperTemplate(NPCTemplateIDProvider provider,
			ItemTemplateIDProvider itemProvider) {
		super(provider.createID(ID));

		addLocation("The Town of Gludio", GLUDIO, 23000);
		addLocation("Town of Schuttgart", SCHUTTGART, 13000);
		addLocation("Dark Elven Village", DARK_ELF_VILLAGE, 13000);
		addLocation("Dwarven Village", DWARVEN_VILLAGE, 17000);
		addLocation("Talking Island Village", TALKING_ISLAND_VILLAGE, 35000);
		addLocation("Elven Village", ELVEN_VILLAGE, 18000);
		addLocation("Kamael Village", KAMAEL_VILLAGE, 17000);
		addLocation("Immortal Plateau, Southern Region",
				IMMORTAL_PLATEAU_SOUTHERN_REGION, 2000);
		addLocation("The Immortal Plateau", IMMORTAL_PLATEAU, 960);
		addLocation("Cave of Trials", CAVE_OF_TRIALS, 1500);
		addLocation("Frozen Waterfall", FROZEN_WATERFALL, 1600);

		this.name = "Tamil";
		this.serverSideName = false;
		this.title = "Gatekeeper";
		this.serverSideTitle = false;
		this.collisionRadius = 8.00;
		this.collisionHeight = 32.00;
		this.level = 70;
		this.sex = ActorSex.FEMALE;
		this.attackRange = 40;
		this.maxHP = 2444.468190000000000;
		this.maxMP = 1345.800000000000000;
		this.hpRegeneration = 7.500000000000000;
		this.mpRegeneration = 2.700000000000000;
		this.experience = 0;
		this.sp = 0;
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
		attributes.physicalAttack = 688.86373;
		attributes.magicalAttack = 470.40463;
		attributes.physicalDefense = 295.91597;
		attributes.magicalDefense = 216.53847;
		attributes.attackSpeed = 253;
		attributes.castSpeed = 333;
		attributes.criticalChance = 4;
		attributes.walkSpeed = 50.00000;
		attributes.runSpeed = 120.00000;
	}
}
