package com.l2jserver.util.transformer.impl;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Integer} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class IntegerTransformer implements Transformer<Integer> {
	public static final IntegerTransformer SHARED_INSTANCE = new IntegerTransformer();

	@Override
	public String transform(Integer value) {
		return Integer.toString(value);
	}

	@Override
	public Integer untransform(String value) {
		return Integer.decode(value);
	}

}
