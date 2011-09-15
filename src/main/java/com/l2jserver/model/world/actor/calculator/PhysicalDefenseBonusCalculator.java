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
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * Calculates the character base physical defense
 * 
 * <pre>
 * ctx.result = c.getTemplate().getBasePhysicalDefense();
 * 
 * if (inv.has(HEAD))
 * 	ctx.result -= 12;
 * final Item chest = inv.getItem(CHEST);
 * if (chest != null)
 * 	ctx.result -= hasMagePDef ? 15 : 31;
 * if (inv.has(LEGS))
 * 	ctx.result -= hasMagePDef ? 8 : 18;
 * if (inv.has(GLOVES))
 * 	ctx.result -= 8;
 * if (inv.has(FEET))
 * 	ctx.result -= 7;
 * ctx.result *= ((100.0 - 11 + c.getLevel()) / 100.0);
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PhysicalDefenseBonusCalculator extends ActorFormula {
	/**
	 * Creates a new instance
	 */
	public PhysicalDefenseBonusCalculator() {
		super(0x200, StatType.POWER_DEFENSE);
	}

	@Override
	protected double calculate(Actor a, ActorTemplate<?> t, double value) {
		// final CharacterInventory inv = c.getInventory();
		//
		// // orc mystics are a special case
		// boolean hasMagePDef = (c.getCharacterClass().type ==
		// ClassType.MYSTIC || c
		// .getCharacterClass() == CharacterClass.ORC_MYSTIC);
		//
		// if (inv.has(HEAD))
		// ctx.result -= 12;
		// final Item chest = inv.getItem(CHEST);
		// if (chest != null)
		// ctx.result -= hasMagePDef ? 15 : 31;
		// if (inv.has(LEGS))
		// // FIXME full armor also applies here
		// ctx.result -= hasMagePDef ? 8 : 18;
		// if (inv.has(GLOVES))
		// ctx.result -= 8;
		// if (inv.has(FEET))
		// ctx.result -= 7;
		return value * ((100.0 - 11 + a.getLevel()) / 100.0);
	}
}
