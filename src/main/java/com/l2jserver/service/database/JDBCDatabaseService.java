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
package com.l2jserver.service.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.google.inject.Injector;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.db.dao.ClanDAO;
import com.l2jserver.db.dao.ItemDAO;
import com.l2jserver.db.dao.NPCDAO;
import com.l2jserver.db.dao.PetDAO;
import com.l2jserver.model.Model;
import com.l2jserver.model.Model.ObjectDesire;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.Pet;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.cache.Cache;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.core.threading.ScheduledAsyncFuture;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.util.ArrayIterator;
import com.l2jserver.util.ClassUtils;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * The database service implementation for MySQL database
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, CacheService.class,
		ConfigurationService.class, TemplateService.class, ThreadService.class })
public class JDBCDatabaseService extends AbstractService implements
		DatabaseService {
	/**
	 * The configuration object
	 */
	private final JDBCDatabaseConfiguration config;
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory
			.getLogger(JDBCDatabaseService.class);

	/**
	 * The Google Guice {@link Injector}. It is used to get DAO instances.
	 */
	private final Injector injector;

	/**
	 * The cache service
	 */
	private final CacheService cacheService;
	/**
	 * The thread service
	 */
	private final ThreadService threadService;

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

	@Inject
	public JDBCDatabaseService(ConfigurationService configService,
			Injector injector, CacheService cacheService,
			ThreadService threadService) {
		config = configService.get(JDBCDatabaseConfiguration.class);
		this.injector = injector;
		this.cacheService = cacheService;
		this.threadService = threadService;
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
							final DataAccessObject<Model<?>, ?> dao = getDAO(object
									.getClass());
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

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <M extends Model<I>, I extends ID<M>> DataAccessObject<M, I> getDAO(
			Class<M> model) {
		if (ClassUtils.isSubclass(model, L2Character.class)) {
			return (DataAccessObject) injector.getInstance(CharacterDAO.class);
		} else if (ClassUtils.isSubclass(model, Clan.class)) {
			return (DataAccessObject) injector.getInstance(ClanDAO.class);
		} else if (ClassUtils.isSubclass(model, Item.class)) {
			return (DataAccessObject) injector.getInstance(ItemDAO.class);
		} else if (ClassUtils.isSubclass(model, NPC.class)) {
			return (DataAccessObject) injector.getInstance(NPCDAO.class);
		} else if (ClassUtils.isSubclass(model, Pet.class)) {
			return (DataAccessObject) injector.getInstance(PetDAO.class);
		}
		return null;
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

	public Object getCachedObject(Object id) {
		Preconditions.checkNotNull(id, "id");
		return objectCache.get(id);
	}

	public boolean hasCachedObject(Object id) {
		Preconditions.checkNotNull(id, "id");
		return objectCache.contains(id);
	}

	public void updateCache(ID<?> key, Model<?> value) {
		Preconditions.checkNotNull(key, "key");
		Preconditions.checkNotNull(value, "value");
		objectCache.put(key, value);
	}

	public void removeCache(Object key) {
		Preconditions.checkNotNull(key, "key");
		objectCache.remove(key);
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
		private final Iterator<T> iterator;

		/**
		 * Creates a new query for <tt>objects</tt>
		 * 
		 * @param objects
		 *            the object list
		 */
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
		public Integer query(Connection conn) throws SQLException {
			Preconditions.checkNotNull(conn, "conn");
			int rows = 0;
			while (iterator.hasNext()) {
				final T object = iterator.next();
				final PreparedStatement st = conn.prepareStatement(query());
				this.parametize(st, object);
				rows += st.executeUpdate();

				// update object desire --it has been realized
				if (object instanceof Model)
					((Model<?>) object).setObjectDesire(ObjectDesire.NONE);

				final Mapper<T> mapper = keyMapper(object);
				if (mapper == null)
					continue;
				final ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					mapper.map(rs);
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
		 */
		protected abstract void parametize(PreparedStatement st, T object)
				throws SQLException;

		/**
		 * Return the key mapper. Can be null if no generated keys are used or
		 * are not important.
		 * 
		 * @param object
		 *            the object
		 * @return the key mapper
		 */
		protected Mapper<T> keyMapper(T object) {
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
		@Override
		public List<T> query(Connection conn) throws SQLException {
			Preconditions.checkNotNull(conn, "conn");
			final PreparedStatement st = conn.prepareStatement(query());
			parametize(st);
			st.execute();
			final List<T> list = CollectionFactory.newList();
			final ResultSet rs = st.getResultSet();
			while (rs.next()) {
				final T obj = mapper().map(rs);
				if (obj == null)
					continue;
				if (obj instanceof Model)
					((Model<?>) obj).setObjectDesire(ObjectDesire.NONE);
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
		 * @param object
		 *            the object
		 * @throws SQLException
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
		@Override
		public T query(Connection conn) throws SQLException {
			Preconditions.checkNotNull(conn, "conn");
			final PreparedStatement st = conn.prepareStatement(query());
			parametize(st);
			st.execute();
			final ResultSet rs = st.getResultSet();
			while (rs.next()) {
				final T object = mapper().map(rs);
				if (object instanceof Model)
					((Model<?>) object).setObjectDesire(ObjectDesire.NONE);
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
		 * @param object
		 *            the object
		 * @throws SQLException
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
		 * The database service instance
		 */
		private final JDBCDatabaseService database;

		private final Mapper<I> idMapper;

		/**
		 * Creates a new instance
		 * 
		 * @param database
		 *            the database service
		 */
		public CachedMapper(JDBCDatabaseService database, Mapper<I> idMapper) {
			this.database = database;
			this.idMapper = idMapper;
		}

		@Override
		@SuppressWarnings("unchecked")
		public final T map(ResultSet rs) throws SQLException {
			final I id = idMapper.map(rs);
			Preconditions.checkNotNull(id, "id");

			if (database.hasCachedObject(id))
				return (T) database.getCachedObject(id);

			final T object = map(id, rs);
			if (object != null)
				database.updateCache(id, object);
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
		 */
		protected abstract T map(I id, ResultSet rs) throws SQLException;
	}
}
