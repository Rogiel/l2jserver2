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

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.client.CM_CHAR_ACTION.CharacterAction;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.Service;
import com.l2jserver.service.core.threading.AsyncFuture;
import com.l2jserver.service.game.character.CannotSetTargetServiceException;
import com.l2jserver.util.geometry.Point3D;

/**
 * This service controls {@link NPC} objects. It can execute {@link NPC}
 * interactions, kill it, move it and make it attack someone or be attacked.
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
	 * @throws ActionServiceException
	 *             if the action thrown an exception
	 * @throws CannotSetTargetServiceException
	 *             if was not possible to set the target
	 */
	void action(NPC npc, L2Character character, CharacterAction action)
			throws ActionServiceException, CannotSetTargetServiceException;

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
	 * @throws ActionServiceException
	 *             if the action thrown an exeption
	 * @throws CannotSetTargetServiceException
	 *             if was not possible to set the target
	 */
	void action(NPC npc, L2Character character, String... args)
			throws ActionServiceException, CannotSetTargetServiceException;

	/**
	 * Kills the given <tt>npc</tt>. If "nobody" killed the NPC (i.e. died by
	 * admin command), <tt>killer</tt> can be null.
	 * 
	 * @param npc
	 *            the npc being killed
	 * @param killer
	 *            the killer actor
	 */
	void die(NPC npc, Actor killer);

	/**
	 * Moves an given <tt>npc</tt> to an <tt>point</tt>
	 * 
	 * @param npc
	 *            the NPC
	 * @param point
	 *            the destination point
	 * @return the future informing once the NPC has been moved to that location
	 */
	AsyncFuture<Boolean> move(NPC npc, Point3D point);

	/**
	 * Attacks an given NPC, if possible.
	 * 
	 * @param npc
	 *            the npc
	 * @param conn
	 *            the {@link Lineage2Client} object
	 * @param attacker
	 *            the character
	 * @throws NotAttackableNPCServiceException
	 *             if {@link NPC} is not attackable
	 */
	void attack(NPC npc, Lineage2Client conn, L2Character attacker)
			throws NotAttackableNPCServiceException;
}
