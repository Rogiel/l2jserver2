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
package com.l2jserver.model.template;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.effect.EffectTemplate;
import com.l2jserver.model.template.effect.TeleportEffectTemplate;
import com.l2jserver.util.jaxb.SkillTemplateIDAdapter;

/**
 * Template for {@link Skill} object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "skill", namespace = "http://schemas.l2jserver2.com/skill")
@XmlType(namespace = "http://schemas.l2jserver2.com/skill", name = "SkillType")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillTemplate extends AbstractTemplate {
	@XmlAttribute(name = "id", required = true)
	@XmlJavaTypeAdapter(value = SkillTemplateIDAdapter.class)
	protected SkillTemplateID id;
	@XmlAttribute(name = "name", required = true)
	protected String name;
	@XmlAttribute(name = "delay")
	protected int delay;
	@XmlAttribute(name = "cooldown")
	protected int cooldown;

	/**
	 * The maximum level supported by this skill
	 */
	protected int maximumLevel = 1;

	@XmlElements({ @XmlElement(name = "teleport", type = TeleportEffectTemplate.class) })
	protected EffectTemplate[] effects;

	/**
	 * Create a new {@link Skill}
	 * 
	 * @return the created object
	 */
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

	/**
	 * @return the effects
	 */
	public List<EffectTemplate> getEffects() {
		return Arrays.asList(effects);
	}

	/**
	 * Tries to locate in the effects list an effect of the given type.
	 * 
	 * @param <E>
	 *            the effect type
	 * @param effectType
	 *            the effect type class
	 * @return the effect found, if any.
	 */
	@SuppressWarnings("unchecked")
	public <E extends EffectTemplate> E getEffect(Class<E> effectType) {
		for (final EffectTemplate effect : effects) {
			if (effectType.isInstance(effect))
				return (E) effect;
		}
		return null;
	}

	@Override
	public SkillTemplateID getID() {
		return id;
	}
}
