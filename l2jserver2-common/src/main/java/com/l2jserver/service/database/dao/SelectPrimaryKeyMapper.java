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
import com.mysema.query.types.Path;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <R>
 *            the primary key raw type
 * @param <I>
 *            the ID type
 * @param <E>
 *            the table type
 */
public class SelectPrimaryKeyMapper<R, I extends ID<? super R>, E extends RelationalPathBase<R>>
		implements SelectMapper<I, R, I, E> {
	/**
	 * The primary key mapper
	 */
	private final PrimaryKeyMapper<I, R> mapper;
	/**
	 * The primary key path
	 */
	private final Path<R> path;

	/**
	 * @param mapper
	 *            the {@link PrimaryKeyMapper}
	 * @param path
	 *            the {@link Path} to the primary key
	 */
	public SelectPrimaryKeyMapper(PrimaryKeyMapper<I, R> mapper, Path<R> path) {
		this.mapper = mapper;
		this.path = path;
	}

	@Override
	public I select(E entity, DatabaseRow row) {
		return mapper.createID(row.get(path));
	}

	@Override
	public SelectMapper<I, R, I, E> getIDMapper(E entity) {
		return this;
	}

	@Override
	public PrimaryKeyMapper<I, R> getPrimaryKeyMapper() {
		return mapper;
	}

}
