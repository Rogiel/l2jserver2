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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;

/**
 * An calculator is used to compute data and outputs its result. Note also, that
 * an calculator is also an function, that way you can nest calculators.
 * 
 * @param <T>
 *            the calculator context type
 * @param <V>
 *            the calculator attribute set
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ComplexCalculator<T extends CalculatorContext, V extends Enum<V>>
		extends AbstractDoubleFunction<T, V> implements Calculator<T, V> {
	/**
	 * List of operations in this calculator
	 */
	private EnumMap<V, Function<T, V>[]> functions;

	/**
	 * Creates a new empty calculator. Functions can be add using
	 * {@link #add(Function)} or {@link #add(Function...)}
	 * 
	 * @param type
	 *            the {@link Class} for attribute set
	 */
	public ComplexCalculator(Class<V> type) {
		super(0x00, null);
		functions = new EnumMap<V, Function<T, V>[]>(type);
	}

	/**
	 * Creates a new calculator with <tt>functions</tt> in the declaration
	 * order.
	 * 
	 * @param type
	 *            the {@link Class} for attribute set
	 * @param calculators
	 *            the calculators which functions will be imported
	 */
	@SuppressWarnings("unchecked")
	public ComplexCalculator(Class<V> type, Calculator<T, V>... calculators) {
		this(type);
		for (final Calculator<T, V> calculator : calculators) {
			importFunctions(calculator);
		}
	}

	/**
	 * Creates a new calculator with <tt>functions</tt> in the declaration
	 * order.
	 * 
	 * @param value
	 *            the attribute type
	 * @param functions
	 *            the calculator functions
	 */
	@SafeVarargs
	public ComplexCalculator(V value, Function<T, V>... functions) {
		super(0x00, value);
		this.functions = new EnumMap<V, Function<T, V>[]>(
				value.getDeclaringClass());
		add(functions);
	}

	/**
	 * Creates a new calculator with <tt>functions</tt> in the declaration
	 * order.
	 * 
	 * @param type
	 *            the {@link Class} for attribute set
	 * @param functions
	 *            the calculator functions
	 */
	@SafeVarargs
	public ComplexCalculator(Class<V> type, Function<T, V>... functions) {
		this(type);
		add(functions);
		for (final Function<T, V> func : functions) {
			Function<T, V>[] funcs = getList(func.type());
			funcs = Arrays.copyOf(funcs, funcs.length + 1);
			funcs[funcs.length - 1] = func;
			setList(func.type(), funcs);
		}
		for (final Function<T, V>[] funcs : this.functions.values()) {
			Arrays.sort(funcs, FunctionOrderComparator.SHARED_INSTANCE);
		}
	}

	/**
	 * Adds a new function to this calculator. Executing order for functions
	 * with the same order is undefined.
	 * <p>
	 * Once a new function is added, sorting will be performed automatically.
	 * 
	 * @param function
	 *            the operation
	 */
	public void add(Function<T, V> function) {
		Function<T, V>[] funcs = getList(function.type());
		funcs = Arrays.copyOf(funcs, funcs.length + 1);
		funcs[funcs.length - 1] = function;
		setList(function.type(), funcs);
		Arrays.sort(funcs, FunctionOrderComparator.SHARED_INSTANCE);
	}

	/**
	 * Adds a new function to this calculator. Executing order for functions
	 * with the same order is undefined.
	 * <p>
	 * Once a new function is added, sorting will be performed automatically.
	 * 
	 * @param functions
	 *            the functions to be added
	 */
	@SafeVarargs
	public final void add(Function<T, V>... functions) {
		for (final Function<T, V> func : functions) {
			Function<T, V>[] funcs = getList(func.type());
			funcs = Arrays.copyOf(funcs, funcs.length + 1);
			funcs[funcs.length - 1] = func;
			setList(func.type(), funcs);
		}
		sort();
	}

	/**
	 * Adds a new function to this calculator. Executing order for functions
	 * with the same order is undefined.
	 * <p>
	 * Once a new function is added, sorting will be performed automatically.
	 * 
	 * @param functions
	 *            the functions to be added
	 */
	@SafeVarargs
	public final void addNoSort(Function<T, V>... functions) {
		for (final Function<T, V> func : functions) {
			Function<T, V>[] funcs = getList(func.type());
			funcs = Arrays.copyOf(funcs, funcs.length + 1);
			funcs[funcs.length - 1] = func;
			setList(func.type(), funcs);
		}
	}

	/**
	 * Removes an fuction from this calculator.
	 * 
	 * @param function
	 *            the operation
	 */
	public void remove(Function<T, V> function) {
		Function<T, V>[] funcs = getList(function.type());
		int index = Arrays.binarySearch(funcs, function);
		if (index >= 0) {
			funcs = ArrayUtils.remove(funcs, index);
		}
		setList(function.type(), funcs);
	}

	/**
	 * Removes all functions from <code>type</code>
	 * 
	 * @param type
	 *            the type
	 */
	public void remove(V type) {
		setList(type, null);
	}

	/**
	 * Imports all functions from the given <tt>calculator</tt>. This is useful
	 * to preserve right calculation ordering but changes to original
	 * <tt>calculator</tt> will no reflect in this one.
	 * <p>
	 * This method will heuristically search for nested calculators.
	 * 
	 * @param calculators
	 *            the calculators to be imported
	 */
	@SuppressWarnings("unchecked")
	public void importFunctions(Calculator<T, V>... calculators) {
		for (final Calculator<T, V> calculator : calculators) {
			if (calculator instanceof SimpleCalculator) {
				for (final Function<T, V> function : ((SimpleCalculator<T, V>) calculator).functions) {
					if (function instanceof Calculator) {
						importFunctions((Calculator<T, V>) function);
					} else {
						add(function);
					}
				}
			} else if (calculator instanceof ComplexCalculator) {
				for (final Entry<V, Function<T, V>[]> e : ((ComplexCalculator<T, V>) calculator).functions
						.entrySet()) {
					for (final Function<T, V> function : e.getValue()) {
						if (function instanceof Calculator) {
							importFunctions((Calculator<T, V>) function);
						} else {
							add(function);
						}
					}
				}
			}
		}
	}

	/**
	 * Removes all imported functions from the given <tt>calculator</tt>.
	 * <p>
	 * This method will heuristically search for nested calculators.
	 * 
	 * @param calculators
	 *            the calculators
	 */
	@SuppressWarnings("unchecked")
	public void removeFunctions(Calculator<T, V>... calculators) {
		for (final Calculator<T, V> calculator : calculators) {
			if (calculator instanceof SimpleCalculator) {
				for (final Function<T, V> function : ((SimpleCalculator<T, V>) calculator).functions) {
					if (function instanceof Calculator) {
						removeFunctions((Calculator<T, V>) function);
					} else {
						remove(function);
					}
				}
			} else if (calculator instanceof ComplexCalculator) {
				for (final Entry<V, Function<T, V>[]> e : ((ComplexCalculator<T, V>) calculator).functions
						.entrySet()) {
					for (final Function<T, V> function : e.getValue()) {
						if (function instanceof Calculator) {
							removeFunctions((Calculator<T, V>) function);
						} else {
							remove(function);
						}
					}
				}
			}
		}
	}

	/**
	 * Sorts the function accordingly to their execution order
	 */
	public void sort() {
		for (final Function<T, V>[] funcs : functions.values()) {
			Arrays.sort(funcs, FunctionOrderComparator.SHARED_INSTANCE);
		}
	}

	/**
	 * Clears the functions on this calculator
	 */
	public void clear() {
		functions.clear();
	}

	@Override
	public double calculate(V v, T ctx, double value) {
		for (final Function<T, V> function : getList(v)) {
			value = function.calculate(ctx, value);
		}
		return value;
	}

	@Override
	public double calculate(T ctx, double value) {
		return 0;
	}

	@Override
	public double calculate(V v, T ctx) {
		return calculate(v, ctx, 0);
	}

	/**
	 * Get the List of functions for the given <code>value</code>
	 * 
	 * @param value
	 *            the value
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	private Function<T, V>[] getList(V value) {
		Function<T, V>[] list = functions.get(value);
		if (list == null) {
			list = new Function[0];
			functions.put(value, list);
		}
		return list;
	}

	/**
	 * 
	 * @param value
	 * @param func
	 */
	private void setList(V value, Function<T, V>[] func) {
		functions.put(value, func);
	}

	/**
	 * This comparator compares two {@link Function}s based on its execution
	 * oder
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public static class FunctionOrderComparator implements
			Comparator<Function<?, ?>>, Serializable {
		/**
		 * Java Serialization UID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Shared instance (recommeded)
		 */
		public static final FunctionOrderComparator SHARED_INSTANCE = new FunctionOrderComparator();

		@Override
		public int compare(Function<?, ?> func1, Function<?, ?> func2) {
			return (func1.order() - func2.order());
		}
	}
}