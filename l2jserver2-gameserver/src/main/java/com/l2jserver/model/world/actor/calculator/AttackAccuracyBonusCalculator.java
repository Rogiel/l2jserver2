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

import org.apache.commons.math.util.FastMath;

import com.l2jserver.model.template.actor.ActorTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * Calculates the character base accuracy.
 * 
 * <pre>
 * ctx.result = c.getTemplate().getBaseAccuracy();
 * ctx.result += sqrt(DEX) * 6
 * ctx.result += level;
 * if (level > 77)
 * 	ctx.result += (level - 77) + 1;
 * if (level > 69)
 * 	ctx.result += (level - 69);
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class AttackAccuracyBonusCalculator extends ActorFormula {
	/**
	 * Creates a new instance
	 */
	public AttackAccuracyBonusCalculator() {
		super(0x200, StatType.ACCURACY_COMBAT);
	}

	@Override
	protected double calculate(Actor a, ActorTemplate<?> t, double value) {
		final int level = a.getLevel();

		value += FastMath.sqrt(a.getStats().getDexterity()) * 6;
		value += level;
		if (level > 77)
			value += (level - 77) + 1;
		if (level > 69)
			value += (level - 69);
		return value;
	}
}
