package com.l2jserver.util.calculator;

import java.util.List;

import com.l2jserver.util.calculator.operation.AddOperation;
import com.l2jserver.util.calculator.operation.CalculatorOperation;
import com.l2jserver.util.calculator.operation.SetOperation;
import com.l2jserver.util.calculator.operation.SubtractOperation;
import com.l2jserver.util.factory.CollectionFactory;

public class Formula {
	private List<CalculatorOperation<Integer>> operations = CollectionFactory
			.newList(null);

	public void addOperation(int order, CalculatorOperation<Integer> operation) {
		operations.set(order, operation);
	}

	public void add(int order, int value) {
		addOperation(order, new AddOperation(value));
	}

	public void subtract(int order, int value) {
		addOperation(order, new SubtractOperation(value));
	}

	public void set(int order, int value) {
		addOperation(order, new SetOperation(value));
	}

	public int calculate() {
		int value = 0;
		for (CalculatorOperation<Integer> operation : operations) {
			value = operation.calculate(value);
		}
		return value;
	}
}
