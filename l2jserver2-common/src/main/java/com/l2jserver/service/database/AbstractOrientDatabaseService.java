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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.Model.ObjectDesire;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.cache.Cache;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.configuration.XMLConfigurationService.ConfigurationXPath;
import com.l2jserver.service.core.threading.AbstractTask;
import com.l2jserver.service.core.threading.AsyncFuture;
import com.l2jserver.service.core.threading.ScheduledAsyncFuture;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.util.ArrayIterator;
import com.l2jserver.util.factory.CollectionFactory;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNativeSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * This is an implementation of {@link DatabaseService} that provides an layer
 * to JDBC.
 * 
 * <h1>Internal specification</h1> <h2>The {@link Query} object</h2>
 * 
 * If you wish to implement a new {@link DataAccessObject} you should try not
 * use {@link Query} object directly because it only provides low level access
 * to the JDBC architecture. Instead, you could use an specialized class, like
 * {@link InsertUpdateQuery}, {@link SelectListQuery} or
 * {@link SelectSingleQuery}. If you do need low level access, feel free to use
 * the {@link Query} class directly.
 * 
 * <h2>The {@link Mapper} object</h2>
 * 
 * The {@link Mapper} object maps an JDBC {@link ResultSet} into an Java
 * {@link Object}. All {@link Model} objects support {@link CachedMapper} that
 * will cache result based on its {@link ID} and always use the same object with
 * the same {@link ID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AbstractOrientDatabaseService extends AbstractService
		implements DatabaseService {
	/**
	 * The configuration object
	 */
	private final OrientDatabaseConfiguration config;
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory
			.getLogger(AbstractOrientDatabaseService.class);

	/**
	 * The cache service
	 */
	private final CacheService cacheService;
	/**
	 * The thread service
	 */
	private final ThreadService threadService;
	/**
	 * The {@link DAOResolver} instance
	 */
	private final DAOResolver daoResolver;

	private ODatabaseDocumentTx database;

	/**
	 * An cache object
	 */
	private Cache<Object, Model<?>> objectCache;
	/**
	 * Future for the auto-save task. Each object that has changed is auto saved
	 * every 1 minute.
	 */
	private ScheduledAsyncFuture autoSaveFuture;

	/**
	 * Configuration interface for {@link AbstractOrientDatabaseService}.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface OrientDatabaseConfiguration extends DatabaseConfiguration {
		/**
		 * @return the orientdb url
		 */
		@ConfigurationPropertyGetter(defaultValue = "file:data/database")
		@ConfigurationXPath("/configuration/services/database/orientdb/url")
		String getUrl();

		/**
		 * @param url
		 *            the new orientdb url
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/orientdb/url")
		void setUrl(String url);

		/**
		 * @return the orientdb database username
		 */
		@ConfigurationPropertyGetter(defaultValue = "l2j")
		@ConfigurationXPath("/configuration/services/database/orientdb/username")
		String getUsername();

		/**
		 * @param username
		 *            the orientdb database username
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/orientdb/username")
		void setUsername(String username);

		/**
		 * @return the orientdb database password
		 */
		@ConfigurationPropertyGetter(defaultValue = "changeme")
		@ConfigurationXPath("/configuration/services/database/orientdb/password")
		String getPassword();

		/**
		 * @param password
		 *            the jdbc database password
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/password")
		void setPassword(String password);
	}

	/**
	 * @param configService
	 *            the configuration service
	 * @param cacheService
	 *            the cache service
	 * @param threadService
	 *            the thread service
	 * @param daoResolver
	 *            the {@link DataAccessObject DAO} resolver
	 */
	@Inject
	public AbstractOrientDatabaseService(ConfigurationService configService,
			CacheService cacheService, ThreadService threadService,
			DAOResolver daoResolver) {
		config = configService.get(OrientDatabaseConfiguration.class);
		this.cacheService = cacheService;
		this.threadService = threadService;
		this.daoResolver = daoResolver;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		database = new ODatabaseDocumentTx(config.getUrl());
		if (!database.exists()) {
			database.create();
		} else {
			database.open(config.getUsername(), config.getPassword());
		}

		// cache must be large enough for all world objects, to avoid
		// duplication... this would endanger non-persistent states
		objectCache = cacheService.createEternalCache("database-service",
				IDAllocator.ALLOCABLE_IDS);

		// start the auto save task
		autoSaveFuture = threadService.async(60, TimeUnit.SECONDS, 60,
				new Runnable() {
					@Override
					public void run() {
						log.debug("Auto save task started");
						int objects = 0;
						for (final Model<?> object : objectCache) {
							@SuppressWarnings("unchecked")
							final DataAccessObject<Model<?>, ?> dao = daoResolver
									.getDAO(object.getClass());
							if (dao.save(object) > 0) {
								objects++;
							}
						}
						log.info(
								"{} objects have been saved by the auto save task",
								objects);
					}
				});
	}

	@Override
	public int transaction(TransactionExecutor executor) {
		return executor.perform();
	}

	@Override
	public AsyncFuture<Integer> transactionAsync(
			final TransactionExecutor executor) {
		return threadService.async(new AbstractTask<Integer>() {
			@Override
			public Integer call() throws Exception {
				return transaction(executor);
			}
		});
	}

	/**
	 * Executes an <tt>query</tt> in the database.
	 * 
	 * @param <T>
	 *            the query return type
	 * @param query
	 *            the query
	 * @return an instance of <tt>T</tt>
	 */
	public <T> T query(Query<T> query) {
		Preconditions.checkNotNull(query, "query");
		log.debug("Executing query {} with {}", query, database);
		try {
			return query.query(database);
		} catch (SQLException e) {
			log.error("Database error", e);
			return null;
		}
	}

	/**
	 * Checks for the cached version of the object
	 * 
	 * @param id
	 *            the object ID
	 * @return the cached version, if any
	 */
	public Object getCachedObject(Object id) {
		Preconditions.checkNotNull(id, "id");
		log.debug("Fetching cached object {}", id);
		return objectCache.get(id);
	}

	/**
	 * Checks for the cached version of the object
	 * 
	 * @param id
	 *            the object ID
	 * @return true if has an cached version,
	 */
	public boolean hasCachedObject(Object id) {
		Preconditions.checkNotNull(id, "id");
		log.debug("Locating cached object {}", id);
		return objectCache.contains(id);
	}

	/**
	 * Updates an cache object
	 * 
	 * @param id
	 *            the cache key
	 * @param value
	 *            the model value
	 */
	public void updateCache(ID<?> id, Model<?> value) {
		Preconditions.checkNotNull(id, "key");
		Preconditions.checkNotNull(value, "value");
		log.debug("Updating cached object {} with {}", id, value);
		objectCache.put(id, value);
	}

	/**
	 * Removes an cached object
	 * 
	 * @param id
	 *            the object id
	 */
	public void removeCache(Object id) {
		Preconditions.checkNotNull(id, "key");
		log.debug("Removing cached object {}", id);
		objectCache.remove(id);
	}

	@Override
	protected void doStop() throws ServiceStopException {
		autoSaveFuture.cancel(true);
		autoSaveFuture = null;
		cacheService.dispose(objectCache);
		objectCache = null;
		database.close();
		database = null;
	}

	/**
	 * The query interface. The query will receive an connection an will be
	 * executed. The can return return a value if required.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <R>
	 *            the return type
	 */
	public interface Query<R> {
		/**
		 * Execute the query in <tt>conn</tt>
		 * 
		 * @param database
		 *            the database connection
		 * @return the query return value
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		R query(ODatabaseDocumentTx database) throws SQLException;
	}

	/**
	 * This query is used for the following statements:
	 * <ul>
	 * <li>INSERT INTO</li>
	 * <li>UPDATE</li>
	 * <li>DELETE FROM</li>
	 * </ul>
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <T>
	 *            the query return type
	 */
	public static abstract class InsertUpdateQuery<T> implements Query<Integer> {
		/**
		 * The logger
		 */
		private final Logger log = LoggerFactory
				.getLogger(InsertUpdateQuery.class);

		/**
		 * The iterator
		 */
		private final Iterator<T> iterator;

		/**
		 * Creates a new query for <tt>objects</tt>
		 * 
		 * @param objects
		 *            the object list
		 */
		@SafeVarargs
		public InsertUpdateQuery(T... objects) {
			this(new ArrayIterator<T>(objects));
		}

		/**
		 * Create a new query for objects in <tt>iterator</tt>
		 * 
		 * @param iterator
		 *            the object iterator
		 */
		public InsertUpdateQuery(Iterator<T> iterator) {
			this.iterator = iterator;
		}

		@Override
		public Integer query(ODatabaseDocumentTx database) throws SQLException {
			Preconditions.checkNotNull(database, "database");

			log.debug("Starting INSERT/UPDATE query execution");

			int rows = 0;
			while (iterator.hasNext()) {
				final T object = iterator.next();
				final ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> query = createQuery(
						database, object);
				final ODocument document;
				if (query != null) {
					List<ODocument> docs = database.query(query);
					if (docs.size() >= 1) {
						document = update(docs.get(0), object);
					} else {
						continue;
					}
				} else {
					document = insert(new ODocument(), object);
				}
				if (document != null)
					database.save(document);
				rows++;
			}
			return rows;
		}

		/**
		 * Creates the <b>prepared</b> query for execution
		 * 
		 * @param database
		 *            the database
		 * @param object
		 *            the object that is being updated
		 * 
		 * @return the <b>prepared</b> query. If <tt>null</tt> is returned a
		 *         insert query will be performed.
		 */
		protected abstract ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
				ODatabaseDocumentTx database, T object);

		/**
		 * Set the parameters in <tt>document</tt> for <tt>object</tt>. The
		 * object can be removed calling {@link ODocument#delete()} and
		 * returning <tt>null</tt>
		 * 
		 * @param document
		 *            the document
		 * @param object
		 *            the object
		 * @return the updated document. Can be <tt>null</tt>
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		protected abstract ODocument update(ODocument document, T object)
				throws SQLException;

		/**
		 * Set the parameters for in <tt>statement</tt> for <tt>object</tt>
		 * 
		 * @param document
		 *            the document
		 * @param object
		 *            the object
		 * @return the filled document
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		protected abstract ODocument insert(ODocument document, T object)
				throws SQLException;

		/**
		 * Return the key mapper. Can be null if no generated keys are used or
		 * are not important.
		 * 
		 * @return the key mapper
		 */
		protected Mapper<? extends ID<?>> keyMapper() {
			return null;
		}
	}

	/**
	 * An select query that returns a list of objects of type <tt>T</tt>
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <T>
	 *            the query return type
	 */
	public static abstract class SelectListQuery<T> implements Query<List<T>> {
		/**
		 * The logger
		 */
		private final Logger log = LoggerFactory
				.getLogger(SelectListQuery.class);

		@Override
		public List<T> query(ODatabaseDocumentTx database) throws SQLException {
			Preconditions.checkNotNull(database, "database");

			log.debug("Starting SELECT List<?> query execution");

			List<ODocument> result = database.query(createQuery(database));
			final List<T> list = CollectionFactory.newList();
			final Mapper<T> mapper = mapper();
			log.debug("Database returned {}", result);
			for (final ODocument document : result) {
				log.debug("Mapping row with {}", mapper);
				final T obj = mapper.map(document);
				if (obj == null) {
					log.debug("Mapper {} returned a null row", mapper);
					continue;
				}
				if (obj instanceof Model)
					((Model<?>) obj).setObjectDesire(ObjectDesire.NONE);
				log.debug("Mapper {} returned {}", mapper, obj);
				list.add(obj);
			}
			return list;
		}

		/**
		 * Creates the <b>prepared</b> query for execution
		 * 
		 * @param database
		 *            the database
		 * 
		 * @return the <b>prepared</b> query
		 */
		protected abstract ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
				ODatabaseDocumentTx database);

		/**
		 * Return the mapper that will bind {@link ResultSet} objects into an
		 * <tt>T</tt> object instance. The mapper will need to create the object
		 * instance.
		 * <p>
		 * <b>Note</b>: This method will be called for each row, an thus is a
		 * good idea to create a new instance on each call!
		 * 
		 * @return the mapper instance
		 */
		protected abstract Mapper<T> mapper();
	}

	/**
	 * An select query that returns a single object of type <tt>T</tt>
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <T>
	 *            the query return type
	 */
	public static abstract class SelectSingleQuery<T> implements Query<T> {
		/**
		 * The logger
		 */
		private final Logger log = LoggerFactory
				.getLogger(SelectSingleQuery.class);

		@Override
		public T query(ODatabaseDocumentTx database) throws SQLException {
			Preconditions.checkNotNull(database, "database");

			log.debug("Starting SELECT single query execution");

			List<ODocument> result = database.query(createQuery(database));
			final Mapper<T> mapper = mapper();
			log.debug("Database returned {}", result);
			for (final ODocument document : result) {
				log.debug("Mapping row with {}", mapper);
				final T obj = mapper.map(document);
				if (obj == null) {
					log.debug("Mapper {} returned a null row", mapper);
					continue;
				}
				if (obj instanceof Model)
					((Model<?>) obj).setObjectDesire(ObjectDesire.NONE);
				log.debug("Mapper {} returned {}", mapper, obj);
				return obj;
			}
			return null;
		}

		/**
		 * Creates the <b>prepared</b> query for execution
		 * 
		 * @param database
		 *            the database
		 * 
		 * @return the <b>prepared</b> query
		 */
		protected abstract ONativeSynchQuery<ODocument, OQueryContextNativeSchema<ODocument>> createQuery(
				ODatabaseDocumentTx database);

		/**
		 * Return the mapper that will bind {@link ResultSet} objects into an
		 * <tt>T</tt> object instance. The mapper will need to create the object
		 * instance.
		 * <p>
		 * <b>Note</b>: This method will be called for each row, an thus is a
		 * good idea to create a new instance on each call!
		 * 
		 * @return the mapper instance
		 */
		protected abstract Mapper<T> mapper();
	}

	/**
	 * The {@link Mapper} maps an {@link ResultSet} into an object <tt>T</tt>
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <T>
	 *            the object type
	 */
	public interface Mapper<T> {
		/**
		 * Map the result set value into an object.
		 * <p>
		 * <b>Note</b>: it is required to call {@link ResultSet#next()}, since
		 * it is called by the {@link Query}.
		 * 
		 * @param document
		 *            the resulted document
		 * @return the created instance
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		T map(ODocument document) throws SQLException;
	}

	/**
	 * The cached mapper will try to lookup the result in the cache, before
	 * create a new instance. If the instance is not found in the cache, then
	 * the {@link Mapper} implementation is called to create the object. Note
	 * that the ID, used for the cache lookup, will be reused. After creation,
	 * the cache is updated.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <T>
	 *            the object type
	 * @param <I>
	 *            the id type
	 */
	public abstract static class CachedMapper<T extends Model<?>, I extends ID<?>>
			implements Mapper<T> {
		/**
		 * The logger
		 */
		private final Logger log = LoggerFactory
				.getLogger(SelectSingleQuery.class);

		/**
		 * The database service instance
		 */
		private final AbstractOrientDatabaseService database;

		/**
		 * The {@link ID} mapper
		 */
		private final Mapper<I> idMapper;

		/**
		 * Creates a new instance
		 * 
		 * @param database
		 *            the database service
		 * @param idMapper
		 *            the {@link ID} {@link Mapper}
		 */
		public CachedMapper(AbstractOrientDatabaseService database,
				Mapper<I> idMapper) {
			this.database = database;
			this.idMapper = idMapper;
		}

		@Override
		@SuppressWarnings("unchecked")
		public final T map(ODocument document) throws SQLException {
			log.debug("Mapping row {} ID with {}", document, idMapper);
			final I id = idMapper.map(document);
			Preconditions.checkNotNull(id, "id");

			log.debug("ID={}, locating cached object", id);

			if (database.hasCachedObject(id))
				return (T) database.getCachedObject(id);

			log.debug("Cached object not found, creating...");

			final T object = map(id, document);
			if (object != null)
				database.updateCache(id, object);
			log.debug("Object {} created", object);
			return object;
		}

		/**
		 * Maps an uncached object. Once mapping is complete, it will be added
		 * to the cache.
		 * 
		 * @param id
		 *            the object id
		 * @param document
		 *            the document result
		 * @return the created object
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		protected abstract T map(I id, ODocument document) throws SQLException;
	}
}
