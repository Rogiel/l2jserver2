package com.l2jserver.service.database;

import com.l2jserver.service.configuration.Configuration.ConfigurationPrefix;

@ConfigurationPrefix("mysql")
public interface MySQLDatabaseConfiguration extends DatabaseConfiguration {
	/**
	 * @return the jdbc url
	 */
	@ConfigurationPropertyGetter(name = "jdbc.url", defaultValue = "jdbc:mysql://localhost/l2jserver-gs")
	String getJdbcUrl();

	/**
	 * @param jdbcUrl
	 *            the new jdbc url
	 */
	@ConfigurationPropertySetter(name = "jdbc.url")
	void setJdbcUrl(String jdbcUrl);

	@ConfigurationPropertyGetter(name = "jdbc.username", defaultValue = "root")
	String getUsername();

	@ConfigurationPropertySetter(name = "jdbc.username")
	void setUsername(String username);

	@ConfigurationPropertyGetter(name = "jdbc.password")
	String getPassword();

	@ConfigurationPropertySetter(name = "jdbc.password")
	void setPassword(String password);
}
