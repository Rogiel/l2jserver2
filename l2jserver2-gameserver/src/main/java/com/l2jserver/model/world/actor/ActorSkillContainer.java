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
package com.l2jserver.model.world.actor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * The Skill container will manage all learned skills by an actor. This class
 * can also create the {@link Skill} object if an actor is learning a new skill.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorSkillContainer implements Iterable<Skill> {
	/**
	 * The actor
	 */
	private final Actor actor;
	/**
	 * The learned skill list
	 */
	private List<Skill> skills = CollectionFactory.newList();

	/**
	 * Creates a new instance
	 * 
	 * @param actor
	 *            the actor
	 */
	public ActorSkillContainer(Actor actor) {
		this.actor = actor;
	}

	/**
	 * Learns a new skill
	 * 
	 * @param template
	 *            the skill to be learned
	 * @param level
	 *            the skill level
	 * @return the created skill object, null if skill existed or could not be
	 *         learned.
	 */
	public Skill learn(SkillTemplate template, int level) {
		if (hasSkill(template.getID()) != null)
			return null;
		if (level > template.getMaximumLevel())
			level = template.getMaximumLevel();
		final Skill newSkill = template.create();
		newSkill.setLevel(level);
		if (skills.add(newSkill))
			return newSkill;
		return null;
	}

	/**
	 * Learns a new skill. The skill <tt>level</tt> will be 1.
	 * 
	 * @param template
	 *            the skill to be learned
	 * @return the created skill object, null if skill existed or could not be
	 *         learned.
	 * @see ActorSkillContainer#learn(SkillTemplate, int)
	 */
	public Skill learn(SkillTemplate template) {
		return learn(template, 1);
	}

	/**
	 * Learns a new skill
	 * 
	 * @param skill
	 *            the skill to be learned
	 * @param level
	 *            the skill level
	 * @return the created skill object, null if skill existed or could not be
	 *         learned.
	 * @see ActorSkillContainer#learn(SkillTemplate, int)
	 */
	public Skill learn(SkillTemplateID skill, int level) {
		return learn(skill.getTemplate(), level);
	}

	/**
	 * Learns a new skill. The skill <tt>level</tt> will be 1.
	 * 
	 * @param skill
	 *            the skill to be learned
	 * @return the created skill object, null if skill existed or could not be
	 *         learned.
	 * @see ActorSkillContainer#learn(SkillTemplate, int)
	 */
	public Skill learn(SkillTemplateID skill) {
		return learn(skill, 1);
	}

	/**
	 * Test is the actor knows the given <tt>skillTemplate</tt>
	 * 
	 * @param skillTemplate
	 *            the skill
	 * @return return the learned skill or null if does not learned skill
	 */
	public Skill hasSkill(SkillTemplateID skillTemplate) {
		for (final Skill skill : this.skills) {
			if (skill.getTemplateID().equals(skillTemplate))
				return skill;
		}
		return null;
	}

	/**
	 * Load all skills in the {@link Collection} to this container
	 * 
	 * @param skills
	 *            the skill collection
	 */
	public void load(Collection<Skill> skills) {
		this.skills.addAll(skills);
	}

	@Override
	public Iterator<Skill> iterator() {
		return skills.iterator();
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return actor;
	}
}
