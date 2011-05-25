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

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.server.ActorStatusUpdatePacket;
import com.l2jserver.game.net.packet.server.ActorStatusUpdatePacket.Stat;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.character.CharacterService;
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

	@Override
	public void action(NPC npc, Lineage2Connection conn, L2Character character,
			String... args) throws L2Exception {
		// send hp update
		conn.write(new ActorStatusUpdatePacket(npc).add(Stat.MAX_HP,
				(int) npc.getTemplate().getMaximumHP()).add(Stat.HP,
				(int) npc.getHP()));
	}
}
