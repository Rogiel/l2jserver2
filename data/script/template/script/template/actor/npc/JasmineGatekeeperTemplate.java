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
public class JasmineGatekeeperTemplate extends TeleporterNPCTemplate {
	public static final int ID = 30134;

	@Inject
	protected JasmineGatekeeperTemplate(NPCTemplateIDProvider provider) {
		super(provider.createID(ID));
		this.name = "Jasmine";
		this.title = "Gatekeeper";
		this.attackable = true;
		this.maxHp = 200;

		this.collisionRadius = 8.00;
		this.collisionHeight = 32.00;

		addLocation("The Town of Gludio", GLUDIO, 10000);
		addLocation("The Dwarven Village", DWARVEN_VILLAGE, 22000);
		addLocation("Talking Island Village", TALKING_ISLAND_VILLAGE, 24000);
		addLocation("The Orc Village", ORC_VILLAGE, 13000);
		addLocation("Kamael Village", KAMAEL_VILLAGE, 13000);
		addLocation("Dark Forest", DARK_FOREST, 890);
		addLocation("Swampland", SWAMPLAND, 1100);
		addLocation("Spider Nest", SPIDER_NEST, 3600);
		addLocation("Neutral Zone", NEUTRAL_ZONE, 1700);
	}

	@Override
	protected HtmlTemplate getChat(String name) throws L2Exception {
		if (name.equals("")) {
			return new HtmlTemplate() {
				@Override
				protected void build(MarkupTag body) {
					body.text("Gatekeeper Jasmine:").br();
					body.text("The Gatekeeper Guild stewards the doors of time and space that connects "
							+ "a multitude of locations on the continent. ");
					body.text("Do you wish to leave this forest and seek your dreams in the wide world beyond? ");
					body.text(
							"If you wish it, I can send you to an exotic land filled with romance and adventure!")
							.br();
					body.addLink("Teleport", "npc_${npcid}_Chat teleport").br();
					body.addLink("Exchange Dimension Diamonds",
							"npc_${npcid}_multisell 002").br();
					body.addLink("Noblesse Exclusive Teleport",
							"npc_${npcid}_Quest 2000_NoblesseTeleport").br();
					body.addLink("Receive your Birthday Gift!",
							"npc_${npcid}_Quest CharacterBirthday").br();
					body.addLink("Quest", "npc_${npcid}_Quest").br();
				}
			};
		}
		return super.getChat(name);
	}
}
