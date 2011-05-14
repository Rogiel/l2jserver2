package com.l2jserver.util.calculator.operation;

public class SubtractOperation implements CalculatorOperation<Integer> {
	private Integer value;

	public SubtractOperation(Integer value) {
		this.value = value;
	}

	@Override
	public Integer calculate(Integer value) {
		return value - this.value;
	}
}
