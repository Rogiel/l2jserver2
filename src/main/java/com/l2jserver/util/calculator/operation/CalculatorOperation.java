package com.l2jserver.util.calculator.operation;

public interface CalculatorOperation<T extends Number> {
	T calculate(T value);
}
