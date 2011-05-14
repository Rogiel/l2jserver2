package com.l2jserver.util.calculator.operation;

public class SetOperation implements CalculatorOperation<Integer> {
	private Integer value;

	public SetOperation(Integer value) {
		this.value = value;
	}

	@Override
	public Integer calculate(Integer value) {
		return this.value;
	}
}
