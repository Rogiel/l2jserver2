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

import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.stat.BaseStats;

/**
 * Calculates the character base walk speed
 * 
 * <pre>
 * ctx.result *= BaseStats.DEX.calculateBonus(c.getStats().getDexterity());
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class WalkSpeedBonusCalculator extends ActorCalculator {
	public WalkSpeedBonusCalculator() {
		super(new ActorCalculatorFunction(0x300) {
			@Override
			protected double calculate(Actor c, ActorTemplate<?> t, double value) {
				return value
						* BaseStats.DEX.calculateBonus(c.getStats()
								.getDexterity());
			}
		});
	}
}
