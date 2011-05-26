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

import java.io.File;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;

import com.l2jserver.util.transformer.impl.BooleanTransformer;
import com.l2jserver.util.transformer.impl.ByteTransformer;
import com.l2jserver.util.transformer.impl.ClassTransformer;
import com.l2jserver.util.transformer.impl.DoubleTransformer;
import com.l2jserver.util.transformer.impl.FileTransformer;
import com.l2jserver.util.transformer.impl.FloatTransformer;
import com.l2jserver.util.transformer.impl.InetSocketAddressTransformer;
import com.l2jserver.util.transformer.impl.IntegerTransformer;
import com.l2jserver.util.transformer.impl.LongTransformer;
import com.l2jserver.util.transformer.impl.ShortTransformer;
import com.l2jserver.util.transformer.impl.URITransformer;
import com.l2jserver.util.transformer.impl.URLTransformer;

/**
 * The {@link TransformerFactory} return the transformer instance for any given
 * type.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TransformerFactory {
	/**
	 * return the transformer instance the given <tt>type</tt>.
	 * 
	 * @param type
	 *            the type
	 * @return the transformer
	 */
	public static final Transformer<?> getTransfromer(Class<?> type) {
		if (type == Byte.class || type == Byte.TYPE) {
			return ByteTransformer.SHARED_INSTANCE;
		} else if (type == Short.class || type == Short.TYPE) {
			return ShortTransformer.SHARED_INSTANCE;
		} else if (type == Integer.class || type == Integer.TYPE) {
			return IntegerTransformer.SHARED_INSTANCE;
		} else if (type == Long.class || type == Long.TYPE) {
			return LongTransformer.SHARED_INSTANCE;
		} else if (type == Float.class || type == Float.TYPE) {
			return FloatTransformer.SHARED_INSTANCE;
		} else if (type == Double.class || type == Double.TYPE) {
			return DoubleTransformer.SHARED_INSTANCE;
		} else if (type == Boolean.class || type == Boolean.TYPE) {
			return BooleanTransformer.SHARED_INSTANCE;
		} else if (type == InetSocketAddress.class) {
			return InetSocketAddressTransformer.SHARED_INSTANCE;
		} else if (type == File.class) {
			return FileTransformer.SHARED_INSTANCE;
		} else if (type == Class.class) {
			return ClassTransformer.SHARED_INSTANCE;
		} else if (type == URI.class) {
			return URITransformer.SHARED_INSTANCE;
		} else if (type == URL.class) {
			return URLTransformer.SHARED_INSTANCE;
		}
		return null;
	}
}
