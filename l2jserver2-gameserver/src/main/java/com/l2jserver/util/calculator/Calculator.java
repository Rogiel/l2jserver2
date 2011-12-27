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
 * @param <T>
 *            the context used in this calculator
 * @param <V>
 *            the attribute calculated by this calculator
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Calculator<T extends CalculatorContext, V extends Enum<V>>
		extends Function<T, V> {
	/**
	 * Calculates the result for the given <code>ctx</code> and
	 * <code>variable</code> and <code>value</code>.
	 * 
	 * @param variable
	 *            the variable
	 * @param ctx
	 *            the context
	 * @param value
	 *            the value
	 * @return the calculated value
	 */
	double calculate(V variable, T ctx, double value);

	/**
	 * Calculates the result for the given <code>ctx</code> and
	 * <code>variable</code>. Original value is <code>0</code>.
	 * 
	 * @param variable
	 *            the variable
	 * @param ctx
	 *            the context
	 * @return the calculated value
	 */
	double calculate(V variable, T ctx);
}
