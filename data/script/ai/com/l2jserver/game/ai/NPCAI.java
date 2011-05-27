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
package com.l2jserver.game.ai;

import com.google.inject.Inject;
import com.l2jserver.game.ai.desires.Desire;
import com.l2jserver.game.ai.desires.MoveDesire;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.npc.NPCService;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class NPCAI extends AI<NPC> {
	@Inject
	protected NPCService npcService;

	/**
	 * @param npc
	 *            the npc
	 */
	protected NPCAI(NPC npc) {
		super(npc);
	}

	@Override
	protected void handleDesire(Desire desire) {
		if (desire instanceof MoveDesire) {
			
		}
	}
}
