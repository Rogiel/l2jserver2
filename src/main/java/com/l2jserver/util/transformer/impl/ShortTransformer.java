package com.l2jserver.util.transformer.impl;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Integer} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ShortTransformer implements Transformer<Short> {
	public static final ShortTransformer SHARED_INSTANCE = new ShortTransformer();

	@Override
	public String transform(Short value) {
		return Short.toString(value);
	}

	@Override
	public Short untransform(String value) {
		return Short.decode(value);
	}

}
