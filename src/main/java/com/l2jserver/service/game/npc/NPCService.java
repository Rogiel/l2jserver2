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
package com.l2jserver.service.game.npc;

import java.util.List;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.client.CharacterActionPacket.CharacterAction;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.Service;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;

/**
 * This service manages {@link NPC} instances
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface NPCService extends Service {
	/**
	 * Executes an action for an NPC. Each {@link NPCTemplate} have it's own
	 * actions.
	 * 
	 * @param npc
	 *            the npc
	 * @param character
	 *            the character
	 * @param action
	 *            the action type
	 */
	void action(NPC npc, L2Character character, CharacterAction action)
			throws ActionServiceException;

	/**
	 * Executes an action for an NPC. Each {@link NPCTemplate} have it's own
	 * actions.
	 * 
	 * @param npc
	 *            the npc
	 * @param character
	 *            the character
	 * @param args
	 *            the action arguments
	 */
	void action(NPC npc, L2Character character, String... args)
			throws ActionServiceException;

	/**
	 * Load from database and spawn all {@link NPC NPCs} instances
	 * 
	 * @throws AlreadySpawnedServiceException
	 *             if one of the NPCs is already spawned
	 * @throws SpawnPointNotFoundServiceException
	 *             if one of the NPCs does not have an spawn point defined
	 */
	List<NPC> spawnAll() throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException;

	/**
	 * Attacks an given NPC, if possible.
	 * 
	 * @param npc
	 *            the npc
	 * @param conn
	 *            the {@link Lineage2Connection} object
	 * @param attacker
	 *            the character
	 * @throws NotAttackableNPCServiceException
	 *             if {@link NPC} is not attackable
	 */
	void attack(NPC npc, Lineage2Connection conn, L2Character attacker)
			throws NotAttackableNPCServiceException;
}
