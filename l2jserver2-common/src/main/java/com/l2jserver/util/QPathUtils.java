/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.util;

import java.lang.reflect.Field;

import com.l2jserver.service.database.ddl.annotation.ColumnAutoIncrement;
import com.l2jserver.service.database.ddl.annotation.ColumnDefault;
import com.l2jserver.service.database.ddl.annotation.ColumnNullable;
import com.l2jserver.service.database.ddl.annotation.ColumnSize;
import com.l2jserver.util.transformer.Transformer;
import com.l2jserver.util.transformer.TransformerFactory;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;

/**
 * Class serving several utilities for {@link Path} and {@link RelationalPath}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QPathUtils {
	/**
	 * Returns the {@link Path} represented by <code>name</code> within the
	 * given {@link RelationalPath}
	 * 
	 * @param relationalPath
	 *            the {@link RelationalPath}
	 * @param name
	 *            the {@link Path} name
	 * @return the {@link Path} instance, if existent
	 */
	public static Path<?> getPath(RelationalPath<?> relationalPath, String name) {
		for (final Path<?> path : relationalPath.getColumns()) {
			if (path.getMetadata().getExpression().toString().equals(name))
				return path;
		}
		return null;
	}

	/**
	 * @param path
	 *            the {@link Path} to be searched in its root entity
	 * @return the {@link Field} holding <code>path</code>
	 */
	public static Field getReflectionField(Path<?> path) {
		return ClassUtils.getFieldWithValue(path.getMetadata().getParent(), path);
	}

	/**
	 * @param relationalPath
	 *            the {@link RelationalPath}
	 * @param pathName
	 *            the {@link Path} name
	 * @return the {@link Field} holding <code>pathName</code>
	 * @see #getPath(RelationalPath, String)
	 */
	public static Field getRefelctionField(RelationalPath<?> relationalPath,
			String pathName) {
		return ClassUtils.getFieldWithValue(relationalPath,
				getPath(relationalPath, pathName));
	}

	/**
	 * @param path
	 *            the {@link Path}
	 * @return the column maximum size
	 */
	public static int getColumnSize(Path<?> path) {
		final Field field = getReflectionField(path);
		if(field == null)
			return 0;
		final ColumnSize size = field.getAnnotation(ColumnSize.class);
		if (size != null)
			return size.value();
		return -1;
	}

	/**
	 * @param path
	 *            the {@link Path}
	 * @return whether the column is auto incrementable or not
	 */
	public static boolean isAutoIncrementable(Path<?> path) {
		final Field field = getReflectionField(path);
		if(field == null)
			return false;
		final ColumnAutoIncrement autoInc = field
				.getAnnotation(ColumnAutoIncrement.class);
		if (autoInc != null)
			return true;
		return false;
	}

	/**
	 * @param path
	 *            the {@link Path}
	 * @return whether the column can be <code>null</code>
	 */
	public static boolean isNullable(Path<?> path) {
		final Field field = getReflectionField(path);
		if(field == null)
			return false;
		final ColumnNullable nullable = field
				.getAnnotation(ColumnNullable.class);
		if (nullable == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param path
	 *            the {@link Path}
	 * @return <code>true</code> if the column has an default value
	 */
	public static boolean hasDefaultValue(Path<?> path) {
		final Field field = getReflectionField(path);
		if(field == null)
			return false;
		final ColumnDefault def = field.getAnnotation(ColumnDefault.class);
		if (def == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @param path
	 *            the {@link Path}
	 * @return the column default value
	 */
	public static String getDefaultUntransformedValue(Path<?> path) {
		final Field field = getReflectionField(path);
		if(field == null)
			return null;
		final ColumnDefault def = field.getAnnotation(ColumnDefault.class);
		if (def != null)
			return def.value();
		return null;
	}

	/**
	 * @param <T>
	 *            the column return type
	 * @param path
	 *            the {@link Path}
	 * @return the column default value (already transformed)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDefaultValue(Path<T> path) {
		@SuppressWarnings("rawtypes")
		final Transformer transformer = TransformerFactory.getTransfromer(path
				.getType());
		return (T) transformer.untransform(path.getType(),
				getDefaultUntransformedValue(path));
	}
}
