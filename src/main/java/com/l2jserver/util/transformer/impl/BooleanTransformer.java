package com.l2jserver.util.transformer.impl;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Boolean} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BooleanTransformer implements Transformer<Boolean> {
	public static final BooleanTransformer SHARED_INSTANCE = new BooleanTransformer();

	@Override
	public String transform(Boolean value) {
		return (value ? "true" : "false");
	}

	@Override
	public Boolean untransform(String value) {
		return Boolean.parseBoolean(value);
	}

}
