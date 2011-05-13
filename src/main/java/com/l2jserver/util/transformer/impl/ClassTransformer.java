package com.l2jserver.util.transformer.impl;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Class} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ClassTransformer implements Transformer<Class<?>> {
	public static final ClassTransformer SHARED_INSTANCE = new ClassTransformer();

	@Override
	public String transform(Class<?> value) {
		return value.getName();
	}

	@Override
	public Class<?> untransform(String value) {
		try {
			return Class.forName(value);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

}
