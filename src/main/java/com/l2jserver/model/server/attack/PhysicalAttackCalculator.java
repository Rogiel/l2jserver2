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
package com.l2jserver.model.server.attack;

import com.l2jserver.model.world.Actor;

/**
 * Calculator used to calculate physical damage on each hit.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PhysicalAttackCalculator extends AttackCalculator {
	public PhysicalAttackCalculator() {
		super(new AttackCalculatorFunction(0x000, AttackCalculatorType.DAMAGE) {
			@Override
			public double calculate(Actor attacker, Actor target, double value) {
				// TODO this is certainly not right!!!
				// this is just an simple calculator for testing!
				return attacker.getStats().getPhysicalAttack()
						- target.getStats().getPhysicalDefense();
			}
		}, new AttackCalculatorFunction(Integer.MAX_VALUE,
				AttackCalculatorType.DAMAGE) {
			@Override
			public double calculate(Actor attacker, Actor target, double value) {
				if (value <= 0)
					return 1;
				return value;
			}
		});
	}
}
