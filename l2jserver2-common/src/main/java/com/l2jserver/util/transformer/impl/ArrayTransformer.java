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

import java.lang.reflect.Array;

import org.apache.commons.lang3.StringUtils;

import com.l2jserver.util.transformer.Transformer;
import com.l2jserver.util.transformer.TransformerFactory;

/**
 * Transform an {@link Array} into an string.
 * <p>
 * <b>Important note</b>: Array elements are by an <code>|</code>.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the array component type that this tranformer transforms
 */
public class ArrayTransformer<T> implements Transformer<T[]> {
	/**
	 * This transformer shared instance
	 */
	public static final ArrayTransformer<?> SHARED_INSTANCE = new ArrayTransformer<>();

	@Override
	@SuppressWarnings("unchecked")
	public String transform(Class<? extends T[]> type, T[] value) {
		final Transformer<T> transformer = (Transformer<T>) TransformerFactory
				.getTransfromer(type.getComponentType());
		final String[] values = new String[value.length];
		int i = 0;
		for (final T item : value) {
			values[i++] = transformer.transform(
					(Class<T>) type.getComponentType(), item);
		}
		return StringUtils.join(values, '|');
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] untransform(Class<? extends T[]> type, String stringValue) {
		final Transformer<T> transformer = (Transformer<T>) TransformerFactory
				.getTransfromer(type.getComponentType());
		final String[] stringValues = StringUtils.split(stringValue, '|');
		final Object values = Array.newInstance(type.getComponentType(),
				stringValues.length);
		int i = 0;
		for (final String value : stringValues) {
			Array.set(values, i++, transformer.untransform(
					(Class<T>) type.getComponentType(), value));
		}

		return type.cast(values);
	}
}
