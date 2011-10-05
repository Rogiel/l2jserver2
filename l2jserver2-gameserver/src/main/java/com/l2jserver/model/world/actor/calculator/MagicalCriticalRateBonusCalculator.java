/*
 * This file is part of l2jse rver <l2jserver.com>.
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

import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.stat.BaseStats;
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * Calculates the base magical attack critical rate
 * 
 * <pre>
 * ctx.result = c.getTemplate().getBaseCritical(); // must be checked
 * ctx.result *= BaseStats.WIT.calculateBonus(c.getStats().getWitness());
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MagicalCriticalRateBonusCalculator extends ActorFormula {
	/**
	 * Creates a new instance
	 */
	public MagicalCriticalRateBonusCalculator() {
		super(0x300, StatType.MCRITICAL_RATE);
	}

	@Override
	protected double calculate(Actor a, ActorTemplate<?> t, double value) {
		// TODO only apply if using a weapon
		return value * BaseStats.WIT.calculateBonus(a.getStats().getWitness());
	}
}
