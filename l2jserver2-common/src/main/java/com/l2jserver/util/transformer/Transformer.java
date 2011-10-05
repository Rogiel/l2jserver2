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
package com.l2jserver.util.transformer;

/**
 * An transformer can transform an {@link Object} into an {@link String} and the
 * {@link String} back to an equivalent object.
 * 
 * @param <T>
 *            the transformed type
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Transformer<T> {
	/**
	 * Transform the object in a string
	 * 
	 * @param value
	 *            the object
	 * @return the string of the object
	 */
	String transform(T value);

	/**
	 * Untransforms the string back to an object
	 * 
	 * @param value
	 *            the string
	 * @return the object
	 */
	T untransform(String value);
}
