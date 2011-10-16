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
package com.l2jserver.model.world.npc.controller;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.server.SM_STATUS_UPDATE;
import com.l2jserver.game.net.packet.server.SM_STATUS_UPDATE.Stat;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.util.exception.L2Exception;

/**
 * This controller is used to control teleporters (e.g. gatekeepers)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MonsterController extends BaseNPCController {
	/**
	 * The {@link CharacterService}
	 */
	@Inject
	protected CharacterService charService;
	/**
	 * The {@link NPCService}
	 */
	@Inject
	protected NPCService npcService;

	@Override
	public void action(NPC mob, Lineage2Client conn, L2Character character,
			String... args) throws L2Exception {
		// send hp update
		if (mob.getID().equals(character.getTargetID())) {
			charService.attack(character, mob);
		} else {
			charService.target(character, mob);
			conn.write(new SM_STATUS_UPDATE(mob).add(Stat.MAX_HP,
					(int) mob.getTemplate().getMaximumHP()).add(Stat.HP,
					(int) mob.getHP()));
		}
	}
}
