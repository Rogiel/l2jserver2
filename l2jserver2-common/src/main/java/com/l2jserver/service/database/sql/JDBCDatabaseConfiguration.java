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

import com.l2jserver.service.configuration.XMLConfigurationService.ConfigurationXPath;
import com.l2jserver.service.database.DatabaseConfiguration;

/**
 * Configuration interface for {@link AbstractSQLDatabaseService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface JDBCDatabaseConfiguration extends DatabaseConfiguration {
	/**
	 * @return the jdbc url
	 */
	@ConfigurationPropertyGetter(defaultValue = "jdbc:mysql://localhost/l2jserver2")
	@ConfigurationXPath("connection/@url")
	String getJdbcUrl();

	/**
	 * @param jdbcUrl
	 *            the new jdbc url
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/@url")
	void setJdbcUrl(String jdbcUrl);

	/**
	 * @return the database engine class
	 */
	@ConfigurationPropertyGetter(defaultValue = "com.l2jserver.service.database.sql.MySQLDatabaseEngine")
	@ConfigurationXPath("connection/engine/@class")
	Class<? extends DatabaseEngine> getDatabaseEngineClass();

	/**
	 * @param driver
	 *            the new database engine class
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/engine/@class")
	void setDatabaseEngineClass(Class<? extends DatabaseEngine> driver);

	/**
	 * @return the jdbc database username
	 */
	@ConfigurationPropertyGetter(defaultValue = "l2j")
	@ConfigurationXPath("connection/authentication/@username")
	String getUsername();

	/**
	 * @param username
	 *            the jdbc database username
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/authentication/@username")
	void setUsername(String username);

	/**
	 * @return the jdbc database password
	 */
	@ConfigurationPropertyGetter(defaultValue = "changeme")
	@ConfigurationXPath("connection/authentication/@password")
	String getPassword();

	/**
	 * @param password
	 *            the jdbc database password
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/authentication/@password")
	void setPassword(String password);

	/**
	 * @return the maximum number of active connections
	 */
	@ConfigurationPropertyGetter(defaultValue = "20")
	@ConfigurationXPath("connection/pool/@max-active")
	int getMaxActiveConnections();

	/**
	 * @param maxActive
	 *            the maximum number of active connections
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/pool/@max-active")
	void setMaxActiveConnections(int maxActive);

	/**
	 * @return the maximum number of idle connections
	 */
	@ConfigurationPropertyGetter(defaultValue = "20")
	@ConfigurationXPath("connection/pool/@max-idle")
	int getMaxIdleConnections();

	/**
	 * @param maxIdle
	 *            the maximum number of idle connections
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/pool/@max-idle")
	void setMaxIdleConnections(int maxIdle);

	/**
	 * @return the minimum number of idle connections
	 */
	@ConfigurationPropertyGetter(defaultValue = "5")
	@ConfigurationXPath("connection/pool/@min-idle")
	int getMinIdleConnections();

	/**
	 * @param minIdle
	 *            the minimum number of idle connections
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/pool/@min-idle")
	void setMinIdleConnections(int minIdle);
}