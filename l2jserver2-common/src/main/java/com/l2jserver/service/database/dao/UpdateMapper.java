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

import com.mysema.query.sql.RelationalPathBase;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <O>
 *            the object type returned by this mapper
 * @param <E>
 *            the entity type
 */
public interface UpdateMapper<O, E extends RelationalPathBase<?>> {
	/**
	 * Maps the update values to the <code>row</code>
	 * 
	 * @param e
	 *            the database table
	 * @param object
	 *            the object to be mapped
	 * @param row
	 *            the row to be mapped
	 */
	void update(E e, O object, WritableDatabaseRow row);
}
