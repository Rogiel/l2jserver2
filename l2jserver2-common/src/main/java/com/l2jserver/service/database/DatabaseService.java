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

import com.l2jserver.model.Model;
import com.l2jserver.model.id.ID;
import com.l2jserver.service.Service;
import com.l2jserver.service.ServiceConfiguration;
import com.l2jserver.service.configuration.Configuration;
import com.l2jserver.service.core.threading.AsyncFuture;

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
	public interface DatabaseConfiguration extends ServiceConfiguration {
	}

	/**
	 * Executes several operations inside a single database transaction.
	 * <p>
	 * Queries inside a transaction applies to an <i>all-or-none</i> model. If
	 * any of the queries executed fails, none of them will be persisted to the
	 * database and no changes will be performed. Transactions are useful in
	 * maintaining data consistency.
	 * <p>
	 * <b>Important</b>: You should <b>never</b> call any async
	 * {@link DataAccessObject} within a transaction. Doing so, will make it be
	 * executed in <b>another transaction</b> and might even cause data
	 * corruption due to queries being executed in different transactions.
	 * <p>
	 * If you wish to execute an transaction asynchronously, see
	 * {@link #transactionAsync(TransactionExecutor)}.
	 * 
	 * @param executor
	 *            the executor implementation (normally an anonymous class)
	 * @return the number of affected rows
	 * @see DatabaseService#transactionAsync(TransactionExecutor)
	 */
	int transaction(TransactionExecutor executor);

	/**
	 * Asynchronously executes several operations inside a single database
	 * transaction.
	 * <p>
	 * Queries inside a transaction applies to an<i>all-or-none</i> model. If
	 * any of the queries executed fails, none of them will be persisted to the
	 * database and no changes will be performed. Transactions are useful in
	 * maintaining data consistency.
	 * 
	 * @param executor
	 *            the executor implementation (normally an anonymous class)
	 * @return the number of affected rows
	 */
	AsyncFuture<Integer> transactionAsync(TransactionExecutor executor);

	/**
	 * This class executes DAO operations inside an transaction. It is
	 * recommended to implement it in an anonymous class.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface TransactionExecutor {
		/**
		 * Perform operations inside the transaction.
		 * 
		 * @return the number of affected rows
		 */
		int perform();
	}

	/**
	 * Checks for the cached version of the object
	 * 
	 * @param <I>
	 *            the {@link ID} type
	 * @param <O>
	 *            the {@link Model} type
	 * 
	 * @param id
	 *            the object ID
	 * @return the cached version, if any
	 */
	<I extends ID<?>, O extends Model<?>> Object getCachedObject(I id);

	/**
	 * Checks for the cached version of the object
	 * 
	 * @param <I>
	 *            the {@link ID} type
	 * @param <O>
	 *            the {@link Model} type
	 * 
	 * @param id
	 *            the object ID
	 * @return true if has an cached version,
	 */
	<I extends ID<?>, O extends Model<?>> boolean hasCachedObject(I id);

	/**
	 * Updates an cache object
	 * 
	 * @param <I>
	 *            the {@link ID} type
	 * @param <O>
	 *            the {@link Model} type
	 * 
	 * @param id
	 *            the cache key
	 * @param value
	 *            the model value
	 */
	<I extends ID<?>, O extends Model<?>> void updateCache(I id, O value);

	/**
	 * Removes an cached object
	 * 
	 * @param <I>
	 *            the {@link ID} type
	 * @param <O>
	 *            the {@link Model} type
	 * 
	 * @param id
	 *            the object id
	 */
	<I extends ID<?>, O extends Model<?>> void removeCache(I id);
}
