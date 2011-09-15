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
package com.l2jserver.model.world.character.calculator;

import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.calculator.ActorCalculatorFunction;
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * An calculator for character formulas.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class CharacterCalculatorFunction extends
		ActorCalculatorFunction {
	/**
	 * @param order
	 *            the calculation order
	 * @param type
	 *            the stat type
	 */
	public CharacterCalculatorFunction(int order, StatType type) {
		super(order, type);
	}

	@Override
	protected final double calculate(Actor a, ActorTemplate<?> actorTemplate,
			double value) {
		return calculate((L2Character) a, (CharacterTemplate) actorTemplate,
				value);
	}

	/**
	 * Computes the calculated value
	 * 
	 * @param c
	 *            the character
	 * @param t
	 *            the template
	 * @param value
	 *            the input value
	 * @return the computed value
	 */
	protected abstract double calculate(L2Character c, CharacterTemplate t,
			double value);
}
