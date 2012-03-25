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
package com.l2jserver.service.database.ddl.struct;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.l2jserver.util.factory.CollectionFactory;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class Table {
	/**
	 * The table name
	 */
	private final String name;

	/**
	 * The columns
	 */
	private final Map<String, Column> columns = CollectionFactory.newMap();
	/**
	 * The primary key
	 */
	private final PrimaryKey primaryKey;
	/**
	 * The foreign keys
	 */
	private final List<ForeignKey> foreignKeys = CollectionFactory.newList();

	/**
	 * @param name
	 *            the table name
	 * @param columns
	 *            the column
	 * @param primaryKey
	 *            the primary key
	 * @param foreignKeys
	 *            the foreign keys
	 */
	public Table(String name, Map<String, Column> columns,
			PrimaryKey primaryKey, List<ForeignKey> foreignKeys) {
		this.name = name;
		this.columns.putAll(columns);
		this.primaryKey = primaryKey;
		if (foreignKeys != null)
			this.foreignKeys.addAll(foreignKeys);
	}

	/**
	 * @param name
	 *            the table name
	 * @param primaryKey
	 *            the primary key
	 */
	public Table(String name, PrimaryKey primaryKey) {
		this.name = name;
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the columns
	 */
	public Map<String, Column> getColumns() {
		return Collections.unmodifiableMap(columns);
	}

	/**
	 * @return the columns
	 */
	public Collection<Column> getColumnList() {
		return columns.values();
	}

	/**
	 * Adds a new column
	 * 
	 * @param column
	 *            the column object
	 * @return the same column
	 */
	public Column addColumn(Column column) {
		columns.put(column.getName(), column);
		return column;
	}

	/**
	 * @param name
	 *            the column name
	 * @return the column represented by <code>name</code>
	 */
	public Column getColumn(String name) {
		for (final Column column : columns.values()) {
			if (name.equals(column.getName()))
				return column;
		}
		return null;
	}

	/**
	 * @return the primaryKey
	 */
	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @return the foreignKeys
	 */
	public List<ForeignKey> getForeignKeys() {
		return Collections.unmodifiableList(foreignKeys);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("CREATE TABLE ").append(name).append(" (\n");
		for (final Column column : columns.values()) {
			builder.append("\t").append(column.toString()).append(",\n");
		}
		builder.setLength(builder.length() - 2);
		builder.append("\n)");

		return builder.toString();
	}
}
