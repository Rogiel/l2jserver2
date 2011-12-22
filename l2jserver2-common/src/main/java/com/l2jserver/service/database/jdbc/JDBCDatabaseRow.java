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
package com.l2jserver.service.database.jdbc;

import java.util.List;

import com.l2jserver.service.database.dao.DatabaseRow;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.Path;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class JDBCDatabaseRow implements DatabaseRow {
	@SuppressWarnings("unused")
	private final RelationalPathBase<?> entity;
	private final List<Path<?>> paths;

	private Object[] row;

	/**
	 * @param row
	 *            the database row data
	 * @param entity
	 *            the entity
	 */
	public JDBCDatabaseRow(Object[] row, RelationalPathBase<?> entity) {
		this.row = row;
		this.entity = entity;
		paths = entity.getColumns();
	}

	/**
	 * @param entity
	 *            the entity
	 */
	public JDBCDatabaseRow(RelationalPathBase<?> entity) {
		this.entity = entity;
		paths = entity.getColumns();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Path<T> path) {
		// this cast should be safe!
		return (T) row[indexOf(path)];
	}

	@Override
	public <T> boolean isNull(Path<T> path) {
		return row[indexOf(path)] == null;
	}

	private int indexOf(Path<?> path) {
		return paths.indexOf(path);
	}

	public void setRow(Object[] row) {
		this.row = row;
	}
}
