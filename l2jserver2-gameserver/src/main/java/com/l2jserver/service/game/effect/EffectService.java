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
package com.l2jserver.service.game.effect;

import com.l2jserver.model.template.effect.EffectTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.effect.Effect;
import com.l2jserver.service.Service;
import com.l2jserver.util.exception.L2Exception;

/**
 * The effect service will handle. This service will be backed by a thread that
 * will execute the effect.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface EffectService extends Service {
	/**
	 * Adds a new effect to an given <tt>character</tt>. Once the effect has
	 * expired, it will be automatically removed and need not to be removed with
	 * {@link #removeEffect(Actor, Effect)}
	 * <p>
	 * <b>Note</b>: some effects will return <code>null</code>. Those effects do
	 * not have an temporal effect and thus are not added to the
	 * <code>actor</code> effect list.
	 * 
	 * @param actor
	 *            the actor which the effect will be applied to
	 * @param effectTemplate
	 *            the effect template that will be applied to <tt>actor</tt>
	 * @return the effect. Can be <tt>null</tt>
	 * @throws L2Exception
	 *             if any exception while applying the effect
	 */
	Effect addEffect(Actor actor, EffectTemplate effectTemplate)
			throws L2Exception;

	/**
	 * Forcefully removes an effect from the given <tt>character</tt>. <b>Only
	 * use this to remove the effect before it expires</b>
	 * 
	 * @param actor
	 *            the actor which the effect will be removed from
	 * @param effect
	 *            the effect that will be removed from <tt>actor</tt>
	 */
	void removeEffect(Actor actor, Effect effect);
}
