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

import javax.inject.Provider;

import org.apache.derby.jdbc.EmbeddedDriver;

import com.l2jserver.service.database.sql.ddl.QueryTemplate;
import com.l2jserver.service.database.sql.ddl.template.DerbyTemplate;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.SQLQueryFactoryImpl;
import com.mysema.query.sql.types.Type;

/**
 * This database provider gives access to MySQL5 databases
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DerbyDatabaseEngine implements DatabaseEngine {
	private final DerbyTemplate template = new DerbyTemplate();
	private final Configuration configuration = new Configuration(template);

	@Override
	public SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> createSQLQueryFactory(
			final Connection conn) {
		return new SQLQueryFactoryImpl(configuration, new Provider<Connection>() {
			@Override
			public Connection get() {
				return conn;
			}
		});
	}

	@Override
	public void registerType(Type<?> type) {
		configuration.register(type);
	}

	@Override
	public QueryTemplate getTemplate() {
		return template;
	}

	@Override
	public Driver newDriver() throws SQLException {
		return new EmbeddedDriver();
	}

}
