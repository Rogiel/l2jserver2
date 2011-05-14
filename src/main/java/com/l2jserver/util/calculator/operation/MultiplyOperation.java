package com.l2jserver.util.calculator.operation;

public class MultiplyOperation implements CalculatorOperation<Integer> {
	private Integer value;

	public MultiplyOperation(Integer value) {
		this.value = value;
	}

	@Override
	public Integer calculate(Integer value) {
		return value * this.value;
	}
}
