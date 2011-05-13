package com.l2jserver.util.transformer.impl;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Integer} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class FloatTransformer implements Transformer<Float> {
	public static final FloatTransformer SHARED_INSTANCE = new FloatTransformer();

	@Override
	public String transform(Float value) {
		return Double.toString(value);
	}

	@Override
	public Float untransform(String value) {
		return Float.parseFloat(value);
	}

}
