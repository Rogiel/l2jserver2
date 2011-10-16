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
package com.l2jserver.util.calculator;

/**
 * An function is nothing more than a mathematical operation.
 * 
 * @param <C>
 *            the calculator context type
 * @param <V>
 *            the attribute set type
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Function<C extends CalculatorContext, V extends Enum<V>> {
	/**
	 * Performs the operation in the calculation process.
	 * <p>
	 * The <tt>value</tt> in the argument is normally used for calculation, but
	 * an {@link Function} can ignore the value if required to.
	 * 
	 * @param ctx
	 *            the calculator context
	 * @param value
	 *            the input value
	 * @return the output value
	 */
	double calculate(C ctx, double value);

	/**
	 * @return the order this function will be executed
	 */
	int order();

	V type();
}
