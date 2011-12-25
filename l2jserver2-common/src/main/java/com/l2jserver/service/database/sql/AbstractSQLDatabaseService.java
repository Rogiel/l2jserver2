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
package com.l2jserver.service.database.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import com.l2jserver.service.core.vfs.VFSService;
import com.l2jserver.service.database.DAOResolver;
import com.l2jserver.service.database.DataAccessObject;
import com.l2jserver.service.database.DatabaseException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.InsertMapper;
import com.l2jserver.service.database.dao.SelectMapper;
import com.l2jserver.service.database.dao.UpdateMapper;
import com.l2jserver.service.database.ddl.QueryFactory;
import com.l2jserver.service.database.ddl.TableFactory;
import com.l2jserver.service.database.ddl.struct.Table;
import com.l2jserver.util.factory.CollectionFactory;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.Mapper;
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
 * <h2>The {@link SelectMapper} object</h2>
 * 
 * The {@link SelectMapper} object maps an JDBC {@link DatabaseRow} into an Java
 * {@link Object}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AbstractSQLDatabaseService extends AbstractService
		implements DatabaseService {
	/**
	 * The configuration object
	 */
	private final JDBCDatabaseConfiguration config;
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory
			.getLogger(AbstractSQLDatabaseService.class);

	/**
	 * The cache service
	 */
	protected final CacheService cacheService;
	/**
	 * The thread service
	 */
	protected final ThreadService threadService;
	/**
	 * The VFS Service
	 */
	protected final VFSService vfsService;
	/**
	 * The {@link DAOResolver} instance
	 */
	private final DAOResolver daoResolver;

	/**
	 * The database engine instance (provides drivers and other factories
	 * classes)
	 */
	private DatabaseEngine engine;
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

	/**
	 * Configuration interface for {@link AbstractSQLDatabaseService}.
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
		 * @return the database engine class
		 */
		@ConfigurationPropertyGetter(defaultValue = "com.l2jserver.service.database.sql.MySQLDatabaseEngine")
		@ConfigurationXPath("/configuration/services/database/jdbc/engine")
		Class<? extends DatabaseEngine> getDatabaseEngineClass();

		/**
		 * @param driver
		 *            the new database engine class
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/engine")
		void setDatabaseEngineClass(Class<? extends DatabaseEngine> driver);

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
		 * @return the update schema state
		 */
		@ConfigurationPropertyGetter(defaultValue = "true")
		@ConfigurationXPath("/configuration/services/database/jdbc/updateSchema")
		boolean getUpdateSchema();

		/**
		 * @param updateSchema
		 *            the new uodate schema state
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/database/jdbc/updateSchema")
		void setUpdateSchema(boolean updateSchema);

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
	 * @param vfsService
	 *            the vfs service
	 * @param daoResolver
	 *            the {@link DataAccessObject DAO} resolver
	 * @param types
	 *            the SQL mapping types
	 */
	@Inject
	public AbstractSQLDatabaseService(ConfigurationService configService,
			CacheService cacheService, ThreadService threadService,
			VFSService vfsService, DAOResolver daoResolver, Type<?>... types) {
		config = configService.get(JDBCDatabaseConfiguration.class);
		this.cacheService = cacheService;
		this.threadService = threadService;
		this.vfsService = vfsService;
		this.daoResolver = daoResolver;
		this.sqlTypes = types;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		try {
			engine = config.getDatabaseEngineClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ServiceStartException(
					"DatabaseEngine instance not found", e);
		}

		connectionPool = new GenericObjectPool(null);
		connectionPool.setMaxActive(config.getMaxActiveConnections());
		connectionPool.setMinIdle(config.getMinIdleConnections());
		connectionPool.setMaxIdle(config.getMaxIdleConnections());

		// test if connections are active while idle
		connectionPool.setTestWhileIdle(true);

		// DriverManager.registerDriver(driver)

		connectionFactory = new DriverManagerConnectionFactory(
				config.getJdbcUrl(), config.getUsername(), config.getPassword());
		poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, connectionPool, null, "SELECT 1", false,
				true);
		dataSource = new PoolingDataSource(connectionPool);

		if (config.getUpdateSchema()) {
			try {
				final Connection conn = dataSource.getConnection();
				try {
					ensureDatabaseSchema(conn);
				} finally {
					conn.close();
				}
			} catch (Exception e) {
				throw new ServiceStartException(
						"Couldn't update database schema", e);
			}
		}

		for (final Type<?> type : sqlTypes) {
			engine.registerType(type);
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

	/**
	 * Makes sure the database schema is up-to-date with the external database
	 * 
	 * @param conn
	 *            the connection to be used
	 * @throws SQLException
	 *             if any {@link SQLException} occur
	 * @throws IOException
	 *             if any {@link IOException} occur
	 */
	protected abstract void ensureDatabaseSchema(Connection conn)
			throws SQLException, IOException;

	/**
	 * 
	 * @param conn
	 *            the connection to be used
	 * @param table
	 *            the {@link RelationalPathBase} table
	 * @return <code>true</code> if a new table was created, <code>false</code>
	 *         otherwise.
	 * @throws SQLException
	 *             if any {@link SQLException} occur
	 */
	protected boolean updateSchema(Connection conn, RelationalPathBase<?> table)
			throws SQLException {
		final Table expected = TableFactory.createTable(table);
		String query = null;
		boolean create = false;
		try {
			final Table current = TableFactory.createTable(conn,
					engine.getTemplate(), table.getTableName());
			query = QueryFactory.alterTableQueryUpdate(expected, current,
					engine.getTemplate());
		} catch (SQLException e) {
			// table may not exist
			query = QueryFactory.createTableQuery(expected,
					engine.getTemplate());
			create = true;
		}
		if ((engine.getTemplate().supportsAlterTable() && !create) || create)
			executeSQL(conn, query);
		return create;
	}

	/**
	 * Executes the SQL code in the databases
	 * 
	 * @param conn
	 *            the SQL connection
	 * @param sql
	 *            the SQL query
	 * @return (see {@link Statement#execute(String)})
	 * @throws SQLException
	 *             if any error occur while executing the sql query
	 */
	protected boolean executeSQL(Connection conn, String sql)
			throws SQLException {
		final Statement st = conn.createStatement();
		try {
			return st.execute(sql);
		} catch (SQLException e) {
			log.warn("Error exectuing query {}", sql);
			throw e;
		} finally {
			st.close();
		}
	}

	@Override
	public <M extends Model<?>, T extends RelationalPathBase<?>> void importData(
			java.nio.file.Path path, T entity) throws IOException {
		Connection conn;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			return;
		}
		log.info("Importing {} to {}", path, entity);
		try {
			BufferedReader reader = Files.newBufferedReader(path,
					Charset.defaultCharset());
			final String header[] = reader.readLine().split(",");
			String line;
			while ((line = reader.readLine()) != null) {
				final String data[] = line.split(",");
				SQLInsertClause insert = engine.createSQLQueryFactory(conn)
						.insert(entity);
				insert.populate(data, new Mapper<Object[]>() {
					@Override
					public Map<Path<?>, Object> createMap(
							RelationalPath<?> relationalPath, Object[] object) {
						final Map<Path<?>, Object> values = CollectionFactory
								.newMap();
						pathFor: for (final Path<?> path : relationalPath
								.getColumns()) {
							int i = 0;
							for (final String headerName : header) {
								if (path.getMetadata().getExpression()
										.toString().equals(headerName)) {
									values.put(path, object[i]);
									continue pathFor;
								}
								i++;
							}
						}
						return values;
					}
				});
				insert.execute();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
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
					return query
							.query(engine.createSQLQueryFactory(conn), this);
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
		 * @param database
		 *            the database service instance
		 * @return the query return value
		 */
		R query(SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory,
				DatabaseService database);
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

	public static class InsertQuery<O, RI, I extends ID<? super RI>, E extends RelationalPathBase<?>>
			extends AbstractQuery<Integer> {
		private final InsertMapper<O, RI, I, E> mapper;
		private final Iterator<O> iterator;
		private final Path<RI> primaryKey;

		protected final E e;

		/**
		 * @param entity
		 *            the entity type
		 * @param mapper
		 *            the insert mapper
		 * @param iterator
		 *            the objects to be inserted
		 * @param primaryKey
		 *            the primary key, if any. Only required if the ID is
		 *            generated by the database engine.
		 */
		public InsertQuery(E entity, InsertMapper<O, RI, I, E> mapper,
				Path<RI> primaryKey, Iterator<O> iterator) {
			this.iterator = iterator;
			this.mapper = mapper;
			this.e = entity;
			this.primaryKey = primaryKey;
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param mapper
		 *            the insert mapper
		 * @param iterator
		 *            the objects to be inserted
		 */
		public InsertQuery(E entity, InsertMapper<O, RI, I, E> mapper,
				Iterator<O> iterator) {
			this(entity, mapper, null, iterator);
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param mapper
		 *            the insert mapper
		 * @param objects
		 *            the objects to be inserted
		 */
		@SafeVarargs
		public InsertQuery(E entity, InsertMapper<O, RI, I, E> mapper,
				O... objects) {
			this(entity, mapper, null, Iterators.forArray(objects));
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param mapper
		 *            the insert mapper
		 * @param objects
		 *            the objects to be inserted
		 * @param primaryKey
		 *            the primary key, if any. Only required if the ID is
		 *            generated by the database engine.
		 */
		@SafeVarargs
		public InsertQuery(E entity, InsertMapper<O, RI, I, E> mapper,
				Path<RI> primaryKey, O... objects) {
			this(entity, mapper, primaryKey, Iterators.forArray(objects));
		}

		@Override
		@SuppressWarnings("unchecked")
		public final Integer query(
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory,
				DatabaseService database) {
			int rows = 0;
			while (iterator.hasNext()) {
				final O object = iterator.next();
				final SQLInsertWritableDatabaseRow row = new SQLInsertWritableDatabaseRow(
						factory.insert(e));
				mapper.insert(e, object, row);

				if (primaryKey == null) {
					row.getClause().execute();
				} else {
					final RI key = row.getClause().executeWithKey(primaryKey);
					final I id = mapper.getPrimaryKeyMapper().createID(key);
					if (object instanceof Model) {
						((Model<I>) object).setID(id);
					}
				}
				rows++;

				updateDesire(object, ObjectDesire.INSERT);
			}
			return rows;
		}
	}

	public static abstract class UpdateQuery<O, E extends RelationalPathBase<?>>
			extends AbstractQuery<Integer> {
		private final UpdateMapper<O, E> mapper;
		private final Iterator<O> iterator;
		protected final E e;

		/**
		 * @param entity
		 *            the entity type
		 * @param mapper
		 *            the update mapper
		 * @param iterator
		 *            the objects to be inserted
		 */
		public UpdateQuery(E entity, UpdateMapper<O, E> mapper,
				Iterator<O> iterator) {
			this.iterator = iterator;
			this.mapper = mapper;
			this.e = entity;
		}

		/**
		 * @param entity
		 *            the entity type
		 * @param mapper
		 *            the update mapper
		 * @param objects
		 *            the objects to be inserted
		 */
		@SafeVarargs
		public UpdateQuery(E entity, UpdateMapper<O, E> mapper, O... objects) {
			this(entity, mapper, Iterators.forArray(objects));
		}

		@Override
		public final Integer query(
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory,
				DatabaseService database) {
			int rows = 0;
			while (iterator.hasNext()) {
				final O object = iterator.next();
				final SQLUpdateWritableDatabaseRow row = new SQLUpdateWritableDatabaseRow(
						factory.update(e));
				// maps query to the values
				query(row.getClause(), object);
				mapper.update(e, object, row);

				rows += row.getClause().execute();

				updateDesire(object, ObjectDesire.UPDATE);
			}
			return rows;
		}

		protected abstract void query(SQLUpdateClause q, O o);
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
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory,
				DatabaseService database) {
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

	public static abstract class AbstractSelectQuery<R, O, RI, I extends ID<? super RI>, E extends RelationalPathBase<RI>>
			extends AbstractQuery<R> {
		protected final E entity;
		protected final SelectMapper<O, RI, I, E> mapper;

		/**
		 * @param entity
		 *            the entity type
		 * @param mapper
		 *            the object mapper
		 */
		public AbstractSelectQuery(E entity, SelectMapper<O, RI, I, E> mapper) {
			this.entity = entity;
			this.mapper = mapper;
		}

		@Override
		public final R query(
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory,
				DatabaseService database) {
			final AbstractSQLQuery<?> select = factory.query();
			// maps query to the values
			select.from(entity);
			query(select, entity);
			return perform(select, database);
		}

		protected abstract void query(AbstractSQLQuery<?> q, E e);

		protected abstract R perform(AbstractSQLQuery<?> select,
				DatabaseService database);

		@SuppressWarnings("unchecked")
		protected O lookupCache(DatabaseRow row, DatabaseService database) {
			final I id = mapper.getPrimaryKeyMapper().createID(
					(RI) row.get(entity.getPrimaryKey().getLocalColumns()
							.get(0)));

			if (id != null) {
				if (database.hasCachedObject(id))
					return (O) database.getCachedObject(id);
			}
			return null;
		}

		protected void updateCache(O instance, DatabaseService database) {
			if (instance == null)
				return;
			if (instance instanceof Model)
				database.updateCache(((Model<?>) instance).getID(),
						(Model<?>) instance);
		}
	}

	public static abstract class SelectSingleQuery<O, RI, I extends ID<? super RI>, E extends RelationalPathBase<RI>>
			extends AbstractSelectQuery<O, O, RI, I, E> {
		/**
		 * @param entity
		 *            the entity
		 * @param mapper
		 *            the mapper
		 */
		public SelectSingleQuery(E entity, SelectMapper<O, RI, I, E> mapper) {
			super(entity, mapper);
		}

		@Override
		protected final O perform(AbstractSQLQuery<?> select,
				DatabaseService database) {
			final List<Object[]> results = select.limit(1).list(entity.all());
			if (results.size() == 1) {
				final DatabaseRow row = new SQLDatabaseRow(results.get(0),
						entity);
				O object = lookupCache(row, database);
				if (object == null) {
					object = mapper.select(entity,
							new SQLDatabaseRow(results.get(0), entity));
					updateCache(object, database);
				}
				return object;
			} else {
				return null;
			}
		}
	}

	public static abstract class SelectListQuery<O, RI, I extends ID<? super RI>, E extends RelationalPathBase<RI>>
			extends AbstractSelectQuery<List<O>, O, RI, I, E> {
		/**
		 * @param entity
		 *            the entity
		 * @param mapper
		 *            the mapper
		 */
		public SelectListQuery(E entity, SelectMapper<O, RI, I, E> mapper) {
			super(entity, mapper);
		}

		@Override
		protected final List<O> perform(AbstractSQLQuery<?> select,
				DatabaseService database) {
			final List<Object[]> results = select.list(entity.all());
			final SQLDatabaseRow row = new SQLDatabaseRow(entity);
			final List<O> objects = CollectionFactory.newList();
			for (final Object[] data : results) {
				row.setRow(data);

				O object = lookupCache(row, database);
				if (object == null) {
					object = mapper.select(entity, row);
					updateCache(object, database);
				}
				if (object != null)
					objects.add(object);
			}
			return objects;
		}
	}
}
