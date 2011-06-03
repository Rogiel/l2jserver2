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
package com.l2jserver.model.id;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.game.CharacterFriend;
import com.l2jserver.model.id.compound.AbstractCompoundID;
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.SkillTemplateID;

/**
 * Each {@link CharacterFriend} is identified by an {@link ID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SkillID extends AbstractCompoundID<ActorID<?>, SkillTemplateID> {
	/**
	 * Creates a new instance
	 * 
	 * @param id1
	 *            the actor id
	 * @param id2
	 *            the skill template id
	 */
	@Inject
	public SkillID(@Assisted("id1") ActorID<?> id1,
			@Assisted("id2") SkillTemplateID id2) {
		super(id1, id2);
	}
}
