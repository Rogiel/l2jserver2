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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.util.jaxb.SkillTemplateIDAdapter;

/**
 * Template for {@link Skill} object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "skill")
@XmlType(namespace = "skill", name = "skill")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillTemplate extends AbstractTemplate<Skill> {
	@XmlAttribute(name = "id")
	@XmlJavaTypeAdapter(value = SkillTemplateIDAdapter.class)
	protected SkillTemplateID id;
	@XmlAttribute(name = "name")
	protected String name;
	@XmlAttribute(name = "delay")
	protected int delay;
	@XmlAttribute(name = "cooldown")
	protected int cooldown;

	/**
	 * The maximum level supported by this skill
	 */
	protected int maximumLevel = 1;

	@Override
	public Skill create() {
		return create(null);
	}

	/**
	 * Creates a new instance
	 * 
	 * @param actorID
	 *            the actor ID
	 * 
	 * @return the created template instance
	 */
	public Skill create(ActorID<?> actorID) {
		final Skill skill = new Skill(id);
		if (actorID != null)
			skill.setActorID(actorID);
		skill.setLevel(1);
		return skill;
	}

	/**
	 * @return the maximumLevel
	 */
	public int getMaximumLevel() {
		return maximumLevel;
	}

	/**
	 * @return the id
	 */
	public SkillTemplateID getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @return the cooldown
	 */
	public int getCooldown() {
		return cooldown;
	}

	@Override
	public SkillTemplateID getID() {
		return id;
	}
}
