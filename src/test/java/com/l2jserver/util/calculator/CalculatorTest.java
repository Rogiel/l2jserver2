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

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class CalculatorTest {
	@Test
	public void testSimple() {
		final Calculator calc = new Calculator();

		calc.add(0, new SetOperation(10));
		calc.add(1, new MultiplicationOperation(2));
		calc.add(2, new SetOperation(30));

		Assert.assertEquals(30.0, calc.compute());
	}

	@Test
	public void testPercent() {
		final Calculator calc = new Calculator();

		calc.add(0, new SetOperation(10));
		calc.add(1, new MultiplicationOperation(2));
		calc.add(2, new PercentOperation(75));

		Assert.assertEquals(15.0, calc.compute());
	}

	@Test
	public void testComplex() {
		final Calculator calc = new Calculator();

		calc.add(0, new SetOperation(10));
		calc.add(1, new MultiplicationOperation(2));
		calc.add(2, new PercentOperation(75));
		calc.add(3, new SumOperation(3));
		calc.add(4, new SubtractOperation(8));
		calc.add(5, new DivisionOperation(2));

		Assert.assertEquals(5.0, calc.compute());
	}

	@Test
	public void testNesting() {
		final Calculator calc1 = new Calculator();

		calc1.add(0, new SetOperation(10));
		calc1.add(1, new MultiplicationOperation(2));
		calc1.add(2, new PercentOperation(75));
		calc1.add(3, new SumOperation(3));
		calc1.add(4, new SubtractOperation(8));
		calc1.add(5, new DivisionOperation(2));
		Assert.assertEquals(5.0, calc1.compute());

		final Calculator calc2 = new Calculator();

		calc2.add(0, new MultiplicationOperation(2));
		calc2.add(1, new PercentOperation(75));
		calc2.add(2, new SumOperation(3));
		calc2.add(3, new SubtractOperation(8));
		calc2.add(4, new DivisionOperation(2));
		Assert.assertEquals(-2.5, calc2.compute());

		final Calculator calc3 = new Calculator();
		calc3.add(0, calc1);
		calc3.add(1, calc2);

		Assert.assertEquals(1.25, calc3.compute());
	}

	@Test
	public void testRounding() {
		final Calculator calc1 = new Calculator();

		calc1.add(0, new MultiplicationOperation(2));
		calc1.add(1, new PercentOperation(75));
		calc1.add(2, new SumOperation(3));
		calc1.add(3, new SubtractOperation(8.1));
		calc1.add(4, new DivisionOperation(2));
		calc1.add(5, new RoundOperation());
		Assert.assertEquals(-3.0, calc1.compute());
	}
}
