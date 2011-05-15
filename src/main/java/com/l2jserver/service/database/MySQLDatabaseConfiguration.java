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
 * Configuration interface for {@link MySQLDatabaseService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface MySQLDatabaseConfiguration extends DatabaseConfiguration {
	/**
	 * @return the jdbc url
	 */
	@ConfigurationPropertyGetter(name = "jdbc.mysql.url", defaultValue = "jdbc:mysql://localhost/l2jserver2")
	String getJdbcUrl();

	/**
	 * @param jdbcUrl
	 *            the new jdbc url
	 */
	@ConfigurationPropertySetter(name = "jdbc.mysql.url")
	void setJdbcUrl(String jdbcUrl);

	/**
	 * @return the jdbc driver class
	 */
	@ConfigurationPropertyGetter(name = "jdbc.mysql.driver", defaultValue = "com.mysql.jdbc.Driver")
	String getDriver();

	/**
	 * @param driver
	 *            the new jdbc driver
	 */
	@ConfigurationPropertySetter(name = "jdbc.mysql.driver")
	void setDriver(Class<?> driver);

	/**
	 * @return the mysql database username
	 */
	@ConfigurationPropertyGetter(name = "jdbc.mysql.username", defaultValue = "l2j")
	String getUsername();

	/**
	 * @param username
	 *            the mysql database username
	 */
	@ConfigurationPropertySetter(name = "jdbc.mysql.username")
	void setUsername(String username);

	/**
	 * @return the mysql database password
	 */
	@ConfigurationPropertyGetter(name = "jdbc.mysql.password", defaultValue = "changeme")
	String getPassword();

	/**
	 * @param password
	 *            the mysql database password
	 */
	@ConfigurationPropertySetter(name = "jdbc.mysql.password")
	void setPassword(String password);
}
