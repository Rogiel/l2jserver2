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
package com.l2jserver.model.world.actor.calculator;

import org.apache.commons.math.util.FastMath;

import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * Calculates the character evasion
 * 
 * <pre>
 * ctx.result = c.getTemplate().getBaseEvasion();
 * ctx.result += sqrt(DEX) * 6;
 * ctx.result += level;
 * if (level &gt; 77)
 * 	ctx.result += (level - 77) + 1;
 * if (level &gt; 69)
 * 	ctx.result += (level - 69);
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class AttackEvasionBonusCalculator extends ActorFormula {
	public AttackEvasionBonusCalculator() {
		super(0x200, StatType.EVASION_RATE);
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
