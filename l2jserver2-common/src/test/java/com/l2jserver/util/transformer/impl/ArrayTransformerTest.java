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
package com.l2jserver.util.transformer.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class ArrayTransformerTest {
	/**
	 * An Integer[] array as an string
	 */
	private static final String INT_ARRAY_STRING = "1|2|3";
	/**
	 * An Integer[] array
	 */
	private static final Integer[] INT_ARRAY = new Integer[] { 1, 2, 3 };

	/**
	 * An String[] array as an string
	 */
	private static final String STRING_ARRAY_STRING = "test1|test2|test3";
	/**
	 * An String[] array
	 */
	private static final String[] STRING_ARRAY = new String[] { "test1",
			"test2", "test3" };

	/**
	 * An Class[] array as an string
	 */
	private static final String CLASS_ARRAY_STRING = "java.lang.Object|java.lang.Integer|java.lang.Long";
	/**
	 * An Class[] array
	 */
	private static final Class<?>[] CLASS_ARRAY = new Class<?>[] {
			Object.class, Integer.class, Long.class };

	/**
	 * Tests transforming an {@link Integer} array
	 */
	@Test
	public void testIntegerTransforming() {
		final ArrayTransformer<Integer> transformer = new ArrayTransformer<Integer>();
		Assert.assertEquals(INT_ARRAY_STRING,
				transformer.transform(Integer[].class, INT_ARRAY));
		Assert.assertArrayEquals(INT_ARRAY,
				transformer.untransform(Integer[].class, INT_ARRAY_STRING));
	}

	/**
	 * Tests transforming an {@link String} array
	 */
	@Test
	public void testStringTransforming() {
		final ArrayTransformer<String> transformer = new ArrayTransformer<String>();
		Assert.assertEquals(STRING_ARRAY_STRING,
				transformer.transform(String[].class, STRING_ARRAY));
		Assert.assertArrayEquals(STRING_ARRAY,
				transformer.untransform(String[].class, STRING_ARRAY_STRING));
	}

	/**
	 * Tests transforming an {@link Class} array
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testClassTransforming() {
		final ArrayTransformer<Class<?>> transformer = new ArrayTransformer<Class<?>>();
		Assert.assertEquals(CLASS_ARRAY_STRING, transformer.transform(
				(Class<? extends Class<?>[]>) Class[].class, CLASS_ARRAY));
		Assert.assertArrayEquals(CLASS_ARRAY, transformer
				.untransform((Class<? extends Class<?>[]>) Class[].class,
						CLASS_ARRAY_STRING));
	}

}
