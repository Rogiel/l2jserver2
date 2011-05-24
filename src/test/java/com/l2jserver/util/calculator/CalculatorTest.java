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
		final Calculator<CalculatorContext> calc = new Calculator<CalculatorContext>();

		calc.add(new SetFunction(0, 10));
		calc.add(new MultiplicationFunction(1, 2));
		calc.add(new SetFunction(2, 30));

		final CalculatorContext ctx = new CalculatorContext();
		calc.calculate(ctx);
		Assert.assertEquals(30.0, ctx.result);
	}

	@Test
	public void testPercent() {
		final Calculator<CalculatorContext> calc = new Calculator<CalculatorContext>();

		calc.add(new SetFunction(0, 10));
		calc.add(new MultiplicationFunction(1, 2));
		calc.add(new PercentFunction(2, 75));

		final CalculatorContext ctx = new CalculatorContext();
		calc.calculate(ctx);
		Assert.assertEquals(15.0, ctx.result);
	}

	@Test
	public void testComplex() {
		final Calculator<CalculatorContext> calc = new Calculator<CalculatorContext>();

		calc.add(new SetFunction(0, 10));
		calc.add(new MultiplicationFunction(1, 2));
		calc.add(new PercentFunction(2, 75));
		calc.add(new SumFunction(3, 3));
		calc.add(new SubtractFunction(4, 8));
		calc.add(new DivisionFunction(5, 2));

		final CalculatorContext ctx = new CalculatorContext();
		calc.calculate(ctx);
		Assert.assertEquals(5.0, ctx.result);
	}

	@Test
	public void testNesting() {
		final Calculator<CalculatorContext> calc1 = new Calculator<CalculatorContext>();
		final CalculatorContext ctx1 = new CalculatorContext();

		calc1.add(new SetFunction(0, 10));
		calc1.add(new MultiplicationFunction(1, 2));
		calc1.add(new PercentFunction(2, 75));
		calc1.add(new SumFunction(3, 3));
		calc1.add(new SubtractFunction(4, 8));
		calc1.add(new DivisionFunction(5, 2));

		calc1.calculate(ctx1);
		Assert.assertEquals(5.0, ctx1.result);

		final Calculator<CalculatorContext> calc2 = new Calculator<CalculatorContext>();
		final CalculatorContext ctx2 = new CalculatorContext();

		calc2.add(new MultiplicationFunction(0, 2));
		calc2.add(new PercentFunction(1, 75));
		calc2.add(new SumFunction(2, 3));
		calc2.add(new SubtractFunction(3, 8));
		calc2.add(new DivisionFunction(4, 2));

		calc2.calculate(ctx2);
		Assert.assertEquals(-2.5, ctx2.result);

		final Calculator<CalculatorContext> calc3 = new Calculator<CalculatorContext>();
		final CalculatorContext ctx3 = new CalculatorContext();
		calc3.add(calc1);
		calc3.add(calc2);

		// this should be executed
		calc2.add(new SumFunction(10, 1));

		calc3.calculate(ctx3);
		Assert.assertEquals(2.25, ctx3.result);
	}

	@Test
	public void testImporting() {
		final Calculator<CalculatorContext> calc1 = new Calculator<CalculatorContext>();
		final CalculatorContext ctx1 = new CalculatorContext();

		calc1.add(new SetFunction(0, 10));
		calc1.add(new MultiplicationFunction(2, 2));
		calc1.add(new PercentFunction(4, 75));
		calc1.add(new SumFunction(6, 3));
		calc1.add(new SubtractFunction(8, 8));
		calc1.add(new DivisionFunction(10, 2));

		calc1.calculate(ctx1);
		Assert.assertEquals(5.0, ctx1.result);

		final Calculator<CalculatorContext> calc2 = new Calculator<CalculatorContext>();
		final CalculatorContext ctx2 = new CalculatorContext();

		calc2.add(new MultiplicationFunction(1, 2));
		calc2.add(new PercentFunction(3, 75));
		calc2.add(new SumFunction(5, 3));
		calc2.add(new SubtractFunction(7, 8));
		calc2.add(new DivisionFunction(9, 2));

		calc2.calculate(ctx2);
		Assert.assertEquals(-2.5, ctx2.result);

		final Calculator<CalculatorContext> calc3 = new Calculator<CalculatorContext>();
		final CalculatorContext ctx3 = new CalculatorContext();
		calc3.importFunctions(calc1);
		calc3.importFunctions(calc2);

		// this should not be executed
		calc2.add(new SumFunction(11, 50));

		calc3.calculate(ctx3);
		Assert.assertEquals(1.25, ctx3.result);
	}

	@Test
	public void testRounding() {
		final Calculator<CalculatorContext> calc = new Calculator<CalculatorContext>();

		calc.add(new MultiplicationFunction(0, 2));
		calc.add(new PercentFunction(1, 75));
		calc.add(new SumFunction(2, 3));
		calc.add(new SubtractFunction(3, 8.1));
		calc.add(new DivisionFunction(4, 2));
		calc.add(new RoundFunction(5));

		final CalculatorContext ctx = new CalculatorContext();
		calc.calculate(ctx);
		Assert.assertEquals(-3.0, ctx.result);
	}
}
