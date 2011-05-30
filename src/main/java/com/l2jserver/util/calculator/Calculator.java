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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.l2jserver.util.factory.CollectionFactory;

/**
 * An calculator is used to compute data and outputs its result. Note also, that
 * an calculator is also an function, that way you can nest calculators.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Calculator<T extends CalculatorContext> extends
		AbstractFunction<T> {
	/**
	 * List of operations in this calculator
	 */
	private final List<Function<? super T>> functions = CollectionFactory
			.newList();

	/**
	 * Creates a new empty calculator. Functions can be add using
	 * {@link #add(int, Function)}.
	 */
	public Calculator() {
		super(0x00);
	}

	/**
	 * Creates a new calculator with <tt>functions</tt> in the declaration
	 * order.
	 * 
	 * @param functions
	 *            the calculator functions
	 */
	public Calculator(Function<? super T>... functions) {
		super(0x00);
		for (final Function<? super T> func : functions) {
			this.functions.add(func);
		}
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
		functions.add(function);
		Collections.sort(functions, FunctionOrderComparator.SHARED_INSTANCE);
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
	public void importFunctions(Calculator<? super T> calculator) {
		for (final Function<? super T> function : calculator.functions) {
			if (function instanceof Calculator) {
				importFunctions((Calculator<? super T>) function);
			} else {
				functions.add(function);
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
	public void removeFunctions(Calculator<? super T> calculator) {
		for (final Function<? super T> function : calculator.functions) {
			if (function instanceof Calculator) {
				removeFunctions((Calculator<? super T>) function);
			} else {
				functions.remove(function);
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