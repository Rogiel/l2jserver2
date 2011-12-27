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

import com.l2jserver.model.template.effect.EffectTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.util.exception.L2Exception;

/**
 * An simple {@link TemporalEffect} implementing basic methods. Effect
 * implementations are encouraged to implement this class instead of
 * {@link TemporalEffect} directly.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the effect template type
 */
public abstract class AbstractTemporalEffect<T extends EffectTemplate> extends
		AbstractEffect<T> implements TemporalEffect {
	/**
	 * The last time the effect ticked
	 */
	protected long lastCallTime;

	/**
	 * @param template
	 *            the effect template
	 * @param actor
	 *            the actor that the effect is applied to
	 */
	public AbstractTemporalEffect(T template, Actor actor) {
		super(template, actor);
	}

	@Override
	public final void applyEffect() throws L2Exception {
		lastCallTime = System.currentTimeMillis();
		applyEffectImpl();
	}

	/**
	 * Applies the given effect to <tt>actor</tt>
	 * 
	 * @throws L2Exception
	 *             if any {@link L2Exception} occur
	 * @see TemporalEffect#applyEffect()
	 */
	public abstract void applyEffectImpl() throws L2Exception;

	@Override
	public final TemporalEffectAction continueEffect() {
		long now = System.currentTimeMillis();
		long delta = now - lastCallTime;
		try {
			return continueEffect(delta);
		} finally {
			lastCallTime = now;
		}
	}

	/**
	 * Continues the effect calculation, if necessary.
	 * 
	 * @param elapsedTime
	 *            the time elapsed since the last call
	 * @return the next action
	 * @see TemporalEffect#continueEffect()
	 */
	protected abstract TemporalEffectAction continueEffect(long elapsedTime);
}
