/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.world.npc.controller.impl;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.TeleportationTemplateIDProvider;
import com.l2jserver.model.template.TeleportationTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.BaseNPCController;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.geometry.Coordinate;

/**
 * This controller is used to control teleporters (e.g. gatekeepers)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TeleporterController extends BaseNPCController {
	/**
	 * The {@link SpawnService}
	 */
	@Inject
	protected SpawnService spawnService;
	/**
	 * The teleportation template id provider
	 */
	@Inject
	protected TeleportationTemplateIDProvider teleportationIdProvider;

	@Override
	public void interact(NPC npc, L2Character character, String... args)
			throws L2Exception {
		if (args.length >= 2) {
			switch (args[0]) {
			case "goto":
				final TeleportationTemplate tele = teleportationIdProvider
						.resolveID(Integer.parseInt(args[1])).getTemplate();
				if (tele == null) {
					throw new NPCControllerException();
				}
				// TODO remove items from character inventory
				spawnService.teleport(character,
						Coordinate.fromTemplateCoordinate(tele.getPoint()));
				return;
			}
		}
		super.interact(npc, character, args);
	}
}
