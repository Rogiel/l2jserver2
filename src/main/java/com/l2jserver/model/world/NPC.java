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
package com.l2jserver.model.world;

import com.l2jserver.game.net.packet.client.CharacterActionPacket.CharacterAction;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.template.NPCTemplate;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class NPC extends AbstractActor {
	/**
	 * The NPC template ID
	 */
	private final NPCTemplateID templateID;

	public NPC(NPCTemplateID templateID) {
		this.templateID = templateID;
	}

	/**
	 * Executes an action on this NPC
	 * 
	 * @param character
	 *            the interacting character
	 * @param action
	 *            the action
	 */
	public void action(L2Character character, CharacterAction action) {
		getTemplate().action(this, character, action);
	}

	/**
	 * @return the NPC template ID
	 */
	public NPCTemplateID getTemplateID() {
		return templateID;
	}

	/**
	 * @return the NPC template
	 */
	public NPCTemplate getTemplate() {
		return templateID.getTemplate();
	}

	@Override
	public NPCID getID() {
		return (NPCID) super.getID();
	}
}
