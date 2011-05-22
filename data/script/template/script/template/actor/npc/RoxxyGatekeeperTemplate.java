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
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.html.markup.HtmlTemplate;
import com.l2jserver.util.html.markup.MarkupTag;

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

	@Override
	protected HtmlTemplate getChat(String name) throws L2Exception {
		if (name.equals("")) {
			return new HtmlTemplate() {
				@Override
				protected void build(MarkupTag body) {
					body.text(
							"Welcome! I am Gatekeeper Roxxy, dispatched from the Ivory Tower. "
									+ "I'm very happy to return to my hometown. Have you perhaps been to "
									+ "Einhovant's School of Magic? Grand Magister Gallint was my old "
									+ "teacher. Now then, how can I help you?")
							.br();
					body.addLink("Ask about the airship wharf.",
							"npc_${npcid}_Chat airship").br();
					body.addLink("Ask about the Ivory Tower.",
							"npc_${npcid}_Chat ivory").br();
					body.addLink("Teleport", "npc_${npcid}_Chat teleport").br();
					body.addLink("Exchange Dimension Diamonds. (TODO)",
							"npc_${npcid}_multisell 002").br();
					body.addLink("Noblesse Exclusive Teleport",
							"npc_${npcid}_Quest 2000_NoblesseTeleport").br();
					body.addLink("Receive your Birthday Gift! (TODO)",
							"npc_${npcid}_Quest CharacterBirthday").br();
					body.addLink("Quest", "npc_${npcid}_Quest").br();
				}
			};
		} else if (name.equals("airship")) {
			return new HtmlTemplate() {
				@Override
				protected void build(MarkupTag body) {
					body.text("This text is not yet implemented.");
				}
			};
		} else if (name.equals("ivory")) {
			return new HtmlTemplate() {
				@Override
				protected void build(MarkupTag body) {
					body.text(
							"The Ivory Tower is the foremost temple of all Mystics. It has been "
									+ "well known as such ever since the age of Elmoreden. I was born here, and "
									+ "graduated from Einhovant's School of Wizardry. And now, I am working as a "
									+ "gatekeeper by the order of the Ivory Tower. If you happen to go to the Ivory "
									+ "Tower later on, please give my best regards to Grand Magister Valerie. She "
									+ "is my teacher and a wonderful, caring person.")
							.br();
					body.addLink("Back", "npc_${npcid}");
				}
			};
		}
		return super.getChat(name);
	}
}
