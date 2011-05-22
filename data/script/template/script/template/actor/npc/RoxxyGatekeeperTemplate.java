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
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.TeleporterNPCTemplate;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RoxxyGatekeeperTemplate extends TeleporterNPCTemplate {
	public static final int ID = 30006;

	@Inject
	protected RoxxyGatekeeperTemplate(NPCTemplateIDProvider provider) {
		super(provider.createID(ID));
		this.name = "Roxxy";
		this.title = "Gatekeeper";
		this.attackable = true;
		this.maxHp = 200;

		this.collisionRadius = 8.00;
		this.collisionHeight = 25.00;

		addLocation("The Village of Gludin", GLUDIN, 18000);
		addLocation("Dark Elven Village", DARK_ELF_VILLAGE, 24000);
		addLocation("Dwarven Village", DWARVEN_VILLAGE, 46000);
		addLocation("Elven Village", ELVEN_VILLAGE, 2300);
		addLocation("Orc Village", ORC_VILLAGE, 35000);
		addLocation("Kamael Village", KAMAEL_VILLAGE, 20000);
		addLocation("Elven Ruins", ELVEN_RUINS, 830);
		addLocation("Singing Waterfall", SINGING_WATERFALL, 770);
		addLocation("Talking Island, Northern Territor",
				TALKING_ISLAND_NORTHERN_TERRITORY, 1000);
		addLocation("Obelisk of Victory", OBELISK_OF_VICTORY, 470);

	}
}
