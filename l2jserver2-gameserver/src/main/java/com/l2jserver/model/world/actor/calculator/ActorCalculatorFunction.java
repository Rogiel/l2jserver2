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
package com.l2jserver.model.world.actor.calculator;

import com.l2jserver.model.template.actor.ActorTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.stat.StatType;
import com.l2jserver.util.calculator.AbstractDoubleFunction;

/**
 * An calculator for character formulas.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ActorCalculatorFunction extends
		AbstractDoubleFunction<ActorCalculatorContext, StatType> {
	/**
	 * @param order
	 *            the calculation order
	 * @param type
	 *            the stat type
	 */
	public ActorCalculatorFunction(int order, StatType type) {
		super(order, type);
	}

	@Override
	public final double calculate(ActorCalculatorContext ctx, double value) {
		return calculate(ctx.actor, ctx.actor.getTemplate(), value);
	}

	/**
	 * @param a
	 *            the actor
	 * @param t
	 *            the actor template
	 * @param value
	 *            the original value
	 * @return the calculated value
	 */
	protected abstract double calculate(Actor a, ActorTemplate<?> t,
			double value);
}
