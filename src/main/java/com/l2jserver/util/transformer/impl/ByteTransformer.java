package com.l2jserver.util.transformer.impl;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Integer} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ByteTransformer implements Transformer<Byte> {
	public static final ByteTransformer SHARED_INSTANCE = new ByteTransformer();

	@Override
	public String transform(Byte value) {
		return Double.toString(value);
	}

	@Override
	public Byte untransform(String value) {
		return Byte.decode(value);
	}

}
