package com.l2jserver.service.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

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

public class MySQLDatabaseService extends AbstractService implements
		DatabaseService {
	private final MySQLDatabaseConfiguration config;
	private final Logger logger = LoggerFactory
			.getLogger(MySQLDatabaseService.class);

	private ObjectPool connectionPool;
	private ConnectionFactory connectionFactory;
	@SuppressWarnings("unused")
	private PoolableConnectionFactory poolableConnectionFactory;
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

	public interface Query<R> {
		R query(Connection conn) throws SQLException;
	}

	public static abstract class InsertUpdateQuery<T> implements Query<Integer> {
		private final Iterator<T> iterator;

		public InsertUpdateQuery(T... objects) {
			this(new ArrayIterator<T>(objects));
		}

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

		protected abstract String query();

		protected abstract void parametize(PreparedStatement st, T object)
				throws SQLException;

		protected Mapper<T> keyMapper(T object) {
			return null;
		}
	}

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

		protected abstract String query();

		protected void parametize(PreparedStatement st) throws SQLException {
		}

		protected abstract Mapper<T> mapper();
	}

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

		protected abstract String query();

		protected abstract void parametize(PreparedStatement st)
				throws SQLException;

		protected abstract Mapper<T> mapper();
	}

	public interface Mapper<T> {
		T map(ResultSet rs) throws SQLException;
	}
}
