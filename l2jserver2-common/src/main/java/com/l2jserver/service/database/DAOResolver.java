package com.l2jserver.service.database;

import com.l2jserver.model.Model;
import com.l2jserver.model.id.ID;

public interface DAOResolver {
	/**
	 * Returns the {@link DataAccessObject} used to retrieve and save objects of
	 * type <M>
	 * 
	 * @param <M>
	 *            the {@link Model} type
	 * @param <I>
	 *            the {@link ID} type
	 * @param modelObject
	 *            the model object
	 * @return the {@link DataAccessObject} for {@link Model}
	 */
	<I extends ID<?>, M extends Model<I>> DataAccessObject<M, I> getDAO(
			M modelObject);

	/**
	 * Returns the {@link DataAccessObject} used to retrieve and save objects of
	 * type <M>
	 * 
	 * @param <M>
	 *            the {@link Model} type
	 * @param <I>
	 *            the {@link ID} type
	 * @param modelClass
	 *            the model class
	 * @return the {@link DataAccessObject} for {@link Model}
	 */
	<M extends Model<I>, I extends ID<M>> DataAccessObject<M, I> getDAO(
			Class<M> modelClass);

	/**
	 * Returns the {@link DataAccessObject} used to retrieve and save objects of
	 * type <code>M</code> which use {@link ID} of type <code>I</code>
	 * 
	 * @param <M>
	 *            the {@link Model} type
	 * @param <I>
	 *            the {@link ID} type
	 * @param modelIdObject
	 *            the model {@link ID} object
	 * @return the {@link DataAccessObject} for {@link Model}
	 */
	<I extends ID<?>, M extends Model<I>> DataAccessObject<M, I> getDAOFromID(
			I modelIdObject);

	/**
	 * Returns the {@link DataAccessObject} used to retrieve and save objects of
	 * type <code>M</code> which use {@link ID} of type <code>I</code>
	 * 
	 * @param <M>
	 *            the {@link Model} type
	 * @param <I>
	 *            the {@link ID} type
	 * @param modelIdType
	 *            the model {@link ID} class
	 * @return the {@link DataAccessObject} for {@link Model}
	 */
	<I extends ID<?>, M extends Model<?>> DataAccessObject<M, I> getDAOFromID(
			Class<I> modelIdType);
}
