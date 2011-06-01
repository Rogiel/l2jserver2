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
package com.l2jserver.model.world.npc.event;

import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.actor.event.ActorDieEvent;

/**
 * Event dispatched once a {@link NPC} has died.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCDieEvent extends ActorDieEvent implements NPCEvent {
	/**
	 * @param npc
	 *            the died npc
	 * @param the
	 *            actor who killed the <tt>npc</tt>
	 */
	public NPCDieEvent(NPC npc, Actor killer) {
		super(npc, killer);
	}

	@Override
	public NPC getNPC() {
		return (NPC) super.getActor();
	}
}
