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
package com.l2jserver.db.dao.mysql5;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.id.ID;
import com.l2jserver.service.database.AbstractDAO;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.JDBCDatabaseService;

/**
 * {@link AbstractDAO} for MySQL DAO implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the object for the DAO
 * @param <I>
 *            the object ID type
 */
public abstract class AbstractMySQL5DAO<T extends Model<?>, I extends ID<?>>
		extends AbstractDAO<T, I> {
	/**
	 * The MySQL Database Service
	 */
	protected final JDBCDatabaseService database;

	@Inject
	protected AbstractMySQL5DAO(DatabaseService database) {
		super(database);
		this.database = (JDBCDatabaseService) database;
	}
}
