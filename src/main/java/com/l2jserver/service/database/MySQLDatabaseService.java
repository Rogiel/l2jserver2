package com.l2jserver.service.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.util.ArrayIterator;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * The database service implementation for MySQL database
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MySQLDatabaseService extends AbstractService implements
		DatabaseService {
	/**
	 * The configuration object
	 */
	private final MySQLDatabaseConfiguration config;
	/**
	 * The logger
	 */
	private final Logger logger = LoggerFactory
			.getLogger(MySQLDatabaseService.class);

	/**
	 * The database connection pool
	 */
	private ObjectPool connectionPool;
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

	@Inject
	public MySQLDatabaseService(ConfigurationService configService) {
		config = configService.get(MySQLDatabaseConfiguration.class);
	}

	@Override
	public void start() throws ServiceStartException {
		connectionPool = new GenericObjectPool(null);
		connectionFactory = new DriverManagerConnectionFactory(
				config.getJdbcUrl(), config.getUsername(), config.getPassword());
		poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, connectionPool, null, null, false, true);
		dataSource = new PoolingDataSource(connectionPool);
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
		try {
			final Connection conn = dataSource.getConnection();
			try {
				return query.query(conn);
			} catch (SQLException e) {
				logger.error("Error executing query", e);
				return null;
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("Could not open database connection", e);
			return null;
		}
	}

	@Override
	public void stop() throws ServiceStopException {
		try {
			if (connectionPool != null)
				connectionPool.close();
		} catch (Exception e) {
			logger.error("Error stopping database service", e);
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
			int rows = 0;
			while (iterator.hasNext()) {
				final T object = iterator.next();
				final PreparedStatement st = conn.prepareStatement(query());
				this.parametize(st, object);
				rows += st.executeUpdate();
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
			final PreparedStatement st = conn.prepareStatement(query());
			parametize(st);
			st.execute();
			final List<T> list = CollectionFactory.newList(null);
			final ResultSet rs = st.getResultSet();
			while (rs.next()) {
				list.add(mapper().map(rs));
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
			final PreparedStatement st = conn.prepareStatement(query());
			parametize(st);
			st.execute();
			final ResultSet rs = st.getResultSet();
			while (rs.next()) {
				return mapper().map(rs);
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
}
