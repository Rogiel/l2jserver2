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

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.util.exception.L2Exception;

/**
 * The {@link NPC} controller is used to control any given NPC. Implementations
 * will add behaviors to each NPC.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface NPCController {
	/**
	 * Performs an interaction with this {@link NPC}.
	 * 
	 * @param npc
	 *            the {@link NPC} instance
	 * @param conn
	 *            the connection to {@link L2Character}
	 * @param character
	 *            the interacting character
	 * @param args
	 *            the action arguments
	 * @throws L2Exception
	 *             any {@link L2Exception}
	 */
	void action(NPC npc, Lineage2Client conn, L2Character character,
			String... args) throws L2Exception;
}
