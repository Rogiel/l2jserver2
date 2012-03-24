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
package com.l2jserver.model.world.npc.calculator.base;

import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.actor.stat.StatType;
import com.l2jserver.model.world.npc.calculator.NPCFormula;

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
public class NPCBasePhysicalDefenseCalculator extends NPCFormula {
	/**
	 * Creates a new instance of this formula
	 */
	public NPCBasePhysicalDefenseCalculator() {
		super(0x000, StatType.POWER_DEFENSE);
	}

	@Override
	protected double calculate(NPC c, NPCTemplate t, double value) {
		return t.getInfo().getStats().getDefense().getPhysical().getValue();
	}
}
