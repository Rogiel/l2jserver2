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
package com.l2jserver.model.template.effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.l2jserver.model.id.template.EffectTemplateID;
import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.effect.Effect;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Effect")
@XmlSeeAlso({ TeleportEffectTemplate.class })
public abstract class EffectTemplate implements Template {
	@XmlAttribute(name = "id")
	protected EffectTemplateID id;

	protected EffectTemplate() {
		this.id = null;
	}

	/**
	 * Creates a new effect for the given <tt>actor</tt>
	 * 
	 * @param actor
	 *            the actor
	 * @return the newly created effect
	 */
	public abstract Effect createEffect(Actor actor);

	@Override
	public EffectTemplateID getID() {
		return id;
	}
}
