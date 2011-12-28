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
package com.l2jserver.service.database.orientdb;

import com.l2jserver.service.configuration.XMLConfigurationService.ConfigurationXPath;
import com.l2jserver.service.database.DatabaseConfiguration;

/**
 * Configuration interface for {@link AbstractOrientDatabaseService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface OrientDatabaseConfiguration extends DatabaseConfiguration {
	/**
	 * @return the orientdb url
	 */
	@ConfigurationPropertyGetter(defaultValue = "local:data/database")
	@ConfigurationXPath("connection/@url")
	String getUrl();

	/**
	 * @param url
	 *            the new orientdb url
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/@url")
	void setUrl(String url);

	/**
	 * @return the orientdb database username
	 */
	@ConfigurationPropertyGetter(defaultValue = "admin")
	@ConfigurationXPath("connection/authentication/@username")
	String getUsername();

	/**
	 * @param username
	 *            the orientdb database username
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/authentication/@username")
	void setUsername(String username);

	/**
	 * @return the orientdb database password
	 */
	@ConfigurationPropertyGetter(defaultValue = "admin")
	@ConfigurationXPath("connection/authentication/@password")
	String getPassword();

	/**
	 * @param password
	 *            the jdbc database password
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("connection/authentication/@password")
	void setPassword(String password);
}