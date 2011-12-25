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
package com.l2jserver.service.database.sql;

import com.l2jserver.service.database.dao.WritableDatabaseRow;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Path;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class SQLInsertWritableDatabaseRow implements WritableDatabaseRow {
	/**
	 * The SQL <code>INSERT</code> clause
	 */
	private final SQLInsertClause clause;

	/**
	 * @param clause
	 *            the insert clause
	 */
	public SQLInsertWritableDatabaseRow(SQLInsertClause clause) {
		this.clause = clause;
	}

	@Override
	public <T> WritableDatabaseRow set(Path<T> path, T value) {
		clause.set(path, value);
		return this;
	}

	@Override
	public <T> WritableDatabaseRow setNull(Path<T> path) {
		return set(path, null);
	}

	/**
	 * @return the insert clause
	 */
	public SQLInsertClause getClause() {
		return clause;
	}
}
