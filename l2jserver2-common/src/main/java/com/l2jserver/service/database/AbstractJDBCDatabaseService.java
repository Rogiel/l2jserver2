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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
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
import com.l2jserver.service.configuration.ProxyConfigurationService.ConfigurationPropertiesKey;
import com.l2jserver.service.configuration.XMLConfigurationService.ConfigurationXPath;
import com.l2jserver.service.core.threading.ScheduledAsyncFuture;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.util.ArrayIterator;
import com.l2jserver.util.factory.CollectionFactory;

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
public abstract class AbstractJDBCDatabaseService extends AbstractService
		implements DatabaseService {
	/**
	 * The configuration object
	 */
	private final JDBCDatabaseConfiguration config;
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory
			.getLogger(AbstractJDBCDatabaseService.class);

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

	/**
	 * The database connection pool
	 */
	private GenericObjectPool connectionPool;
	/**
	 * The dayabase connection factory
	 */
	private ConnectionFactory connectionFactory;
	/**
	 * The poolable connection factory
	 */
	@SuppressWarnings("unused")
	private PoolableConnectionFactory poolableConnectionFactory;
	/**
	 * The connection {@link DataSource}.
	 */
	private PoolingDataSource dataSource;

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
	 * Configuration interface for {@link AbstractJDBCDatabaseService}.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface JDBCDatabaseConfiguration extends DatabaseConfiguration {
		/**
		 * @return the jdbc url
		 */
		@ConfigurationPropertyGetter(defaultValue = "jdbc:mysql://localhost/l2jserver2")
		@ConfigurationPropertiesKey("jdbc.url")
		@ConfigurationXPath("/configuration/services/database/jdbc/url")
		String getJdbcUrl();

		/**
		 * @param jdbcUrl
		 *            the new jdbc url
		 */
		@ConfigurationPropertySetter
		@ConfigurationPropertiesKey("jdbc.url")
		@ConfigurationXPath("/configuration/services/database/jdbc/url")
		void setJdbcUrl(String jdbcUrl);

		/**
		 * @return the jdbc driver class
		 */
		@ConfigurationPropertyGetter(defaultValue = "com.jdbc.jdbc.Driver")
		@ConfigurationPropertiesKey("jdbc.driver")
		@ConfigurationXPath("/configuration/services/database/jdbc/driver")
		String getDriver();

		/**
		 * @param driver
		 *            the new jdbc driver
		 */
		@ConfigurationPropertySetter
		@ConfigurationPropertiesKey("jdbc.driver")
		@ConfigurationXPath("/configuration/services/database/jdbc/driver")
		void setDriver(Class<?> driver);

		/**
		 * @return the jdbc database username
		 */
		@ConfigurationPropertyGetter(defaultValue = "l2j")
		@ConfigurationPropertiesKey("jdbc.username")
		@ConfigurationXPath("/configuration/services/database/jdbc/username")
		String getUsername();

		/**
		 * @param username
		 *            the jdbc database username
		 */
		@ConfigurationPropertySetter
		@ConfigurationPropertiesKey("jdbc.username")
		@ConfigurationXPath("/configuration/services/database/jdbc/username")
		void setUsername(String username);

		/**
		 * @return the jdbc database password
		 */
		@ConfigurationPropertyGetter(defaultValue = "changeme")
		@ConfigurationPropertiesKey("jdbc.password")
		@ConfigurationXPath("/configuration/services/database/jdbc/password")
		String getPassword();

		/**
		 * @param password
		 *            the jdbc database password
		 */
		@ConfigurationPropertySetter
		@ConfigurationPropertiesKey("jdbc.password")
		@ConfigurationXPath("/configuration/services/database/jdbc/password")
		void setPassword(String password);

		/**
		 * @return the maximum number of active connections
		 */
		@ConfigurationPropertyGetter(defaultValue = "20")
		@ConfigurationPropertiesKey("jdbc.active.max")
		@ConfigurationXPath("/configuration/services/database/connections/active-maximum")
		int getMaxActiveConnections();

		/**
		 * @param password
		 *            the maximum number of active connections
		 */
		@ConfigurationPropertySetter
		@ConfigurationPropertiesKey("jdbc.active.max")
		@ConfigurationXPath("/configuration/services/database/connections/active-maximum")
		void setMaxActiveConnections(int password);

		/**
		 * @return the maximum number of idle connections
		 */
		@ConfigurationPropertyGetter(defaultValue = "20")
		@ConfigurationPropertiesKey("jdbc.idle.max")
		@ConfigurationXPath("/configuration/services/database/connections/idle-maximum")
		int getMaxIdleConnections();

		/**
		 * @param password
		 *            the maximum number of idle connections
		 */
		@ConfigurationPropertySetter
		@ConfigurationPropertiesKey("jdbc.idle.max")
		@ConfigurationXPath("/configuration/services/database/connections/idle-maximum")
		void setMaxIdleConnections(int password);

		/**
		 * @return the minimum number of idle connections
		 */
		@ConfigurationPropertyGetter(defaultValue = "5")
		@ConfigurationPropertiesKey("jdbc.idle.min")
		@ConfigurationXPath("/configuration/services/database/connections/idle-minimum")
		int getMinIdleConnections();

		/**
		 * @param password
		 *            the minimum number of idle connections
		 */
		@ConfigurationPropertySetter
		@ConfigurationPropertiesKey("jdbc.idle.min")
		@ConfigurationXPath("/configuration/services/database/connections/idle-minimum")
		void setMinIdleConnections(int password);
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
	public AbstractJDBCDatabaseService(ConfigurationService configService,
			CacheService cacheService, ThreadService threadService,
			DAOResolver daoResolver) {
		config = configService.get(JDBCDatabaseConfiguration.class);
		this.cacheService = cacheService;
		this.threadService = threadService;
		this.daoResolver = daoResolver;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		connectionPool = new GenericObjectPool(null);
		connectionPool.setMaxActive(config.getMaxActiveConnections());
		connectionPool.setMinIdle(config.getMinIdleConnections());
		connectionPool.setMaxIdle(config.getMaxIdleConnections());

		// test if connections are active while idle
		connectionPool.setTestWhileIdle(true);

		connectionFactory = new DriverManagerConnectionFactory(
				config.getJdbcUrl(), config.getUsername(), config.getPassword());
		poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, connectionPool, null, "SELECT 1", false,
				true);
		dataSource = new PoolingDataSource(connectionPool);

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
							final DataAccessObject<Model<?>, ?> dao = (DataAccessObject<Model<?>, ?>) daoResolver
									.getDAO(object.getClass());
							if (dao.save(object)) {
								objects++;
							}
						}
						log.info(
								"{} objects have been saved by the auto save task",
								objects);
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
		try {
			final Connection conn = dataSource.getConnection();
			log.debug("Executing query {} with {}", query, conn);
			try {
				return query.query(conn);
			} catch (SQLException e) {
				log.error("Error executing query", e);
				return null;
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			log.error("Could not open database connection", e);
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

		try {
			if (connectionPool != null)
				connectionPool.close();
		} catch (Exception e) {
			log.error("Error stopping database service", e);
			throw new ServiceStopException(e);
		} finally {
			connectionPool = null;
			connectionFactory = null;
			poolableConnectionFactory = null;
			dataSource = null;
		}
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
		 * @param conn
		 *            the connection
		 * @return the query return value
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		R query(Connection conn) throws SQLException;
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

		@SuppressWarnings("unchecked")
		@Override
		public Integer query(Connection conn) throws SQLException {
			Preconditions.checkNotNull(conn, "conn");

			log.debug("Starting INSERT/UPDATE query execution");

			int rows = 0;
			while (iterator.hasNext()) {
				final T object = iterator.next();
				final String queryString = query();

				log.debug("Preparing statement for {}: {}", object, queryString);
				final PreparedStatement st = conn.prepareStatement(queryString,
						Statement.RETURN_GENERATED_KEYS);

				log.debug("Parametizing statement {} with {}", st, object);
				this.parametize(st, object);

				log.debug("Sending query to database for {}", object);
				rows += st.executeUpdate();
				log.debug("Query inserted or updated {} rows for {}", rows,
						object);

				// update object desire --it has been realized
				if (object instanceof Model && rows > 0) {
					log.debug("Updating Model ObjectDesire to NONE");
					((Model<?>) object).setObjectDesire(ObjectDesire.NONE);

					final Mapper<? extends ID<?>> mapper = keyMapper();
					if (mapper == null)
						continue;
					final ResultSet rs = st.getGeneratedKeys();
					log.debug("Mapping generated keys with {} using {}",
							mapper, rs);
					while (rs.next()) {
						final ID<?> generatedID = mapper.map(rs);
						log.debug("Generated ID for {} is {}", object,
								generatedID);
						((Model<ID<?>>) object).setID(generatedID);
						mapper.map(rs);
					}
				}
			}
			return rows;
		}

		/**
		 * Creates the <b>prepared</b> query for execution
		 * 
		 * @return the <b>prepared</b> query
		 */
		protected abstract String query();

		/**
		 * Set the parameters for in <tt>statement</tt> for <tt>object</tt>
		 * 
		 * @param st
		 *            the prepared statement
		 * @param object
		 *            the object
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		protected abstract void parametize(PreparedStatement st, T object)
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
		public List<T> query(Connection conn) throws SQLException {
			Preconditions.checkNotNull(conn, "conn");

			log.debug("Starting SELECT List<?> query execution");

			final String queryString = query();
			log.debug("Preparing statement with {}", queryString);
			final PreparedStatement st = conn.prepareStatement(query());

			log.debug("Parametizing statement {}", st);
			parametize(st);

			log.debug("Sending query to database for {}", st);
			st.execute();

			final List<T> list = CollectionFactory.newList();
			final ResultSet rs = st.getResultSet();
			final Mapper<T> mapper = mapper();
			log.debug("Database returned {}", rs);
			while (rs.next()) {
				log.debug("Mapping row with {}", mapper);
				final T obj = mapper.map(rs);
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
		 * @return the <b>prepared</b> query
		 */
		protected abstract String query();

		/**
		 * Set the parameters for in <tt>statement</tt> for <tt>object</tt>
		 * 
		 * @param st
		 *            the prepared statement
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		protected void parametize(PreparedStatement st) throws SQLException {
		}

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
		public T query(Connection conn) throws SQLException {
			Preconditions.checkNotNull(conn, "conn");

			log.debug("Starting SELECT single query execution");

			final String queryString = query();
			log.debug("Preparing statement with {}", queryString);
			final PreparedStatement st = conn.prepareStatement(query());

			log.debug("Parametizing statement {}", st);
			parametize(st);

			log.debug("Sending query to database for {}", st);
			st.execute();

			final ResultSet rs = st.getResultSet();
			final Mapper<T> mapper = mapper();
			log.debug("Database returned {}", rs);
			while (rs.next()) {
				log.debug("Mapping row {} with {}", rs, mapper);
				final T object = mapper.map(rs);
				if (object instanceof Model)
					((Model<?>) object).setObjectDesire(ObjectDesire.NONE);
				log.debug("Mapper {} returned {}", mapper, object);
				return object;
			}
			return null;
		}

		/**
		 * Creates the <b>prepared</b> query for execution
		 * 
		 * @return the <b>prepared</b> query
		 */
		protected abstract String query();

		/**
		 * Set the parameters for in <tt>statement</tt> for <tt>object</tt>
		 * 
		 * @param st
		 *            the prepared statement
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		protected void parametize(PreparedStatement st) throws SQLException {
		}

		/**
		 * Return the mapper that will bind {@link ResultSet} objects into an
		 * <tt>T</tt> object instance. The mapper will need to create the object
		 * instance.
		 * 
		 * @return the mapper
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
		 * @param rs
		 *            the result set
		 * @return the created instance
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		T map(ResultSet rs) throws SQLException;
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
		private final AbstractJDBCDatabaseService database;

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
		public CachedMapper(AbstractJDBCDatabaseService database,
				Mapper<I> idMapper) {
			this.database = database;
			this.idMapper = idMapper;
		}

		@Override
		@SuppressWarnings("unchecked")
		public final T map(ResultSet rs) throws SQLException {
			log.debug("Mapping row {} ID with {}", rs, idMapper);
			final I id = idMapper.map(rs);
			Preconditions.checkNotNull(id, "id");

			log.debug("ID={}, locating cached object", id);

			if (database.hasCachedObject(id))
				return (T) database.getCachedObject(id);

			log.debug("Cached object not found, creating...");

			final T object = map(id, rs);
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
		 * @param rs
		 *            the jdbc result set
		 * @return the created object
		 * @throws SQLException
		 *             if any SQL error occur
		 */
		protected abstract T map(I id, ResultSet rs) throws SQLException;
	}
}
