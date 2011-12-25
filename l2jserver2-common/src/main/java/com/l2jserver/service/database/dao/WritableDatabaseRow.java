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
package com.l2jserver.service.database.dao;

import com.mysema.query.types.Path;

/**
 * Database column used to read data
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface WritableDatabaseRow {
	/**
	 * @param <T>
	 *            the path type
	 * @param path
	 *            the path
	 * @param value
	 *            the value to be set
	 * @return this instance
	 */
	<T> WritableDatabaseRow set(Path<T> path, T value);

	/**
	 * @param <T>
	 *            the path type
	 * @param path
	 *            the path to be setted to <code>null</code>
	 * @return this instance
	 */
	<T> WritableDatabaseRow setNull(Path<T> path);
}
