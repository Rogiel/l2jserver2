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
package com.l2jserver.model.world.npc.event;

import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.WorldObject;

/**
 * Event dispatched once a {@link NPC} has died.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCTalkEvent implements NPCEvent {
	private final NPC npc;
	private final L2Character character;
	private final String html;

	/**
	 * @param npc
	 *            the {@link NPC} that is talking to the player
	 * @param character
	 *            the {@link L2Character} talking to the {@link NPC}
	 * @param html
	 *            the message html content
	 */
	public NPCTalkEvent(NPC npc, L2Character character, String html) {
		this.npc = npc;
		this.character = character;
		this.html = html;
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}

	/**
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}

	@Override
	public WorldObject getObject() {
		return npc;
	}

	@Override
	public NPC getNPC() {
		return npc;
	}

	@Override
	public ObjectID<?>[] getDispatchableObjects() {
		return new ObjectID[] { npc.getID(), character.getID() };
	}
}
