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
package com.l2jserver.model.game;

import com.l2jserver.model.AbstractModel;
import com.l2jserver.model.id.SkillID;
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.Actor;

/**
 * Register the state of a skill known by an {@link Actor}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Skill extends AbstractModel<SkillID> {
	/**
	 * The {@link Skill} template id
	 */
	private final SkillTemplateID templateID;
	/**
	 * The skill level learned
	 */
	private int level;

	/**
	 * Creates a new instance in level one
	 * 
	 * @param templateID
	 *            the {@link SkillTemplateID} instance
	 * @param level
	 *            the level of this skill
	 */
	public Skill(SkillTemplateID templateID, int level) {
		this.templateID = templateID;
		this.level = level;
	}

	/**
	 * Creates a new instance in level one
	 * 
	 * @param templateID
	 *            the {@link SkillTemplateID} instance
	 */
	public Skill(SkillTemplateID templateID) {
		this(templateID, 1);
	}

	/**
	 * @return true if this skill is learned in the maximum level
	 */
	public boolean isMaximumLevel() {
		return level >= getTemplate().getMaximumLevel();
	}

	/**
	 * @return the actorID
	 */
	public ActorID<?> getActorID() {
		return id.getID1();
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return getActorID().getObject();
	}

	/**
	 * Sets the actor ID. Note that this will change the skill ID and because of
	 * this, the ID can only be set once.
	 * 
	 * @param actorID
	 *            set the actor ID
	 * @see Skill#setID(SkillID)
	 */
	public void setActorID(ActorID<?> actorID) {
		setID(new SkillID(actorID, templateID));
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		desireUpdate();
		this.level = level;
	}

	/**
	 * @return the skillTemplateID
	 */
	public SkillTemplateID getTemplateID() {
		return id.getID2();
	}

	/**
	 * @return the skillTemplate
	 */
	public SkillTemplate getTemplate() {
		return getTemplateID().getTemplate();
	}
}
