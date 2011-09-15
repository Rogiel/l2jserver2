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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * An calculator is used to compute data and outputs its result. Note also, that
 * an calculator is also an function, that way you can nest calculators.
 * 
 * @param <T>
 *            the calculator context type
 * @param <V>
 *            the attribute set type
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SimpleCalculator<T extends CalculatorContext, V extends Enum<V>>
		extends AbstractDoubleFunction<T, V> implements Calculator<T, V> {
	protected final V value;
	/**
	 * List of operations in this calculator
	 */
	protected final Function<T, V>[] functions;

	/**
	 * Creates a new calculator with <tt>functions</tt> in the declaration
	 * order.
	 * 
	 * @param value
	 *            the attribute
	 * @param functions
	 *            the calculator functions
	 */
	public SimpleCalculator(V value, Function<T, V>... functions) {
		super(0x00, value);
		this.value = value;
		this.functions = functions;
		Arrays.sort(this.functions, FunctionOrderComparator.SHARED_INSTANCE);
	}

	public double calculate(V v, T ctx, double value) {
		if (v != this.value)
			return value;
		for (final Function<T, V> function : functions) {
			value = function.calculate(ctx, value);
		}
		return value;
	}

	public double calculate(V v, T ctx) {
		return calculate(ctx, 0);
	}

	@Override
	public double calculate(T ctx, double value) {
		return 0;
	}

	public static class FunctionOrderComparator implements
			Comparator<Function<?, ?>>, Serializable {
		private static final long serialVersionUID = 1L;

		public static final FunctionOrderComparator SHARED_INSTANCE = new FunctionOrderComparator();

		@Override
		public int compare(Function<?, ?> func1, Function<?, ?> func2) {
			return (func1.order() - func2.order());
		}
	}
}