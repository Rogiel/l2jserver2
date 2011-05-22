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

import com.google.common.base.Preconditions;
import com.l2jserver.game.net.packet.client.CharacterActionPacket.CharacterAction;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.AbstractService;
import com.l2jserver.util.exception.L2Exception;

/**
 * Default {@link NPCService} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCServiceImpl extends AbstractService implements NPCService {
	@Override
	public void action(NPC npc, L2Character character, CharacterAction action)
			throws ActionServiceException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(action, "action");

		final NPCTemplate template = npc.getTemplate();
		try {
			template.action(npc, character, new String[0]);
		} catch (L2Exception e) {
			throw new ActionServiceException(e);
		}
	}

	@Override
	public void action(NPC npc, L2Character character, String... args)
			throws ActionServiceException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(character, "character");
		if (args == null)
			args = new String[0];

		final NPCTemplate template = npc.getTemplate();
		try {
			template.action(npc, character, args);
		} catch (L2Exception e) {
			throw new ActionServiceException(e);
		}
	}

	@Override
	public void attack(NPC npc, L2Character attacker)
			throws NotAttackableNPCServiceException {
		Preconditions.checkNotNull(npc, "npc");
		Preconditions.checkNotNull(attacker, "attacker");
	}
}
