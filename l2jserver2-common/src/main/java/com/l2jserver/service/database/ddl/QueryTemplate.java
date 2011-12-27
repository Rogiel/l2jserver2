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

import com.l2jserver.service.database.ddl.struct.Column.ColumnType;
import com.mysema.query.sql.SQLTemplates;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public abstract class QueryTemplate extends SQLTemplates {
	/**
	 * @param quoteStr
	 *            the quote string
	 * @param escape
	 *            the escape char
	 * @param useQuotes
	 *            whether to use quotes or not
	 */
	protected QueryTemplate(String quoteStr, char escape, boolean useQuotes) {
		super(quoteStr, escape, useQuotes);
	}

	// String quoteTableName(String name);
	// String quoteColumnName(String name);
	// String quoteValue(String value);
	//
	// String getCreateTable(String tableName);
	// String getColumnDefinition(String name, ColumnType type, int size,
	// boolean nullable);

	/**
	 * @param type
	 *            the column type
	 * @return the database specific type
	 */
	public abstract String getDatabaseType(ColumnType type);

	/**
	 * @param type
	 *            the column type
	 * @return true if the database requires an size parameter for the column
	 *         type
	 */
	public abstract boolean getTypeSizeRequirement(ColumnType type);

	/**
	 * @return true if the database supports enums
	 */
	public abstract boolean supportsEnum();

	/**
	 * @return true if the database supports auto increment
	 */
	public abstract boolean supportsAutoIncrement();

	/**
	 * @return true if the database supports foreign keys
	 */
	public abstract boolean supportsForeignKeys();

	/**
	 * @return true if the database supports changing column types
	 */
	public abstract boolean supportsColumnChangeTypes();

	/**
	 * @return true if the database supports renaming columns
	 */
	public abstract boolean supportsColumnRename();

	/**
	 * @return true if the database supports altering tables
	 */
	public abstract boolean supportsAlterTable();

	/**
	 * @param defaultValue
	 *            the value
	 * @return the quoted value
	 */
	public String quoteValue(String defaultValue) {
		return new StringBuilder("'").append(defaultValue).append("'")
				.toString();
	}

	/**
	 * @return the <code>ALTER TABLE</code> statement
	 */
	public String getAlterTable() {
		return "alter table ";
	}

	/**
	 * @return the <code>ADD COLUMN</code> statement
	 */
	public String getAddColumn() {
		return "add column ";
	}

	/**
	 * @return the <code>DROP COLUMN</code> statement
	 */
	public String getDropColumn() {
		return "drop column ";
	}

	/**
	 * @return the <code>ALTER COLUMN</code> statement
	 */
	public String getAlterColumn() {
		return "alter column ";
	}
}
