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
package com.l2jserver.model.template;

import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.NPC;

/**
 * Template for {@link NPC}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class NPCTemplate<T extends NPC> extends ActorTemplate<T> {
	protected NPCTemplate(NPCTemplateID id) {
		super(id, null);
	}

	@Override
	public T createInstance() {
		return null;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	@Override
	public NPCTemplateID getID() {
		return (NPCTemplateID) super.getID();
	}
}
