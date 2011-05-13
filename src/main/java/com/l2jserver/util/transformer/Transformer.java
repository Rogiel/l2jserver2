package com.l2jserver.util.transformer;

/**
 * An transformer can transform an {@link Object} into an {@link String} and the
 * {@link String} back to an equivalent object.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Transformer<T> {
	/**
	 * Transform the object in a string
	 * 
	 * @param value
	 *            the object
	 * @return the string of the object
	 */
	String transform(T value);

	/**
	 * Untransforms the string back to an object
	 * 
	 * @param value
	 *            the string
	 * @return the object
	 */
	T untransform(String value);
}
