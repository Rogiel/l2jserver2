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
package com.l2jserver.model.world.npc.calculator;

import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.actor.calculator.ActorCalculatorFunction;
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * An function for {@link NPC} formulas.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class NPCCalculatorFunction extends ActorCalculatorFunction {
	/**
	 * Creates a new function
	 * 
	 * @param order
	 *            the calculation order
	 * @param type
	 *            the stat type
	 */
	public NPCCalculatorFunction(int order, StatType type) {
		super(order, type);
	}

	@Override
	protected final double calculate(Actor a, ActorTemplate<?> actorTemplate,
			double value) {
		return calculate((NPC) a, (NPCTemplate) actorTemplate, value);
	}

	/**
	 * Calculates the value
	 * 
	 * @param n
	 *            the {@link NPC}
	 * @param t
	 *            the {@link Template}
	 * @param value
	 *            the original value
	 * @return the output value (calculated)
	 */
	protected abstract double calculate(NPC n, NPCTemplate t, double value);
}
