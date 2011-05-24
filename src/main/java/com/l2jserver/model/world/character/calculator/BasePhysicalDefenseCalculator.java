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

import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.CHEST;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.FEET;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.GLOVES;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.HEAD;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEGS;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.model.world.character.CharacterClass.ClassType;
import com.l2jserver.model.world.character.CharacterInventory;
import com.l2jserver.util.calculator.AbstractFunction;
import com.l2jserver.util.calculator.CalculatorContext;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BasePhysicalDefenseCalculator extends CharacterCalculator {
	@SuppressWarnings("unchecked")
	public BasePhysicalDefenseCalculator() {
		super(new AbstractFunction<L2Character>(0x000) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				ctx.result = c.getTemplate().getPhysicalDefense();
			}
		}, new AbstractFunction<L2Character>(0x200) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				final CharacterInventory inv = c.getInventory();

				// orc mystics are a special case
				boolean hasMagePDef = (c.getCharacterClass().type == ClassType.MYSTIC || c
						.getCharacterClass() == CharacterClass.ORC_MYSTIC);

				if (inv.has(HEAD))
					ctx.result -= 12;
				final Item chest = inv.getItem(CHEST);
				if (chest != null)
					ctx.result -= hasMagePDef ? 15 : 31;
				if (inv.has(LEGS))
					// FIXME full armor also applies here
					ctx.result -= hasMagePDef ? 8 : 18;
				if (inv.has(GLOVES))
					ctx.result -= 8;
				if (inv.has(FEET))
					ctx.result -= 7;
				ctx.result *= ((100.0 - 11 + c.getLevel()) / 100.0);
			}
		});
	}
}
