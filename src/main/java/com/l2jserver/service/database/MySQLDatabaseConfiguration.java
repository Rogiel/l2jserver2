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
