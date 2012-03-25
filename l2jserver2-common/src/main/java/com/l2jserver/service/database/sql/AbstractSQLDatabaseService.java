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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.l2jserver.service.AbstractConfigurableService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.cache.Cache;
import com.l2jserver.service.cache.CacheService;
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
import com.l2jserver.util.CSVUtils;
import com.l2jserver.util.CSVUtils.CSVMapProcessor;
import com.l2jserver.util.QPathUtils;
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
public abstract class AbstractSQLDatabaseService extends
		AbstractConfigurableService<JDBCDatabaseConfiguration> implements
		DatabaseService {
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
	private GenericObjectPool<Connection> connectionPool;
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
	private ThreadLocal<Connection> transaction = new ThreadLocal<>();
	/**
	 * The {@link Type} that will be mapped by the querydsl.
	 */
	private final Type<?>[] sqlTypes;

	/**
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
	public AbstractSQLDatabaseService(CacheService cacheService,
			ThreadService threadService, VFSService vfsService,
			DAOResolver daoResolver, Type<?>... types) {
		super(JDBCDatabaseConfiguration.class);
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

		connectionPool = new GenericObjectPool<Connection>(null);
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

		if (config.isAutomaticSchemaUpdateEnabled()) {
			updateSchemas();
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
		} finally {
			st.close();
		}
	}

	@Override
	public <M extends Model<?>, T extends RelationalPathBase<?>> void importData(
			final java.nio.file.Path path, final T entity) throws IOException {
		final Connection conn;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			return;
		}
		log.info("Importing {} to {}", path, entity);
		try {
			conn.setAutoCommit(false);
			CSVUtils.parseCSV(path, new CSVMapProcessor<Long>() {
				@Override
				public Long process(final Map<String, String> map) {
					SQLInsertClause insert = engine.createSQLQueryFactory(conn)
							.insert(entity);
					insert.populate(map, new Mapper<Map<String, String>>() {
						@Override
						public Map<Path<?>, Object> createMap(
								RelationalPath<?> relationalPath,
								Map<String, String> map) {
							final Map<Path<?>, Object> values = CollectionFactory
									.newMap();
							for (final Entry<String, String> entry : map
									.entrySet()) {
								final Path<?> path = QPathUtils.getPath(entity,
										entry.getKey());
								values.put(path, entry.getValue());
							}
							return values;
						}
					});
					return insert.execute();
				}
			});
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public boolean updateSchema(RelationalPath<?> table) {
		final Table expected = TableFactory.createTable(table);

		log.info("Updating {} schema definition", table);

		try {
			final Connection conn = dataSource.getConnection();
			try {
				String query = null;
				boolean create = false;
				try {
					final Table current = TableFactory.createTable(conn,
							engine.getTemplate(), table.getTableName());
					query = QueryFactory.alterTableQueryUpdate(expected,
							current, engine.getTemplate());
				} catch (SQLException e) {
					// table may not exist
					query = QueryFactory.createTableQuery(expected,
							engine.getTemplate());
					create = true;
				}
				if ((engine.getTemplate().supportsAlterTable() && !create)
						|| create)
					executeSQL(conn, query);
				return create;
			} catch (SQLException e) {
				throw new DatabaseException(e);
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new DatabaseException(e);
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
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

				transaction.set(new TransactionIsolatedConnection(conn));
				final int rows = executor.perform();

				conn.commit();
				return rows;
			} catch (Exception e) {
				conn.rollback();
				throw e;
			} finally {
				transaction.set(null);
				transaction.remove();
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
			Connection conn = transaction.get();
			if (conn == null) {
				log.debug(
						"Transactional connection for {} is not set, creating new connection",
						query);
				conn = dataSource.getConnection();
			}
			log.debug("Executing query {} with {}", query, conn);
			try {
				return query.query(engine.createSQLQueryFactory(conn), this);
			} finally {
				// transaction wrappers does not allow closing, so this is safe
				// to do
				conn.close();
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

	/**
	 * An base abstract query. For internal use only.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <R>
	 *            the query return type
	 */
	public static abstract class AbstractQuery<R> implements Query<R> {
		/**
		 * Tries to update the object desire if it currently is equal to
		 * <code>expected</code>
		 * 
		 * @param object
		 *            the object to update desire
		 * @param expected
		 *            the expected desire
		 */
		protected void updateDesire(Object object, ObjectDesire expected) {
			if (object instanceof Model) {
				if (((Model<?>) object).getObjectDesire() == ObjectDesire.TRANSIENT)
					return;
				if (((Model<?>) object).getObjectDesire() != expected)
					return;
				((Model<?>) object).setObjectDesire(ObjectDesire.NONE);
			}
		}

		/**
		 * Tests if the object desire is not {@link ObjectDesire#TRANSIENT}
		 * 
		 * @param object
		 *            the object
		 * @return true if the object desire is {@link ObjectDesire#TRANSIENT}
		 */
		protected boolean testDesire(Object object) {
			if (object instanceof Model) {
				if (((Model<?>) object).getObjectDesire() == ObjectDesire.TRANSIENT)
					return true;
				return false;
			}
			return false;
		}
	}

	/**
	 * An query implementation designed to insert new objects into the database.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <O>
	 *            the object type used in this query
	 * @param <RI>
	 *            the raw ID type
	 * @param <I>
	 *            the ID type
	 * @param <E>
	 *            the entity type
	 */
	public static class InsertQuery<O, RI, I extends ID<? super RI>, E extends RelationalPathBase<?>>
			extends AbstractQuery<Integer> {
		/**
		 * The row mapper
		 */
		private final InsertMapper<O, RI, I, E> mapper;
		/**
		 * The query object iterator
		 */
		private final Iterator<O> iterator;
		/**
		 * The query primary key column. Only set if want auto generated IDs
		 */
		private final Path<RI> primaryKey;

		/**
		 * The query entity
		 */
		protected final E entity;

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
			this.entity = entity;
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
				if (testDesire(object))
					continue;

				final SQLInsertWritableDatabaseRow row = new SQLInsertWritableDatabaseRow(
						factory.insert(entity));
				mapper.insert(entity, object, row);

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

	/**
	 * An query implementation designed to update objects in the database
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <O>
	 *            the query object type
	 * @param <E>
	 *            the query entity type
	 */
	public static abstract class UpdateQuery<O, E extends RelationalPathBase<?>>
			extends AbstractQuery<Integer> {
		/**
		 * The row mapper
		 */
		private final UpdateMapper<O, E> mapper;
		/**
		 * The object iterator for this query
		 */
		private final Iterator<O> iterator;
		/**
		 * The query entity
		 */
		protected final E entity;

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
			this.entity = entity;
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
				if (testDesire(object))
					continue;

				final SQLUpdateWritableDatabaseRow row = new SQLUpdateWritableDatabaseRow(
						factory.update(entity));
				// maps query to the values
				query(row.getClause(), object);
				mapper.update(entity, object, row);

				rows += row.getClause().execute();

				updateDesire(object, ObjectDesire.UPDATE);
			}
			return rows;
		}

		/**
		 * Performs the query filtering
		 * 
		 * @param q
		 *            the query clause
		 * @param o
		 *            the object
		 */
		protected abstract void query(SQLUpdateClause q, O o);
	}

	/**
	 * An query implementation designed for deleting objects in the database.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <O>
	 *            the query object type
	 * @param <E>
	 *            the query entity type
	 */
	public static abstract class DeleteQuery<O, E extends RelationalPathBase<?>>
			extends AbstractQuery<Integer> {
		/**
		 * The object iterator for this query
		 */
		private final Iterator<O> iterator;
		/**
		 * This query entity
		 */
		protected final E entity;

		/**
		 * @param entity
		 *            the entity type
		 * @param iterator
		 *            the objects to be inserted
		 */
		public DeleteQuery(E entity, Iterator<O> iterator) {
			this.iterator = iterator;
			this.entity = entity;
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
				if (testDesire(object))
					continue;

				final SQLDeleteClause delete = factory.delete(entity);
				// maps query to the values
				query(delete, object);

				rows += delete.execute();

				updateDesire(object, ObjectDesire.DELETE);
			}
			return rows;
		}

		/**
		 * Performs the query filtering
		 * 
		 * @param q
		 *            the query clause
		 * @param o
		 *            the object
		 */
		protected abstract void query(SQLDeleteClause q, O o);
	}

	/**
	 * Abstract query implementation designed for selecting database objects.
	 * Internal use only.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <R>
	 *            the query return type
	 * @param <O>
	 *            the query object type
	 * @param <RI>
	 *            the raw ID type
	 * @param <I>
	 *            the ID type
	 * @param <E>
	 *            the query entity type
	 */
	public static abstract class AbstractSelectQuery<R, O, RI, I extends ID<? super RI>, E extends RelationalPathBase<RI>>
			extends AbstractQuery<R> {
		/**
		 * This query entity type
		 */
		protected final E entity;
		/**
		 * The row mapper
		 */
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

		/**
		 * Performs the query filtering
		 * 
		 * @param q
		 *            the query clause
		 * @param e
		 *            the query entity
		 */
		protected abstract void query(AbstractSQLQuery<?> q, E e);

		/**
		 * Effectively performs the query executing and mapping process
		 * 
		 * @param select
		 *            the query clause ready to be executed (can be modified if
		 *            needed)
		 * @param database
		 *            the database service
		 * @return the query result, returned directly to the user
		 */
		protected abstract R perform(AbstractSQLQuery<?> select,
				DatabaseService database);

		/**
		 * Checks if the object is on the cache. Returns it if available,
		 * <code>null</code> otherwise.
		 * 
		 * @param row
		 *            the row
		 * @param database
		 *            the database service
		 * @return the object on cache, if exists.
		 */
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

		/**
		 * Updates the cache instance
		 * 
		 * @param instance
		 *            the object instance
		 * @param database
		 *            the database service
		 */
		protected void updateCache(O instance, DatabaseService database) {
			if (instance == null)
				return;
			if (instance instanceof Model)
				database.updateCache(((Model<?>) instance).getID(),
						(Model<?>) instance);
		}
	}

	/**
	 * An query implementation designed for selecting a single object in the
	 * database
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <O>
	 *            the object type
	 * @param <RI>
	 *            the raw ID type
	 * @param <I>
	 *            the ID type
	 * @param <E>
	 *            the query entity type
	 */
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
					updateDesire(object, ObjectDesire.INSERT);
				}
				return object;
			} else {
				return null;
			}
		}
	}

	/**
	 * An query implementation designed for selecting several objects in the
	 * database
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <O>
	 *            the object type
	 * @param <RI>
	 *            the raw ID type
	 * @param <I>
	 *            the ID type
	 * @param <E>
	 *            the query entity type
	 */
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
					updateDesire(object, ObjectDesire.INSERT);
				}
				if (object != null)
					objects.add(object);
			}
			return objects;
		}
	}

	/**
	 * An query implementation designed to count objects in the database
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <E>
	 *            the query entity type
	 */
	public static abstract class CountQuery<E extends RelationalPathBase<?>>
			extends AbstractQuery<Integer> {
		/**
		 * The query entity
		 */
		protected final E entity;

		/**
		 * @param entity
		 *            the entity typeO
		 */
		public CountQuery(E entity) {
			this.entity = entity;
		}

		@Override
		public final Integer query(
				SQLQueryFactory<? extends AbstractSQLQuery<?>, ?, ?, ?, ?, ?> factory,
				DatabaseService database) {
			final AbstractSQLQuery<?> count = factory.query().from(entity);
			query(count, entity);
			return (int) count.count();
		}

		/**
		 * Performs the query filtering
		 * 
		 * @param q
		 *            the query clause
		 * @param e
		 *            the entity
		 */
		protected abstract void query(AbstractSQLQuery<?> q, E e);
	}
}
