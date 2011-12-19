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

import java.util.Collection;
import java.util.Iterator;

import com.l2jserver.model.Model;
import com.l2jserver.model.id.ID;
import com.l2jserver.service.core.threading.AsyncFuture;
import com.l2jserver.service.database.DatabaseService.TransactionExecutor;

/**
 * The Data Access Object interface used used to retrieve, save and remove
 * objects from the database. The underlying storage engine can be an plain text
 * file, SQL Database or an serialized version of the object. This layer will
 * abstract the translation of the data and ease the transition from one engine
 * to another.
 * <p>
 * Every DAO is also an {@link Iterable}. If you wish you can iterate through
 * all objects in the database very abstractly. But please note that the default
 * {@link Iterator} implementation in {@link AbstractDAO} will load all the
 * {@link ID} objects and for every call {@link Iterator#next()}, a new database
 * query will be made requesting the given object. In a large dataset, this
 * could be a huge performance issue. DAO implementations are encouraged to
 * override the iterator implementation with a more specific implementation.
 * <p>
 * <h1>Transactions</h1>
 * {@link DatabaseService} implementations are required to implement
 * {@link DatabaseService#transaction(com.l2jserver.service.database.DatabaseService.TransactionExecutor)}
 * which can allow you to perform several operations within a single database
 * transaction and have consistency guarantee. Since not all services might
 * support transactions, if transaction is not supported, the system degrades
 * gracefully and executes the operations outside an transaction. See below an
 * example of how to execute in-transaction operations:
 * 
 * <pre>
 * final {@link DataAccessObject}<ObjectType1> anyDao1 = ...;
 * final {@link DataAccessObject}<ObjectType2> anyDao2 = ...;
 * final int rows = {@link DatabaseService databaseService}.{@link DatabaseService#transaction(com.l2jserver.service.database.DatabaseService.TransactionExecutor) transaction}(new {@link TransactionExecutor}() {
 * 	public int {@link TransactionExecutor#perform() perform}() {
 * 		int rows = anyDao1.{@link #insert(Model) insert}(object1);
 * 		rows += anyDao2.{@link #delete(Model) delete}(object2);
 * 		return rows;
 * 	}
 * });
 * </pre>
 * 
 * If any any operation inside the transaction fails, none of them will be
 * commited to the database and its state will be restored previus to the
 * execution of the transaction.
 * <p>
 * <h2>Important note about asynchronous transactions</h2>
 * You should not use any of the {@link #insertObjectsAsync(Model...)},
 * {@link #updateObjectsAsync(Model...)}, {@link #deleteObjectsAsync(Model...)}
 * or {@link #selectAsync(ID)} methods within an transaction. Doing so, will not
 * result in exception, though all operations will be performed <b>outside</b>
 * the transaction and might not be performed in the declared order. You should,
 * instead, make a call to
 * {@link DatabaseService#transactionAsync(com.l2jserver.service.database.DatabaseService.TransactionExecutor)}
 * in order to execute transactions in an asynchronous manner. Remember, even in
 * asynchronous transactions, you should use the synchronous DAO methods. If you
 * wish to execute the previus transactions in a asynchronous manner, all you
 * need to do is change the <code>transaction</code> method, as shown below:
 * 
 * <pre>
 * final {@link DataAccessObject}<ObjectType1> anyDao1 = ...;
 * final {@link DataAccessObject}<ObjectType2> anyDao2 = ...;
 * final {@link AsyncFuture}<Integer> f = {@link DatabaseService databaseService}.{@link DatabaseService#transactionAsync(com.l2jserver.service.database.DatabaseService.TransactionExecutor) transactionAsync}(new {@link TransactionExecutor}() {
 * 	public int {@link TransactionExecutor#perform() perform}() {
 * 		// note the use of synchronous operations here!
 * 		int rows = anyDao1.{@link #insert(Model) insert}(object1);
 * 		rows += anyDao2.{@link #delete(Model) delete}(object2);
 * 		return rows;
 * 	}
 * });
 * final int rows = f.{@link AsyncFuture#get() get}();
 * </pre>
 * 
 * @param <O>
 *            the {@link Model} supported by this DAO
 * @param <I>
 *            the ID used to represent the {@link Model} in this DAO
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public interface DataAccessObject<O extends Model<?>, I extends ID<?>> extends
		Iterable<O> {
	/**
	 * Load the instance represented by <tt>id</tt> from the database
	 * 
	 * @param id
	 *            the id
	 * @return the selected object. <tt>null</tt> if could not be found in the
	 *         database.
	 */
	O select(I id);

	/**
	 * Asynchronously load the instance represented by <tt>id</tt> from the
	 * database
	 * 
	 * @param id
	 *            the id
	 * @return the {@link AsyncFuture} that will load the selected object.
	 *         {@link AsyncFuture} might return <tt>null</tt> if the object
	 *         could not be found in the database.
	 */
	AsyncFuture<O> selectAsync(I id);

	/**
	 * Loads an List of all {@link ID}s in the database
	 * 
	 * @return the list containing all {@link ID} objects
	 */
	Collection<I> selectIDs();

	/**
	 * Save the instance to the database. This method will only save if the
	 * object has changed.
	 * 
	 * @param object
	 *            the object
	 * @return the number of affected rows
	 * @see DataAccessObject#save(Model, boolean)
	 */
	int save(O object);

	/**
	 * Save several instances to the database using a transaction (if possible).
	 * This method will only save if the object has changed.
	 * 
	 * @param objects
	 *            the objects
	 * @return the number of affected rows
	 */
	int saveObjects(@SuppressWarnings("unchecked") O... objects);

	/**
	 * Asynchronously save several instances to the database using a transaction
	 * (if possible). This method will only save if the object has changed.
	 * 
	 * @param objects
	 *            the objects
	 * @return the task future. The future returns an Integer with the number of
	 *         affected rows.
	 */
	AsyncFuture<Integer> saveObjectsAsync(
			@SuppressWarnings("unchecked") O... objects);

	/**
	 * Save the instance to the database. If a new database entry was created
	 * returns true.
	 * 
	 * @param object
	 *            the object
	 * @param force
	 *            will force an save, even if the object has not changed
	 * @return the number of affected rows
	 */
	int save(O object, boolean force);

	/**
	 * Inserts the instance in the database.
	 * 
	 * @param object
	 *            the object
	 */
	void insert(O object);

	/**
	 * Inserts several instances in the database using a transaction (if
	 * possible).
	 * 
	 * @param objects
	 *            the objects
	 * @return the number of inserted rows
	 */
	int insertObjects(@SuppressWarnings("unchecked") O... objects);

	/**
	 * Asynchronously insert several instances in the database using a
	 * transaction (if possible).
	 * 
	 * @param objects
	 *            the objects
	 * @return the task future. The future returns an Integer with the number of
	 *         inserted rows.
	 */
	AsyncFuture<Integer> insertObjectsAsync(
			@SuppressWarnings("unchecked") O... objects);

	/**
	 * Updates the instance in the database.
	 * 
	 * @param object
	 *            the object
	 */
	void update(O object);

	/**
	 * Updates several instances in the database using a transaction (if
	 * possible).
	 * 
	 * @param objects
	 *            the objects
	 * @return the number of updated rows
	 */
	int updateObjects(@SuppressWarnings("unchecked") O... objects);

	/**
	 * Asynchronously update several instances in the database using a
	 * transaction (if possible).
	 * 
	 * @param objects
	 *            the objects
	 * @return the task future. The future returns an Integer with the number of
	 *         updated rows.
	 */
	AsyncFuture<Integer> updateObjectsAsync(
			@SuppressWarnings("unchecked") O... objects);

	/**
	 * Deletes the instance in the database.
	 * 
	 * @param object
	 *            the object
	 */
	void delete(O object);

	/**
	 * Deletes several instances in the database using an transaction (if
	 * possible).
	 * 
	 * @param objects
	 *            the objects
	 * @return the number of deleted rows
	 */
	int deleteObjects(@SuppressWarnings("unchecked") O... objects);

	/**
	 * Asynchronously delete several instances in the database using a
	 * transaction (if possible).
	 * 
	 * @param objects
	 *            the objects
	 * @return the task future. The future returns an Integer with the number of
	 *         deleted rows.
	 */
	AsyncFuture<Integer> deleteObjectsAsync(
			@SuppressWarnings("unchecked") O... objects);
}
