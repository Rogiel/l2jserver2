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

import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService;

/**
 * This is an implementation of {@link DatabaseService} that provides an layer
 * to JDBC.
 * 
 * <h1>Internal specification</h1> <h2>The
 * {@link com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.Query
 * Query} object</h2>
 * 
 * If you wish to implement a new {@link DataAccessObject} you should try not
 * use
 * {@link com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.Query
 * Query} object directly because it only provides low level access to the JDBC
 * architecture. Instead, you could use an specialized class, like
 * {@link com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.InsertQuery
 * InsertUpdateQuery} ,
 * {@link com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.SelectListQuery
 * SelectListQuery} or
 * {@link com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.SelectSingleQuery
 * SelectSingleQuery} . If you do need low level access, feel free to use the
 * {@link com.l2jserver.service.database.jdbc.AbstractJDBCDatabaseService.Query
 * Query} class directly.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, CacheService.class,
		ConfigurationService.class, ThreadService.class })
public class LoginServerJDBCDatabaseService extends AbstractJDBCDatabaseService
		implements DatabaseService {
	/**
	 * @param configService
	 *            the config service
	 * @param cacheService
	 *            the cache service
	 * @param threadService
	 *            the thread service
	 * @param daoResolver
	 *            the {@link DataAccessObject DAO} resolver
	 */
	public LoginServerJDBCDatabaseService(ConfigurationService configService,
			CacheService cacheService, ThreadService threadService,
			DAOResolver daoResolver) {
		super(configService, cacheService, threadService, daoResolver);
	}
}
