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

import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * Calculates the character maximum HP
 * 
 * <pre>
 * int lvl = c.getLevel() - template.getMinimumLevel();
 * double mod = template.getBaseHPModifier() * lvl;
 * double max = (template.getBaseHPAdd() + mod) * lvl;
 * double min = (template.getBaseHPAdd() * lvl) + mod;
 * ctx.result += (max + min) / 2;
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MaximumHPAddCalculator extends CharacterFormula {
	/**
	 * Creates a new instance of this formula
	 */
	public MaximumHPAddCalculator() {
		super(0x100, StatType.MAX_HP);
	}

	@Override
	public double calculate(L2Character c, CharacterTemplate t, double value) {
		int lvl = c.getLevel() - t.getMinimumLevel();
		double mod = t.getBaseHPModifier() * lvl;
		double max = (t.getBaseHPAdd() + mod) * lvl;
		double min = (t.getBaseHPAdd() * lvl) + mod;

		return value + (max + min) / 2;
	}
}
