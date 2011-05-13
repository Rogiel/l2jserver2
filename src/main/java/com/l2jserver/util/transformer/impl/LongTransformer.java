package com.l2jserver.util.transformer.impl;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Long} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class LongTransformer implements Transformer<Long> {
	public static final LongTransformer SHARED_INSTANCE = new LongTransformer();

	@Override
	public String transform(Long value) {
		return Long.toString(value);
	}

	@Override
	public Long untransform(String value) {
		return Long.decode(value);
	}

}
