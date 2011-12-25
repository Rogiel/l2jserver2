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

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

import com.l2jserver.service.database.ddl.QueryTemplate;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.types.Type;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface DatabaseEngine {
	/**
	 * @return an newly created {@link Driver} instance
	 * @throws SQLException
	 *             any sql exception thrown by the driver
	 */
	Driver newDriver() throws SQLException;

	/**
	 * @param conn
	 *            the connection
	 * @return the {@link SQLQueryFactory} instance for the given connection
	 */
	SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> createSQLQueryFactory(
			Connection conn);

	/**
	 * @param type
	 *            the type to be registered
	 */
	void registerType(Type<?> type);

	/**
	 * @return the {@link QueryTemplate} used to create and alter tables
	 */
	QueryTemplate getTemplate();
}
