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

import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.stat.BaseStats;
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * Calculates the character base magical defense.
 * 
 * <pre>
 * if (inv.has(LEFT_FINGER))
 * 	ctx.result -= 5;
 * if (inv.has(RIGHT_FINGER))
 * 	ctx.result -= 5;
 * if (inv.has(LEFT_EAR))
 * 	ctx.result -= 9;
 * if (inv.has(RIGHT_EAR))
 * 	ctx.result -= 9;
 * if (inv.has(NECK))
 * 	ctx.result -= 13;
 * ctx.result *= BaseStats.MEN.calculateBonus(c.getStats().getMentality())
 * 		* ((100.0 - 11 + c.getLevel()) / 100.0);
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MagicalDefenseBonusCalculator extends ActorFormula {
	/**
	 * Creates a new instance
	 */
	public MagicalDefenseBonusCalculator() {
		super(0x200, StatType.MAGIC_DEFENSE);
	}

	@Override
	protected double calculate(Actor a, ActorTemplate t, double value) {
		// final CharacterInventory inv = c.getInventory();

		// if (inv.has(LEFT_FINGER))
		// ctx.result -= 5;
		// if (inv.has(RIGHT_FINGER))
		// ctx.result -= 5;
		// if (inv.has(LEFT_EAR))
		// ctx.result -= 9;
		// if (inv.has(RIGHT_EAR))
		// ctx.result -= 9;
		// if (inv.has(NECK))
		// ctx.result -= 13;

		return value
				* BaseStats.MEN.calculateBonus(a.getStats().getMentality())
				* ((100.0 - 11 + a.getLevel()) / 100.0);
	}
}
