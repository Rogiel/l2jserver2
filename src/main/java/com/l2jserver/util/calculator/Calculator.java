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

import java.util.List;

import com.l2jserver.util.factory.CollectionFactory;

/**
 * An calculator is used to compute data and outputs its result. Note also, that
 * an calculator is also an operation, that way you can nest calculators.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Calculator implements Operation<Double> {
	/**
	 * List of operations in this calculator
	 */
	private final List<Operation<Double>> operations = CollectionFactory
			.newList(null);

	/**
	 * Adds a new operation to this calculator. Operations with the same order
	 * are replaced.
	 * 
	 * @param order
	 *            the operation order, starting at 0.
	 * @param operation
	 *            the operation
	 */
	public void add(int order, Operation<Double> operation) {
		operations.add(order, operation);
	}

	/**
	 * Computes the result and output it
	 * 
	 * @return the computed value
	 */
	public double compute() {
		return perform(0.00);
	}

	@Override
	public Double perform(Double result) {
		for (final Operation<Double> operation : operations) {
			result = operation.perform(result);
		}
		return result;
	}
}