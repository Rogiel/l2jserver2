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

import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.actor.calculator.ActorCalculatorContext;

/**
 * Calculator context for {@link NPC} formulas
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCCalculatorContext extends ActorCalculatorContext {
	/**
	 * The npc instance
	 */
	public final NPC npc;

	public NPCCalculatorContext(NPC npc) {
		super(npc);
		this.npc = npc;
	}
}
