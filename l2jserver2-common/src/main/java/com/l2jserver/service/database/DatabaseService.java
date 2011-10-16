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
package com.l2jserver.service.database;

import com.l2jserver.service.Service;
import com.l2jserver.service.ServiceConfiguration;
import com.l2jserver.service.configuration.Configuration;
import com.l2jserver.service.configuration.ProxyConfigurationService.ConfigurationName;

/**
 * This service provides access to an database implementation. Each
 * implementation had its own {@link DataAccessObject} model, however they do
 * need to respect {@link DataAccessObject} interfaces located in "
 * <tt>com.l2jserver.db.dao</tt>". There can be several service implementation,
 * but only one of them can be active at an given time.
 * 
 * The service does not directly provide much functionality most of its
 * operations are done trough an {@link DataAccessObject}. Each service
 * implementation provides an custom interface that is used to link
 * {@link DataAccessObject}-{@link DatabaseService Service}. See implementation
 * specific documentation for more information.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface DatabaseService extends Service {
	/**
	 * Database service configuration
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * @see Configuration
	 */
	@ConfigurationName("database")
	public interface DatabaseConfiguration extends ServiceConfiguration {
	}
}
