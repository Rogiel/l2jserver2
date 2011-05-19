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
import java.util.Collections;
import java.util.List;

import com.l2jserver.util.factory.CollectionFactory;

/**
 * An calculator is used to compute data and outputs its result. Note also, that
 * an calculator is also an function, that way you can nest calculators.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Calculator implements Function<Double> {
	/**
	 * List of operations in this calculator
	 */
	private final List<FunctionContainer> functions = CollectionFactory
			.newList(null);

	/**
	 * Creates a new empty calculator. Functions can be add using
	 * {@link #add(int, Function)}.
	 */
	public Calculator() {
	}

	/**
	 * Creates a new calculator with <tt>functions</tt> in the declaration
	 * order.
	 * 
	 * @param functions
	 *            the calculator functions
	 */
	public Calculator(Function<Double>... functions) {
		for (int i = 0; i < functions.length; i++) {
			this.functions.add(new FunctionContainer(i, functions[i]));
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
	public void add(int order, Function<Double> function) {
		functions.add(new FunctionContainer(order, function));
		Collections.sort(functions);
	}

	/**
	 * Imports all functions in the given <tt>calculator</tt>. This is useful to
	 * preserve right calculation ordering but changes to original
	 * <tt>calculator</tt> will no reflect in this one.
	 * <p>
	 * This method will heuristically search for nested calculators.
	 * 
	 * @param calculator
	 *            the calculator
	 */
	public void importFunctions(Calculator calculator) {
		for (final FunctionContainer container : calculator.functions) {
			if (container.function instanceof Calculator) {
				importFunctions((Calculator) container.function);
			} else {
				functions.add(container);
			}
		}
	}

	/**
	 * Computes the result and output it. Input value is 0.
	 * 
	 * @return the computed value
	 * @see #calculate(Double)
	 */
	public double calculate() {
		return calculate(0.00);
	}

	@Override
	public Double calculate(Double input) {
		double result = input;
		for (final FunctionContainer container : functions) {
			result = container.function.calculate(result);
		}
		return result;
	}

	/**
	 * <h1>-- Internal use only --</h1> Container used to sort calculator
	 * functions. This class implements {@link Comparable} and can be used to
	 * sort lists using {@link Collections#sort(List)} or
	 * {@link Arrays#sort(Object[])}.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private static class FunctionContainer implements
			Comparable<FunctionContainer> {
		/**
		 * The execution order
		 */
		protected final int order;
		/**
		 * The function object
		 */
		protected final Function<Double> function;

		/**
		 * Creates a new instance
		 * 
		 * @param order
		 *            the execution order
		 * @param function
		 *            the function
		 */
		public FunctionContainer(int order, Function<Double> function) {
			this.order = order;
			this.function = function;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((function == null) ? 0 : function.hashCode());
			result = prime * result + order;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FunctionContainer other = (FunctionContainer) obj;
			if (function == null) {
				if (other.function != null)
					return false;
			} else if (!function.equals(other.function))
				return false;
			if (order != other.order)
				return false;
			return true;
		}

		@Override
		public int compareTo(FunctionContainer o) {
			if (this.equals(o))
				return 0;
			return this.order - o.order;
		}
	}
}