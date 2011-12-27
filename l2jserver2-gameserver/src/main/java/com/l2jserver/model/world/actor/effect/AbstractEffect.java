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
package com.l2jserver.model.world.actor.effect;

import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.EffectTemplateID;
import com.l2jserver.model.template.effect.EffectTemplate;
import com.l2jserver.model.world.Actor;

/**
 * An simple {@link Effect} implementing basic methods. Effect implementations
 * are encouraged to implement this class instead of {@link Effect} directly.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the effect template type
 */
public abstract class AbstractEffect<T extends EffectTemplate> implements
		Effect {
	/**
	 * The effect template
	 */
	protected final T template;
	/**
	 * The actor that the effect is applied to
	 */
	protected final Actor actor;

	/**
	 * @param template
	 *            the effect template
	 * @param actor
	 *            the actor that the effect is applied to
	 */
	public AbstractEffect(T template, Actor actor) {
		this.template = template;
		this.actor = actor;
	}

	@Override
	public EffectTemplateID getTemplateID() {
		return template.getID();
	}

	@Override
	public EffectTemplate getTemplate() {
		return template;
	}

	@Override
	public ActorID<?> getActorID() {
		return actor.getID();
	}

	@Override
	public Actor getActor() {
		return actor;
	}
}
