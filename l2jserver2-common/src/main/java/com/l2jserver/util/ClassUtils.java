/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * This class contains utilities that are used when we are working with classes
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ClassUtils {
	/**
	 * Return true if class a is either equivalent to class b, or if class a is
	 * a subclass of class b, i.e. if a either "extends" or "implements" b. Note
	 * that either or both "Class" objects may represent interfaces.
	 * 
	 * @param a
	 *            class
	 * @param b
	 *            class
	 * @return true if a == b or a extends b or a implements b
	 */
	public static boolean isSubclass(Class<?> a, Class<?> b) {
		// We rely on the fact that for any given java class or
		// primitive type there is a unique Class object, so
		// we can use object equivalence in the comparisons.
		if (a == b) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		for (Class<?> x = a; x != null; x = x.getSuperclass()) {
			if (x == b) {
				return true;
			}
			if (b.isInterface()) {
				Class<?> interfaces[] = x.getInterfaces();
				for (Class<?> anInterface : interfaces) {
					if (isSubclass(anInterface, b)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Recursively searches for an annotation <h1>Search order</h1>
	 * <p>
	 * <ol>
	 * <li><code>cls</code> class</li>
	 * <li><code>cls</code> implementing interfaces</code></li>
	 * <li><code>cls</code> super class</code></li>
	 * </ol>
	 * If after all those steps, no annotation is found, <code>null</code> is
	 * returned.
	 * 
	 * @param <T>
	 *            the annotation type
	 * 
	 * @param annotationClass
	 *            the annotation class
	 * @param cls
	 *            the class to start searching
	 * @return the annotation, if found.
	 */
	public static <T extends Annotation> T getAnnotation(
			Class<T> annotationClass, Class<?> cls) {
		T annotation = cls.getAnnotation(annotationClass);
		if (annotation == null) {
			for (final Class<?> interfaceCls : cls.getInterfaces()) {
				annotation = getAnnotation(annotationClass, interfaceCls);
				if (annotation != null)
					break;
			}
		}
		if (annotation == null && cls.getSuperclass() != null
				&& cls.getSuperclass() != Object.class)
			annotation = getAnnotation(annotationClass, cls.getSuperclass());
		return annotation;
	}

	/**
	 * Checks if class in member of the package
	 * 
	 * @param clazz
	 *            class to check
	 * @param packageName
	 *            package
	 * @return true if is member
	 */
	public static boolean isPackageMember(Class<?> clazz, String packageName) {
		return isPackageMember(clazz.getName(), packageName);
	}

	/**
	 * Checks if classNames belongs to package
	 * 
	 * @param className
	 *            class name
	 * @param packageName
	 *            package
	 * @return true if belongs
	 */
	public static boolean isPackageMember(String className, String packageName) {
		if (!className.contains(".")) {
			return packageName == null || packageName.isEmpty();
		} else {
			String classPackage = className.substring(0,
					className.lastIndexOf('.'));
			return packageName.equals(classPackage);
		}
	}

	/**
	 * Recursively searches for an {@link Field} within an given {@link Class}
	 * that is instance of <code>object</code> for an field with the given
	 * <code>value</code>
	 * 
	 * @param object
	 *            the object to look for the value
	 * @param value
	 *            the value to be looked for
	 * @return the field that has the correct value, if any.
	 */
	public static Field getFieldWithValue(Object object, Object value) {
		final Class<?> clazz = object.getClass();
		for (final Field field : clazz.getDeclaredFields()) {
			boolean accessible = field.isAccessible();
			try {
				field.setAccessible(true);
				if (field.get(object) == value)
					return field;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				continue;
			} finally {
				field.setAccessible(accessible);
			}
		}
		return null;
	}
}
