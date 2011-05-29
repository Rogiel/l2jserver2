/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.database;

/**
 * Configuration interface for {@link JDBCDatabaseService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface JDBCDatabaseConfiguration extends DatabaseConfiguration {
	/**
	 * @return the jdbc url
	 */
	@ConfigurationPropertyGetter(name = "jdbc.url", defaultValue = "jdbc:mysql://localhost/l2jserver2")
	String getJdbcUrl();

	/**
	 * @param jdbcUrl
	 *            the new jdbc url
	 */
	@ConfigurationPropertySetter(name = "jdbc.url")
	void setJdbcUrl(String jdbcUrl);

	/**
	 * @return the jdbc driver class
	 */
	@ConfigurationPropertyGetter(name = "jdbc.driver", defaultValue = "com.jdbc.jdbc.Driver")
	String getDriver();

	/**
	 * @param driver
	 *            the new jdbc driver
	 */
	@ConfigurationPropertySetter(name = "jdbc.driver")
	void setDriver(Class<?> driver);

	/**
	 * @return the jdbc database username
	 */
	@ConfigurationPropertyGetter(name = "jdbc.username", defaultValue = "l2j")
	String getUsername();

	/**
	 * @param username
	 *            the jdbc database username
	 */
	@ConfigurationPropertySetter(name = "jdbc.username")
	void setUsername(String username);

	/**
	 * @return the jdbc database password
	 */
	@ConfigurationPropertyGetter(name = "jdbc.password", defaultValue = "changeme")
	String getPassword();

	/**
	 * @param password
	 *            the jdbc database password
	 */
	@ConfigurationPropertySetter(name = "jdbc.password")
	void setPassword(String password);

	/**
	 * @return the maximum number of active connections
	 */
	@ConfigurationPropertyGetter(name = "jdbc.active.max", defaultValue = "20")
	int getMaxActiveConnections();

	/**
	 * @param password
	 *            the maximum number of active connections
	 */
	@ConfigurationPropertySetter(name = "jdbc.active.max")
	void setMaxActiveConnections(int password);
	
	/**
	 * @return the maximum number of idle connections
	 */
	@ConfigurationPropertyGetter(name = "jdbc.idle.max", defaultValue = "20")
	int getMaxIdleConnections();

	/**
	 * @param password
	 *            the maximum number of idle connections
	 */
	@ConfigurationPropertySetter(name = "jdbc.idle.max")
	void setMaxIdleConnections(int password);
	
	/**
	 * @return the minimum number of idle connections
	 */
	@ConfigurationPropertyGetter(name = "jdbc.idle.min", defaultValue = "5")
	int getMinIdleConnections();

	/**
	 * @param password
	 *            the minimum number of idle connections
	 */
	@ConfigurationPropertySetter(name = "jdbc.idle.min")
	void setMinIdleConnections(int password);
}
