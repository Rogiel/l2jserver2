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

import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEFT_EAR;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEFT_FINGER;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.NECK;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.RIGHT_EAR;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.RIGHT_FINGER;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.stat.BaseStats;
import com.l2jserver.model.world.character.CharacterInventory;
import com.l2jserver.util.calculator.AbstractFunction;
import com.l2jserver.util.calculator.CalculatorContext;

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
public class BaseMagicalDefenseCalculator extends CharacterCalculator {
	@SuppressWarnings("unchecked")
	public BaseMagicalDefenseCalculator() {
		super(new AbstractFunction<L2Character>(0x000) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				ctx.result = c.getTemplate().getBaseMagicalDefense();
			}
		}, new AbstractFunction<L2Character>(0x200) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				final CharacterInventory inv = c.getInventory();

				if (inv.has(LEFT_FINGER))
					ctx.result -= 5;
				if (inv.has(RIGHT_FINGER))
					ctx.result -= 5;
				if (inv.has(LEFT_EAR))
					ctx.result -= 9;
				if (inv.has(RIGHT_EAR))
					ctx.result -= 9;
				if (inv.has(NECK))
					ctx.result -= 13;

				ctx.result *= BaseStats.MEN.calculateBonus(c.getStats()
						.getMentality())
						* ((100.0 - 11 + c.getLevel()) / 100.0);
			}
		});
	}
}
