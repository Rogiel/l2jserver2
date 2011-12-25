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

import java.net.InetSocketAddress;

import com.l2jserver.util.transformer.TransformException;
import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Integer} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class InetSocketAddressTransformer implements
		Transformer<InetSocketAddress> {
	/**
	 * This transformer shared instance
	 */
	public static final InetSocketAddressTransformer SHARED_INSTANCE = new InetSocketAddressTransformer();

	@Override
	public String transform(Class<? extends InetSocketAddress> type,
			InetSocketAddress value) {
		return value.getHostName() + ":" + value.getPort();
	}

	@Override
	public InetSocketAddress untransform(
			Class<? extends InetSocketAddress> type, String value) {
		final String[] pieces = value.split(":");
		if (pieces.length != 2)
			throw new TransformException(
					"InetSocketAddress must have format ip:port");
		return new InetSocketAddress(pieces[0], Integer.parseInt(pieces[1]));
	}

}
