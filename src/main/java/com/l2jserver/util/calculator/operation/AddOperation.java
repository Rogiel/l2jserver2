package com.l2jserver.util.calculator.operation;

public class AddOperation implements CalculatorOperation<Integer> {
	private Integer value;

	public AddOperation(Integer value) {
		this.value = value;
	}

	@Override
	public Integer calculate(Integer value) {
		return value + this.value;
	}
}
