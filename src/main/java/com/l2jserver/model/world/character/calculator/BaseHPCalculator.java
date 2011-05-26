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
import com.l2jserver.model.world.actor.stat.BaseStats;
import com.l2jserver.util.calculator.AbstractFunction;
import com.l2jserver.util.calculator.CalculatorContext;

/**
 * Calculates the character maximum HP
 * 
 * <pre>
 * ctx.result = c.getTemplate().getBaseHP();
 * 
 * int lvl = c.getLevel() - template.getMinimumLevel();
 * double mod = template.getBaseHP() * lvl;
 * double max = (template.getBaseHPAdd() + mod) * lvl;
 * double min = (template.getBaseHPAdd() * lvl) + mod;
 * ctx.result += (max + min) / 2;
 * 
 * ctx.result *= BaseStats.CON.calculateBonus(c.getStats().getConcentration());
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BaseHPCalculator extends CharacterCalculator {
	@SuppressWarnings("unchecked")
	public BaseHPCalculator() {
		super(new AbstractFunction<L2Character>(0x000) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				ctx.result = c.getTemplate().getBaseHP();
			}
		}, new AbstractFunction<L2Character>(0x100) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				final CharacterTemplate template = c.getTemplate();

				int lvl = c.getLevel() - template.getMinimumLevel();
				double mod = template.getBaseHP() * lvl;
				double max = (template.getBaseHPAdd() + mod) * lvl;
				double min = (template.getBaseHPAdd() * lvl) + mod;

				ctx.result += (max + min) / 2;
			}
		}, new AbstractFunction<L2Character>(0x200) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				ctx.result *= BaseStats.CON.calculateBonus(c.getStats()
						.getConcentration());
			}
		});
	}
}
