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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.l2jserver.util.transformer.TransformException;
import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link URI} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class URLTransformer implements Transformer<URL> {
	/**
	 * This transformer shared instance
	 */
	public static final URLTransformer SHARED_INSTANCE = new URLTransformer();

	@Override
	public String transform(Class<? extends URL> type, URL value) {
		return value.toString();
	}

	@Override
	public URL untransform(Class<? extends URL> type, String value) {
		try {
			return new URL(value);
		} catch (MalformedURLException e) {
			throw new TransformException(e);
		}
	}

}
