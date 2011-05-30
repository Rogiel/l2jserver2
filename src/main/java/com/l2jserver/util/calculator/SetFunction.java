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

/**
 * This function performs an set. It ignores the input value and return its own.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SetFunction extends AbstractFunction<CalculatorContext> {
	/**
	 * The value
	 */
	private final double value;

	public SetFunction(int order, double value) {
		super(order);
		this.value = value;
	}

	@Override
	public double calculate(CalculatorContext ctx, double value) {
		return this.value;
	}
}
