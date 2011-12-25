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
package com.l2jserver.service.database.sql.ddl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.l2jserver.service.database.sql.ddl.annotation.ColumnAutoIncrement;
import com.l2jserver.service.database.sql.ddl.annotation.ColumnDefault;
import com.l2jserver.service.database.sql.ddl.annotation.ColumnNullable;
import com.l2jserver.service.database.sql.ddl.annotation.ColumnSize;
import com.l2jserver.service.database.sql.ddl.struct.Column;
import com.l2jserver.service.database.sql.ddl.struct.Column.ColumnType;
import com.l2jserver.service.database.sql.ddl.struct.ForeignKey;
import com.l2jserver.service.database.sql.ddl.struct.PrimaryKey;
import com.l2jserver.service.database.sql.ddl.struct.Table;
import com.l2jserver.util.ClassUtils;
import com.l2jserver.util.factory.CollectionFactory;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.Path;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class TableFactory {
	/**
	 * Creates an {@link Table} object from an {@link RelationalPathBase}
	 * 
	 * @param tablePath
	 *            the table path
	 * @return the {@link Table} object
	 */
	public static Table createTable(RelationalPathBase<?> tablePath) {
		final Map<String, Column> columns = CollectionFactory.newMap();
		for (final Path<?> path : tablePath.all()) {
			final Column col = createColumn(tablePath, path);
			columns.put(col.getName(), col);
		}

		return new Table(tablePath.getTableName(), columns, createPK(tablePath,
				columns), createFKs(tablePath, columns));
	}

	/**
	 * Reads an table from the database and returns the parsed object.
	 * <p>
	 * Note that this method does not parse everything: default values, enum
	 * types, primary and foreign keys are not parsed.
	 * 
	 * @param conn
	 *            the JDBC {@link Connection}
	 * @param template the query template
	 * @param tableName
	 *            the table name
	 * @return the parsed table
	 * @throws SQLException
	 *             if any sql error occur
	 */
	public static Table createTable(Connection conn, QueryTemplate template,
			String tableName) throws SQLException {
		Statement st = conn.createStatement();
		try {
			st.execute(new StringBuilder(template.getSelect()).append("*")
					.append(template.getFrom())
					.append(template.quoteIdentifier(tableName)).toString());
			final ResultSet rs = st.getResultSet();
			ResultSetMetaData metaData = rs.getMetaData();

			final Map<String, Column> columns = CollectionFactory.newMap();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				columns.put(metaData.getColumnName(i),
						new Column(metaData.getColumnName(i),
								getColumnType(metaData.getColumnType(i)),
								false, metaData.getColumnDisplaySize(i), false,
								null));
			}
			return new Table(metaData.getTableName(1), columns, null, null);
		} finally {
			st.close();
		}
	}

	/**
	 * Creates the foreign key list
	 * 
	 * @param tablePath
	 *            the table object
	 * @param columns
	 *            the columns
	 * @return the foreign key list
	 */
	private static List<ForeignKey> createFKs(RelationalPathBase<?> tablePath,
			Map<String, Column> columns) {
		final List<ForeignKey> fks = CollectionFactory.newList();
		for (final com.mysema.query.sql.ForeignKey<?> fk : tablePath
				.getForeignKeys()) {
			StringBuilder name = new StringBuilder();
			final List<Column> cols = CollectionFactory.newList();
			for (final Path<?> path : fk.getLocalColumns()) {
				String colName = path.getMetadata().getExpression().toString();
				cols.add(columns.get(colName));
				name.append(colName).append("-");
			}
			name.setLength(name.length() - 1);
			fks.add(new ForeignKey(name.toString(), cols));
		}
		return fks;
	}

	private static PrimaryKey createPK(RelationalPathBase<?> tablePath,
			Map<String, Column> columns) {
		return new PrimaryKey(columns.get(tablePath.getPrimaryKey()
				.getLocalColumns().get(0).getMetadata().getExpression()
				.toString()));
	}

	private static Column createColumn(RelationalPathBase<?> tablePath,
			Path<?> path) {
		final String columnName = path.getMetadata().getExpression().toString();
		final ColumnType columnType = getColumnType(path.getType());
		final Field field = ClassUtils.getFieldWithValue(tablePath, path);

		// settings
		int columnSize = -1;
		boolean columnNullable = false;
		boolean hasDefaultValue = false;
		String defaultValue = null;

		if (field != null) {
			final ColumnSize size = field.getAnnotation(ColumnSize.class);
			if (size != null) {
				columnSize = size.value();
			}

			final ColumnAutoIncrement autoInc = field
					.getAnnotation(ColumnAutoIncrement.class);
			if (autoInc != null) {
				return new Column(columnName, columnSize, true);
			}

			final ColumnNullable nullable = field
					.getAnnotation(ColumnNullable.class);
			if (nullable == null) {
				columnNullable = false;
			} else {
				columnNullable = true;
			}

			final ColumnDefault def = field.getAnnotation(ColumnDefault.class);
			if (def == null && nullable != null) {
				hasDefaultValue = true;
				defaultValue = null;
			} else if (def != null) {
				hasDefaultValue = true;
				defaultValue = def.value();
			}
		}

		if (columnType == ColumnType.ENUM) {
			@SuppressWarnings("unchecked")
			Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) path
					.getType();
			Enum<?>[] enums = enumClass.getEnumConstants();
			final List<String> enumValues = CollectionFactory.newList();
			for (final Enum<?> e : enums) {
				enumValues.add(e.name());
			}

			return new Column(columnName, columnNullable, enumValues,
					hasDefaultValue, defaultValue);
		}

		return new Column(columnName, columnType, columnNullable, columnSize,
				hasDefaultValue, defaultValue);
	}

	private static ColumnType getColumnType(Class<?> type) {
		if (ClassUtils.isSubclass(type, String.class))
			return ColumnType.STRING;
		else if (type.isEnum())
			return ColumnType.ENUM;
		else if (ClassUtils.isSubclass(type, Integer.class))
			return ColumnType.INTEGER;
		else if (ClassUtils.isSubclass(type, Long.class))
			return ColumnType.INTEGER;
		else if (ClassUtils.isSubclass(type, Double.class))
			return ColumnType.DOUBLE;
		else if (ClassUtils.isSubclass(type, Float.class))
			return ColumnType.DOUBLE;
		else if (ClassUtils.isSubclass(type, Date.class))
			return ColumnType.TIMESTAMP;
		return null;
	}

	private static ColumnType getColumnType(int jdbcType) {
		switch (jdbcType) {
		case Types.INTEGER:
			return ColumnType.INTEGER;
		case Types.VARCHAR:
			return ColumnType.STRING;
		case Types.TIMESTAMP:
			return ColumnType.TIMESTAMP;
		case Types.DOUBLE:
			return ColumnType.TIMESTAMP;
		case Types.CHAR:
			return ColumnType.ENUM;
		default:
			return null;
		}
	}
}
