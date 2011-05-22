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

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.service.game.ai.AIScript;

/**
 * NPC stand for "Not Playable Character" and is an character that not player
 * has control over it. In most cases they are controlled by an {@link AIScript}
 * .
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPC extends AbstractActor {
	/**
	 * The NPC template ID
	 */
	private final NPCTemplateID templateID;

	/**
	 * Creates a new instance
	 * 
	 * @param templateID
	 *            the {@link NPC} {@link TemplateID}
	 */
	public NPC(NPCTemplateID templateID) {
		this.templateID = templateID;
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
