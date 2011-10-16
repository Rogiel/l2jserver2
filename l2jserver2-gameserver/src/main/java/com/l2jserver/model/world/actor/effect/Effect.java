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

import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.EffectTemplateID;
import com.l2jserver.model.template.effect.EffectTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.util.exception.L2Exception;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Effect {
	/**
	 * Applies the given effect to <tt>actor</tt>
	 * 
	 * @throws L2Exception
	 *             if any {@link L2Exception} occur
	 */
	void applyEffect() throws L2Exception;

	/**
	 * @return this effect template id
	 */
	EffectTemplateID getTemplateID();

	/**
	 * @return this effect template
	 */
	EffectTemplate getTemplate();

	/**
	 * @return the actor {@link ID} to which this {@link Effect} is (or was or
	 *         will be) applied to.
	 */
	ActorID<?> getActorID();

	/**
	 * @return the {@link Actor} to which this {@link Effect} is (or was or will
	 *         be) applied to.
	 */
	Actor getActor();
}
