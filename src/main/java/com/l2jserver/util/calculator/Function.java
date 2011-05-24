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
package com.l2jserver.util.calculator;

import com.l2jserver.model.world.Actor;

/**
 * An function is nothing more than a mathematical operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Function<O extends Actor> {
	/**
	 * Performs the operation in the calculation process.
	 * <p>
	 * The <tt>value</tt> in the argument is normally used for calculation, but
	 * an {@link Function} can ignore the value if required to (i.e.
	 * {@link SetFunction})
	 * 
	 * @param value
	 *            the input value
	 * @return the output value
	 */
	void calculate(O actor, CalculatorContext ctx);

	/**
	 * @return the order this function will be executed
	 */
	int order();
}
