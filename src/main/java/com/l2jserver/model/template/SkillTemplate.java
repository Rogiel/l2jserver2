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

import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.capability.Castable;
import com.l2jserver.model.world.character.CharacterClass;

/**
 * Template for {@link Skill} object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class SkillTemplate extends AbstractTemplate<Skill> implements
		Castable {
	/**
	 * The maximum level supported by this skill
	 */
	protected int maximumLevel = 1;

	public SkillTemplate(SkillTemplateID id) {
		super(id);
	}

	/**
	 * @return the maximumLevel
	 */
	public int getMaximumLevel() {
		return maximumLevel;
	}

	public abstract CharacterClass[] getClasses();

	@Override
	public Skill create() {
		final Skill skill = new Skill(this.getID());
		return skill;
	}

	@Override
	public SkillTemplateID getID() {
		return (SkillTemplateID) super.getID();
	}
}
