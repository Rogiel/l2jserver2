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
package com.l2jserver.service.database.ddl;

import java.util.List;

import com.l2jserver.service.database.ddl.struct.Column;
import com.l2jserver.service.database.ddl.struct.ForeignKey;
import com.l2jserver.service.database.ddl.struct.PrimaryKey;
import com.l2jserver.service.database.ddl.struct.Table;
import com.l2jserver.service.database.ddl.struct.Column.ColumnType;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class QueryFactory {
	/**
	 * Creates an <code>CREATE TABLE</code> query from an {@link Table} object
	 * 
	 * @param table
	 *            the table object
	 * @param template
	 *            the query template
	 * @return the <code>CREATE TABLE</code> query as string
	 */
	public static String createTableQuery(Table table, QueryTemplate template) {
		final StringBuilder builder = new StringBuilder();
		builder.append(template.getCreateTable())
				.append(template.quoteIdentifier(table.getName()))
				.append(" (\n").toString();
		for (final Column column : table.getColumnList()) {
			builder.append("\t");
			createColumnDefinition(builder, template, column, false);
			builder.append(",\n");
		}
		if (table.getPrimaryKey() != null) {
			builder.append("\t");
			generatePrimaryKeyDefinition(builder, template, table,
					table.getPrimaryKey());
			builder.append(",\n");
		}
		if (template.supportsForeignKeys()) {
			for (final ForeignKey fk : table.getForeignKeys()) {
				builder.append("\t");
				generateForeignKeyDefinition(builder, template, fk);
				builder.append(",\n");
			}
		}
		builder.setLength(builder.length() - 2);
		builder.append("\n)");

		return builder.toString();
	}

	/**
	 * Creates an <code>ALTER TABLE</code> query from the difference between two
	 * {@link Table} objects
	 * 
	 * @param expected
	 *            the desired table model
	 * @param current
	 *            the current table model
	 * @param template
	 *            the query template
	 * @return the <code>ALTER TABLE</code> query as string
	 */
	public static String alterTableQueryDelta(Table expected, Table current,
			QueryTemplate template) {
		// detect missing columns
		final List<Column> addColumns = CollectionFactory.newList();
		final List<Column> updateColumns = CollectionFactory.newList();
		for (final Column expectedColumn : expected.getColumnList()) {
			final Column missingColumn = current.getColumn(expectedColumn
					.getName());
			if (missingColumn == null)
				addColumns.add(expectedColumn);
			else
				updateColumns.add(expectedColumn);
		}
		// detect wrong columns
		final List<Column> dropColumns = CollectionFactory.newList();
		for (final Column unexpectedColumn : current.getColumnList()) {
			final Column expectedColumn = expected.getColumn(unexpectedColumn
					.getName());
			if (expectedColumn == null)
				dropColumns.add(unexpectedColumn);
		}

		final StringBuilder builder = new StringBuilder();
		builder.append(template.getAlterTable())
				.append(template.quoteIdentifier(expected.getName()))
				.append("\n").toString();
		for (final Column column : addColumns) {
			builder.append("\t").append(template.getAddColumn());
			createColumnDefinition(builder, template, column, true);
			builder.append(",\n");
		}
		for (final Column column : dropColumns) {
			builder.append("\t").append(template.getDropColumn())
					.append(template.quoteIdentifier(column.getName()))
					.append(",\n");
		}
		for (final Column column : updateColumns) {
			builder.append("\t").append(template.getAlterColumn());
			if (template.supportsColumnRename())
				builder.append(template.quoteIdentifier(column.getName()))
						.append(" ");
			createColumnDefinition(builder, template, column, true);
			builder.append(",\n");
		}
		builder.setLength(builder.length() - 2);

		return builder.toString();
	}

	/**
	 * Creates an <code>ALTER TABLE</code> query from the difference between two
	 * {@link Table} objects.
	 * <p>
	 * This method does not delete any column.
	 * 
	 * @param expected
	 *            the desired table model
	 * @param current
	 *            the current table model
	 * @param template
	 *            the query template
	 * @return the <code>ALTER TABLE</code> query as string
	 */
	public static String alterTableQueryUpdate(Table expected, Table current,
			QueryTemplate template) {
		// detect missing columns
		final List<Column> addColumns = CollectionFactory.newList();
		final List<Column> updateColumns = CollectionFactory.newList();
		for (final Column expectedColumn : expected.getColumnList()) {
			final Column missingColumn = current.getColumn(expectedColumn
					.getName());
			if (missingColumn == null)
				addColumns.add(expectedColumn);
			else
				updateColumns.add(expectedColumn);
		}

		final StringBuilder builder = new StringBuilder();
		builder.append(template.getAlterTable())
				.append(template.quoteIdentifier(expected.getName()))
				.append("\n").toString();
		for (final Column column : addColumns) {
			builder.append("\t").append(template.getAddColumn());
			createColumnDefinition(builder, template, column, true);
			builder.append(",\n");
		}
		for (final Column column : updateColumns) {
			builder.append("\t").append(template.getAlterColumn());
			if (template.supportsColumnRename())
				builder.append(template.quoteIdentifier(column.getName()))
						.append(" ");
			createColumnDefinition(builder, template, column, true);
			builder.append(",\n");
		}
		builder.setLength(builder.length() - 2);

		return builder.toString();
	}

	/**
	 * Creates an <code>ALTER TABLE</code> query from the difference between two
	 * {@link Table} objects. Note that this method will only add missing
	 * columns, but won't update their types.
	 * 
	 * @param expected
	 *            the desired table model
	 * @param current
	 *            the current table model
	 * @param template
	 *            the query template
	 * @return the <code>ALTER TABLE</code> query as string
	 */
	public static String alterTableQueryMissing(Table expected, Table current,
			QueryTemplate template) {
		// detect missing columns
		final List<Column> addColumns = CollectionFactory.newList();
		for (final Column expectedColumn : expected.getColumnList()) {
			final Column missingColumn = current.getColumn(expectedColumn
					.getName());
			if (missingColumn == null)
				addColumns.add(expectedColumn);
		}

		if (addColumns.isEmpty())
			return null;

		final StringBuilder builder = new StringBuilder();
		builder.append(template.getAlterTable())
				.append(template.quoteIdentifier(expected.getName()))
				.append("\n").toString();
		for (final Column column : addColumns) {
			builder.append("\t").append(template.getAddColumn());
			createColumnDefinition(builder, template, column, true);
			builder.append(",\n");
		}
		builder.setLength(builder.length() - 2);

		return builder.toString();
	}

	/**
	 * @param builder
	 *            the {@link StringBuilder}
	 * @param template
	 *            the query template
	 * @param column
	 *            the column
	 * @param alter
	 *            whether it is an alter table or create table
	 */
	private static void createColumnDefinition(StringBuilder builder,
			QueryTemplate template, Column column, boolean alter) {
		builder.append(template.quoteIdentifier(column.getName())).append(" ");

		if ((alter && template.supportsColumnChangeTypes()) || !alter) {
			builder.append(template.getDatabaseType(column.getType()));
			if (column.getType() == ColumnType.ENUM && template.supportsEnum()) {
				builder.append("(");
				if (!column.getEnumValues().isEmpty()) {
					for (final String val : column.getEnumValues()) {
						builder.append(template.quoteValue(val)).append(",");
					}
					builder.setLength(builder.length() - 1);
				}
				builder.append(")");
			} else if (template.getTypeSizeRequirement(column.getType())
					&& (column.getSize() > 0 || column.getEnumValues() != null)) {
				int size = column.getSize();
				if (column.getEnumValues() != null) {
					for (final String val : column.getEnumValues()) {
						if (val.length() > column.getSize())
							// redefine size
							size = val.length();
					}
				}
				builder.append("(").append(size).append(")");
			}
		}
		if (column.isNullable() == false) {
			builder.append(template.getNotNull());
		}
		if (column.isAutoIncrement() && template.supportsAutoIncrement()) {
			builder.append(template.getAutoIncrement());
		} else if (column.hasDefaultValue()) {
			builder.append(" DEFAULT ");
			if (column.getDefaultValue() == null) {
				builder.append("NULL");
			} else {
				builder.append(template.quoteValue(column.getDefaultValue()));
			}
		}
	}

	/**
	 * @param builder
	 *            the {@link StringBuilder}
	 * @param template
	 *            the query template
	 * @param table
	 *            the table
	 * @param pk
	 *            the primary key
	 */
	private static void generatePrimaryKeyDefinition(StringBuilder builder,
			QueryTemplate template, Table table, PrimaryKey pk) {
		builder.append("CONSTRAINT ")
				.append(template.quoteIdentifier(table.getName() + "-"
						+ pk.getColumn().getName())).append(" PRIMARY KEY(")
				.append(template.quoteIdentifier(pk.getColumn().getName()))
				.append(")");
		// builder.append("PRIMARY KEY (")
		// .append(template.quoteIdentifier(pk.getColumn().getName()))
		// .append(")");
	}

	/**
	 * @param builder
	 *            the {@link StringBuilder}
	 * @param template
	 *            the query template
	 * @param fk
	 *            the foreign key
	 */
	private static void generateForeignKeyDefinition(StringBuilder builder,
			QueryTemplate template, ForeignKey fk) {
		builder.append("CONSTRAINT ")
				.append(template.quoteIdentifier(fk.getName())).append(" KEY(");
		for (final Column column : fk.getColumns()) {
			builder.append(template.quoteIdentifier(column.getName())).append(
					", ");
		}
		builder.setLength(builder.length() - 2);
		builder.append(")");
	}
}
