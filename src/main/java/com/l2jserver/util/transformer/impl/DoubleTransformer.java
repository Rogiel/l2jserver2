package com.l2jserver.util.transformer.impl;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Integer} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DoubleTransformer implements Transformer<Double> {
	public static final DoubleTransformer SHARED_INSTANCE = new DoubleTransformer();
	
	@Override
	public String transform(Double value) {
		return Double.toString(value);
	}

	@Override
	public Double untransform(String value) {
		return Double.parseDouble(value);
	}

}
