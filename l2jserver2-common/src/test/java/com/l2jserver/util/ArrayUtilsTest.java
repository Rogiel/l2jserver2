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
package com.l2jserver.util;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class ArrayUtilsTest extends ArrayUtils {
	@Test
	public void testCopyArrayExcept() {
		final TestClass objA = new TestClass("a");
		final TestClass objB = new TestClass("b");
		final TestClass objC = new TestClass("c");

		TestClass[] arr = new TestClass[] { objA, objB, objC };
		TestClass[] selected = ArrayUtils.copyArrayExcept(TestClass[].class,
				arr, objB);

		System.out.println(Arrays.toString(selected));
		Assert.assertTrue(Arrays.equals(new TestClass[] { objA, objC },
				selected));
	}

	private static class TestClass {
		private String name;

		public TestClass(String string) {
			this.name = string;
		}

		@Override
		public String toString() {
			return "TestClass [name=" + name + "]";
		}
	}
}
