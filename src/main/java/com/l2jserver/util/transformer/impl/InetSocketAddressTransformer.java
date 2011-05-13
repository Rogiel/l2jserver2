package com.l2jserver.util.transformer.impl;

import java.net.InetSocketAddress;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Integer} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class InetSocketAddressTransformer implements
		Transformer<InetSocketAddress> {
	public static final InetSocketAddressTransformer SHARED_INSTANCE = new InetSocketAddressTransformer();

	@Override
	public String transform(InetSocketAddress value) {
		return value.getHostName() + ":" + value.getPort();
	}

	@Override
	public InetSocketAddress untransform(String value) {
		final String[] pieces = value.split(":");
		if (pieces.length != 2)
			return null;
		return new InetSocketAddress(pieces[0], Integer.parseInt(pieces[1]));
	}

}
