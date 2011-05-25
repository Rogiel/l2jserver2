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
package com.l2jserver.model.world.npc.controller;

import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.server.NPCHtmlMessagePacket;
import com.l2jserver.model.id.template.provider.TeleportationTemplateIDProvider;
import com.l2jserver.model.template.NPCTemplate.TeleportRegion;
import com.l2jserver.model.template.TeleportationTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.html.markup.HtmlTemplate;
import com.l2jserver.util.html.markup.MarkupTag;

/**
 * This controller is used to control teleporters (e.g. gatekeepers)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TeleporterController extends AbstractNPCController {
	/**
	 * The {@link SpawnService}
	 */
	@Inject
	protected SpawnService spawnService;
	@Inject
	protected TeleportationTemplateIDProvider teleportationIdProvider;

	@Override
	public void action(NPC npc, Lineage2Connection conn, L2Character character,
			String... args) throws L2Exception {
		if (args.length >= 2) {
			if (args[0].equals("TeleportList")) {
				final List<TeleportRegion> regions = getTeleportRegions(npc,
						args[1]);
				final HtmlTemplate template = new HtmlTemplate() {
					@Override
					protected void build(MarkupTag body) {
						body.textcode(556);
						for (final TeleportRegion region : regions) {
							body.addLink(
									region.getID().getTemplate().getName()
											+ " - "
											+ region.getPrice()
											+ " "
											+ region.getItem().getTemplate()
													.getName(),
									"Teleport " + region.getID().getID()).br();
						}
					}
				};
				conn.write(new NPCHtmlMessagePacket(npc, template));
			} else if (args[0].equals("Teleport")) {
				final TeleportationTemplate tele = teleportationIdProvider
						.createID(args[1]).getTemplate();
				if (tele == null) {
					// TODO chat
					conn.sendActionFailed();
					return;
				} else {
					spawnService.teleport(character, tele.getCoordinate());
				}
			}
		}
		super.action(npc, conn, character, args);
	}

	protected List<TeleportRegion> getTeleportRegions(NPC npc, String id) {
		return npc.getTemplate().getTeleportRegions(id);
	}
}
