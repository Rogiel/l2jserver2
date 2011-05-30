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

import java.util.Arrays;
import java.util.Comparator;

/**
 * An calculator is used to compute data and outputs its result. Note also, that
 * an calculator is also an function, that way you can nest calculators.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SimpleCalculator<T extends CalculatorContext> extends
		AbstractDoubleFunction<T> {
	/**
	 * List of operations in this calculator
	 */
	private Function<? super T>[] functions;

	/**
	 * Creates a new empty calculator. Functions can be add using
	 * {@link #add(int, Function)}.
	 */
	@SuppressWarnings("unchecked")
	public SimpleCalculator() {
		super(0x00);
		functions = new Function[0];
	}

	/**
	 * Creates a new calculator with <tt>functions</tt> in the declaration
	 * order.
	 * 
	 * @param functions
	 *            the calculator functions
	 */
	public SimpleCalculator(Function<? super T>... functions) {
		super(0x00);
		this.functions = functions;
	}

	/**
	 * Adds a new function to this calculator. Executing order for functions
	 * with the same order is undefined.
	 * <p>
	 * Once a new function is added, sorting will be performed automatically.
	 * 
	 * @param order
	 *            the operation order, starting at 0.
	 * @param function
	 *            the operation
	 */
	public void add(Function<? super T> function) {
		functions = Arrays.copyOf(functions, functions.length + 1);
		functions[functions.length - 1] = function;
		Arrays.sort(functions, FunctionOrderComparator.SHARED_INSTANCE);
	}

	/**
	 * Imports all functions from the given <tt>calculator</tt>. This is useful
	 * to preserve right calculation ordering but changes to original
	 * <tt>calculator</tt> will no reflect in this one.
	 * <p>
	 * This method will heuristically search for nested calculators.
	 * 
	 * @param calculator
	 *            the calculator
	 */
	public void importFunctions(SimpleCalculator<? super T> calculator) {
		for (final Function<? super T> function : calculator.functions) {
			if (function instanceof SimpleCalculator) {
				importFunctions((SimpleCalculator<? super T>) function);
			} else {
				add(function);
			}
		}
	}

	/**
	 * Removes all imported functions from the given <tt>calculator</tt>.
	 * <p>
	 * This method will heuristically search for nested calculators.
	 * 
	 * @param calculator
	 *            the calculator
	 */
	public void removeFunctions(SimpleCalculator<? super T> calculator) {
		for (final Function<? super T> function : calculator.functions) {
			if (function instanceof SimpleCalculator) {
				removeFunctions((SimpleCalculator<? super T>) function);
			} else {
				// TODO
				// remove(function);
			}
		}
	}

	@Override
	public double calculate(T ctx, double value) {
		for (final Function<? super T> function : functions) {
			value = function.calculate(ctx, value);
		}
		return value;
	}

	public double calculate(T ctx) {
		return calculate(ctx, 0);
	}

	public static class FunctionOrderComparator implements
			Comparator<Function<?>> {
		public static final FunctionOrderComparator SHARED_INSTANCE = new FunctionOrderComparator();

		@Override
		public int compare(Function<?> func1, Function<?> func2) {
			return (func1.order() - func2.order());
		}
	}
}