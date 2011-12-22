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
package com.l2jserver.service.database.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Provider;
import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
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
import com.l2jserver.service.database.DAOResolver;
import com.l2jserver.service.database.DataAccessObject;
import com.l2jserver.service.database.DatabaseException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.Mapper;
import com.l2jserver.util.factory.CollectionFactory;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.sql.types.Type;
import com.mysema.query.types.Path;

/**
 * This is an implementation of {@link DatabaseService} that provides an layer
 * to JDBC.
 * 
 * <h1>Internal specification</h1> <h2>The {@link Query} object</h2>
 * 
 * If you wish to implement a new {@link DataAccessObject} you should try not
 * use {@link Query} object directly because it only provides low level access
 * to the JDBC architecture. Instead, you could use an specialized class, like
 * {@link InsertQuery}, {@link SelectListQuery} or {@link SelectSingleQuery}. If
 * you do need low level access, feel free to use the {@link Query} class
 * directly.
 * 
 * <h2>The {@link Mapper} object</h2>
 * 
 * The {@link Mapper} object maps an JDBC {@link DatabaseRow} into an Java
 * {@link Object}.
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
	private Cache<ID<?>, Model<?>> objectCache;
	/**
	 * Future for the auto-save task. Each object that has changed is auto saved
	 * every 1 minute.
	 */
	private ScheduledAsyncFuture autoSaveFuture;

	/**
	 * The connection used inside a transaction from multiple DAOs.
	 */
	private ThreadLocal<Connection> transactionalConnection = new ThreadLocal<>();
	/**
	 * The {@link Type} that will be mapped by the querydsl.
	 */
	private final Type<?>[] sqlTypes;

	private SQLTemplates queryTemplates;
	private Configuration queryConfig;

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
		@ConfigurationXPath("/configuration/services/database/jdbc/url")
		String getJdbcUrl();

		/**
		 * @param jdbcUrl
		 *            the new jdbc url
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/url")
		void setJdbcUrl(String jdbcUrl);

		/**
		 * @return the jdbc driver class
		 */
		@ConfigurationPropertyGetter(defaultValue = "com.jdbc.jdbc.Driver")
		@ConfigurationXPath("/configuration/services/database/jdbc/driver")
		Class<?> getDriver();

		/**
		 * @param driver
		 *            the new jdbc driver
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/driver")
		void setDriver(Class<?> driver);

		/**
		 * @return the sql template class
		 */
		@ConfigurationPropertyGetter(defaultValue = "com.mysema.query.sql.MySQLTemplates")
		@ConfigurationXPath("/configuration/services/database/jdbc/templates")
		Class<? extends SQLTemplates> getSQLTemplatesClass();

		/**
		 * @param templatesClass
		 *            the new sql template class
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/templates")
		void setSQLTemplatesClass(Class<? extends SQLTemplates> templatesClass);

		/**
		 * @return the sql template class
		 */
		@ConfigurationPropertyGetter(defaultValue = "com.mysema.query.sql.mysql.MySQLQueryFactory")
		@ConfigurationXPath("/configuration/services/database/jdbc/queryFactory")
		Class<? extends SQLQueryFactory<?, ?, ?, ?, ?, ?>> getSQLQueryFactoryClass();

		/**
		 * @param factoryClass
		 *            the new sql query factory class
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/queryFactory")
		void setSQLQueryFactoryClass(
				Class<? extends SQLQueryFactory<?, ?, ?, ?, ?, ?>> factoryClass);

		/**
		 * @return the jdbc database username
		 */
		@ConfigurationPropertyGetter(defaultValue = "l2j")
		@ConfigurationXPath("/configuration/services/database/jdbc/username")
		String getUsername();

		/**
		 * @param username
		 *            the jdbc database username
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/username")
		void setUsername(String username);

		/**
		 * @return the jdbc database password
		 */
		@ConfigurationPropertyGetter(defaultValue = "changeme")
		@ConfigurationXPath("/configuration/services/database/jdbc/password")
		String getPassword();

		/**
		 * @param password
		 *            the jdbc database password
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/password")
		void setPassword(String password);

		/**
		 * @return the maximum number of active connections
		 */
		@ConfigurationPropertyGetter(defaultValue = "20")
		@ConfigurationXPath("/configuration/services/database/connections/active-maximum")
		int getMaxActiveConnections();

		/**
		 * @param password
		 *            the maximum number of active connections
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/connections/active-maximum")
		void setMaxActiveConnections(int password);

		/**
		 * @return the maximum number of idle connections
		 */
		@ConfigurationPropertyGetter(defaultValue = "20")
		@ConfigurationXPath("/configuration/services/database/connections/idle-maximum")
		int getMaxIdleConnections();

		/**
		 * @param password
		 *            the maximum number of idle connections
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/connections/idle-maximum")
		void setMaxIdleConnections(int password);

		/**
		 * @return the minimum number of idle connections
		 */
		@ConfigurationPropertyGetter(defaultValue = "5")
		@ConfigurationXPath("/configuration/services/database/connections/idle-minimum")
		int getMinIdleConnections();

		/**
		 * @param password
		 *            the minimum number of idle connections
		 */
		@ConfigurationPropertySetter
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
	 * @param types
	 *            the SQL mapping types
	 */
	@Inject
	public AbstractJDBCDatabaseService(ConfigurationService configService,
			CacheService cacheService, ThreadService threadService,
			DAOResolver daoResolver, Type<?>... types) {
		config = configService.get(JDBCDatabaseConfiguration.class);
		this.cacheService = cacheService;
		this.threadService = threadService;
		this.daoResolver = daoResolver;
		this.sqlTypes = types;
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

		try {
			queryTemplates = config.getSQLTemplatesClass()
					.getConstructor(Boolean.TYPE).newInstance(true);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new ServiceStartException(e);
		}
		queryConfig = new Configuration(queryTemplates);
		for (final Type<?> type : sqlTypes) {
			queryConfig.register(type);
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
						try {
							log.debug("Auto save task started");
							int objects = 0;
							for (final Model<?> object : objectCache) {
								@SuppressWarnings("unchecked")
								final DataAccessObject<Model<?>, ?> dao = (DataAccessObject<Model<?>, ?>) daoResolver
										.getDAO(object.getClass());
								if (dao == null)
									continue;
								if (dao.save(object) > 0) {
									objects++;
								}
							}
							log.info(
									"{} objects have been saved by the auto save task",
									objects);
						} catch (Exception e) {
							log.error("Error occured in save thread", e);
						}
					}
				});
	}

	@Override
	public int transaction(TransactionExecutor executor) {
		Preconditions.checkNotNull(executor, "executor");
		try {
			final Connection conn = dataSource.getConnection();
			log.debug("Executing transaction {} with {}", executor, conn);
			try {
				conn.setAutoCommit(false);

				transactionalConnection.set(conn);
				final int rows = executor.perform();

				conn.commit();
				return rows;
			} catch (Exception e) {
				conn.rollback();
				throw e;
			} finally {
				transactionalConnection.set(null);
				transactionalConnection.remove();
				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (Throwable e) {
			throw new DatabaseException(e);
		}
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
	 * @throws DatabaseException
	 *             if any exception occur (can have nested {@link SQLException})
	 */
	public <T> T query(Query<T> query) throws DatabaseException {
		Preconditions.checkNotNull(query, "query");
		try {
			boolean inTransaction = true;
			Connection conn = transactionalConnection.get();
			if (conn == null) {
				System.out.println("new connection!");
				log.debug(
						"Transactional connection for {} is not set, creating new connection",
						query);
				inTransaction = false;
				conn = dataSource.getConnection();
			}
			log.debug("Executing query {} with {}", query, conn);
			try {
				if (!inTransaction) {
					conn.setAutoCommit(false);
				}
				try {
					final Connection retConn = conn;
					final SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory = createQueryFactory(new Provider<Connection>() {
						@Override
						public Connection get() {
							return retConn;
						}
					});
					return query.query(factory);
				} finally {
					if (!inTransaction) {
						conn.commit();
					}
				}
			} catch (Exception e) {
				if (!inTransaction) {
					conn.rollback();
				}
				throw e;
			} finally {
				if (!inTransaction) {
					conn.setAutoCommit(true);
					conn.close();
				}
			}
		} catch (Throwable e) {
			log.error("Could not open database connection", e);
			throw new DatabaseException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> createQueryFactory(
			Provider<Connection> provider) {
		try {
			return (SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?>) config
					.getSQLQueryFactoryClass()
					.getConstructor(Configuration.class, Provider.class)
					.newInstance(queryConfig, provider);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <I extends ID<?>, O extends Model<?>> O getCachedObject(I id) {
		Preconditions.checkNotNull(id, "id");
		log.debug("Fetching cached object {}", id);
		return (O) objectCache.get(id);
	}

	@Override
	public <I extends ID<?>, O extends Model<?>> boolean hasCachedObject(I id) {
		Preconditions.checkNotNull(id, "id");
		log.debug("Locating cached object {}", id);
		return objectCache.contains(id);
	}

	@Override
	public <I extends ID<?>, O extends Model<?>> void updateCache(I id, O value) {
		Preconditions.checkNotNull(id, "key");
		Preconditions.checkNotNull(value, "value");
		log.debug("Updating cached object {} with {}", id, value);
		objectCache.put(id, value);
	}

	@Override
	public <I extends ID<?>, O extends Model<?>> void removeCache(I id) {
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
		 * @param factory
		 *            the query factory (database specific)
		 * @return the query return value
		 */
		R query(SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory);
	}

	public static abstract class AbstractQuery<R> implements Query<R> {
		protected void updateDesire(Object object, ObjectDesire expected) {
			if (object instanceof Model) {
				if (((Model<?>) object).getObjectDesire() == expected) {
					((Model<?>) object).setObjectDesire(ObjectDesire.NONE);
				}
			}
		}
	}

	public static abstract class InsertQuery<O, E extends RelationalPathBase<?>, K>
			extends AbstractQuery<Integer> {
		private final Iterator<O> iterator;
		private final Path<K> primaryKey;

		protected final E e;

		/**
		 * @param entity
		 *            the entity type
		 * @param iterator
		 *            the objects to be inserted
		 * @param primaryKey
		 *            the primary key, if any. Only required if the ID is
		 *            generated by the database engine.
		 */
		public InsertQuery(E entity, Path<K> primaryKey, Iterator<O> iterator) {
			this.iterator = iterator;
			this.e = entity;
			this.primaryKey = primaryKey;
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param iterator
		 *            the objects to be inserted
		 */
		public InsertQuery(E entity, Iterator<O> iterator) {
			this(entity, null, iterator);
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param objects
		 *            the objects to be inserted
		 */
		@SafeVarargs
		public InsertQuery(E entity, O... objects) {
			this(entity, null, Iterators.forArray(objects));
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param objects
		 *            the objects to be inserted
		 * @param primaryKey
		 *            the primary key, if any. Only required if the ID is
		 *            generated by the database engine.
		 */
		@SafeVarargs
		public InsertQuery(E entity, Path<K> primaryKey, O... objects) {
			this(entity, primaryKey, Iterators.forArray(objects));
		}

		@Override
		public final Integer query(
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory) {
			int rows = 0;
			while (iterator.hasNext()) {
				final O object = iterator.next();
				final SQLInsertClause insert = factory.insert(e);
				// maps query to the values
				map(insert, object);

				if (primaryKey == null) {
					insert.execute();
				} else {
					final K key = insert.executeWithKey(primaryKey);
					key(key, object);
				}
				rows++;

				updateDesire(object, ObjectDesire.INSERT);
			}
			return rows;
		}

		protected abstract void map(SQLInsertClause q, O o);

		protected void key(K k, O o) {
		}
	}

	public static abstract class UpdateQuery<O, E extends RelationalPathBase<?>>
			extends AbstractQuery<Integer> {
		private final Iterator<O> iterator;
		protected final E e;

		/**
		 * @param entity
		 *            the entity type
		 * @param iterator
		 *            the objects to be inserted
		 */
		public UpdateQuery(E entity, Iterator<O> iterator) {
			this.iterator = iterator;
			this.e = entity;
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param objects
		 *            the objects to be inserted
		 */
		@SafeVarargs
		public UpdateQuery(E entity, O... objects) {
			this(entity, Iterators.forArray(objects));
		}

		@Override
		public final Integer query(
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory) {
			int rows = 0;
			while (iterator.hasNext()) {
				final O object = iterator.next();
				final SQLUpdateClause update = factory.update(e);
				// maps query to the values
				query(update, object);
				map(update, object);

				rows += update.execute();

				updateDesire(object, ObjectDesire.UPDATE);
			}
			return rows;
		}

		protected abstract void query(SQLUpdateClause q, O o);

		protected abstract void map(SQLUpdateClause q, O o);
	}

	public static abstract class DeleteQuery<O, E extends RelationalPathBase<?>>
			extends AbstractQuery<Integer> {
		private final Iterator<O> iterator;
		protected final E e;

		/**
		 * @param entity
		 *            the entity type
		 * @param iterator
		 *            the objects to be inserted
		 */
		public DeleteQuery(E entity, Iterator<O> iterator) {
			this.iterator = iterator;
			this.e = entity;
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param objects
		 *            the objects to be inserted
		 */
		@SafeVarargs
		public DeleteQuery(E entity, O... objects) {
			this(entity, Iterators.forArray(objects));
		}

		@Override
		public final Integer query(
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory) {
			int rows = 0;
			while (iterator.hasNext()) {
				final O object = iterator.next();
				final SQLDeleteClause delete = factory.delete(e);
				// maps query to the values
				query(delete, object);

				rows += delete.execute();

				updateDesire(object, ObjectDesire.DELETE);
			}
			return rows;
		}

		protected abstract void query(SQLDeleteClause q, O o);
	}

	public static abstract class AbstractSelectQuery<R, O, E extends RelationalPathBase<?>>
			extends AbstractQuery<R> {
		protected final E entity;
		protected final Mapper<O, E> mapper;

		/**
		 * @param entity
		 *            the entity type
		 * @param mapper
		 *            the object mapper
		 */
		public AbstractSelectQuery(E entity, Mapper<O, E> mapper) {
			this.entity = entity;
			this.mapper = mapper;
		}

		@Override
		public final R query(
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory) {
			final AbstractSQLQuery<?> select = factory.query();
			// maps query to the values
			select.from(entity);
			query(select, entity);
			return perform(select);
		}

		protected abstract void query(AbstractSQLQuery<?> q, E e);

		protected abstract R perform(AbstractSQLQuery<?> select);
	}

	public static abstract class SelectSingleQuery<O, E extends RelationalPathBase<?>>
			extends AbstractSelectQuery<O, O, E> {
		/**
		 * @param entity
		 *            the entity
		 * @param mapper
		 *            the mapper
		 */
		public SelectSingleQuery(E entity, Mapper<O, E> mapper) {
			super(entity, mapper);
		}

		@Override
		protected final O perform(AbstractSQLQuery<?> select) {
			final List<Object[]> results = select.limit(1).list(entity.all());
			if (results.size() == 1) {
				return mapper.map(entity, new JDBCDatabaseRow(results.get(0),
						entity));
			} else {
				return null;
			}
		}
	}

	public static abstract class SelectListQuery<O, E extends RelationalPathBase<?>>
			extends AbstractSelectQuery<List<O>, O, E> {
		/**
		 * @param entity
		 *            the entity
		 * @param mapper
		 *            the mapper
		 */
		public SelectListQuery(E entity, Mapper<O, E> mapper) {
			super(entity, mapper);
		}

		@Override
		protected final List<O> perform(AbstractSQLQuery<?> select) {
			final List<Object[]> results = select.list(entity.all());
			final JDBCDatabaseRow row = new JDBCDatabaseRow(entity);
			final List<O> objects = CollectionFactory.newList();
			for (final Object[] data : results) {
				row.setRow(data);
				final O object = mapper.map(entity, row);
				if (object != null)
					objects.add(object);
			}
			return objects;
		}
	}
}
