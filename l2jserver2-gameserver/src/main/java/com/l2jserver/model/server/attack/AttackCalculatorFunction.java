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
package com.l2jserver.model.server.attack;

import com.l2jserver.model.server.attack.AttackCalculator.AttackCalculatorType;
import com.l2jserver.model.world.Actor;
import com.l2jserver.util.calculator.AbstractDoubleFunction;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AttackCalculatorFunction extends
		AbstractDoubleFunction<AttackCalculatorContext, AttackCalculatorType> {
	/**
	 * @param order
	 *            the calculation order
	 * @param type
	 *            the function attribute
	 */
	public AttackCalculatorFunction(int order, AttackCalculatorType type) {
		super(order, type);
	}

	@Override
	public double calculate(AttackCalculatorContext ctx, double value) {
		return calculate(ctx.attacker, ctx.target, value);
	}

	/**
	 * @param attacker
	 *            the attacker
	 * @param target
	 *            the target
	 * @param value
	 *            the original value
	 * @return the calculated value
	 */
	public abstract double calculate(Actor attacker, Actor target, double value);
}
