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
package com.l2jserver.model.world.npc.calculator.base;

import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.calculator.NPCCalculator;
import com.l2jserver.model.world.npc.calculator.NPCCalculatorFunction;

/**
 * Calculates the character base critical rate
 * 
 * <pre>
 * ctx.result = c.getTemplate().getBaseCritical();
 * ctx.result *= BaseStats.DEX.calculateBonus(c.getStats().getDexterity());
 * ctx.result *= 10;
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCBasePhysicalCriticalRateCalculator extends NPCCalculator {
	public NPCBasePhysicalCriticalRateCalculator() {
		super(new NPCCalculatorFunction(0x000) {
			@Override
			protected double calculate(NPC c, NPCTemplate t, double value) {
				return t.getCritical();
			}
		});
	}
}
