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

import com.l2jserver.model.id.ID;
import com.mysema.query.sql.RelationalPathBase;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <O>
 *            the object type
 * @param <R>
 *            the raw id type
 * @param <I>
 *            the id type
 * @param <E>
 *            the table type
 */
public interface SelectMapper<O, R, I extends ID<? super R>, E extends RelationalPathBase<R>> {
	/**
	 * Reads the {@link DatabaseRow} object into an model object
	 * 
	 * @param entity
	 *            the entity object (used to execute the select query)
	 * @param row
	 *            the database row containing data
	 * @return the created object
	 */
	O select(E entity, DatabaseRow row);

	/**
	 * @param entity
	 *            the table
	 * @return an wrapper {@link PrimaryKeyMapper} that maps results into
	 *         {@link ID}s instead of objects
	 */
	SelectMapper<I, R, I, E> getIDMapper(E entity);

	/**
	 * @return the {@link PrimaryKeyMapper} that maps {@link ID}s that will be
	 *         used for caches
	 */
	PrimaryKeyMapper<I, R> getPrimaryKeyMapper();
}
