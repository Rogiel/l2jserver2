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

import com.l2jserver.service.database.sql.ddl.struct.Column.ColumnType;
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

	public abstract String getDatabaseType(ColumnType type);

	public abstract boolean getTypeSizeRequirement(ColumnType type);

	public abstract boolean supportsEnum();

	public abstract boolean supportsAutoIncrement();

	public abstract boolean supportsForeignKeys();

	public abstract boolean supportsColumnChangeTypes();

	public abstract boolean supportsColumnRename();

	public abstract boolean supportsAlterTable();

	public String quoteValue(String defaultValue) {
		return new StringBuilder("'").append(defaultValue).append("'")
				.toString();
	}

	public String getAlterTable() {
		return "alter table ";
	}

	public String getAddColumn() {
		return "add column ";
	}

	public String getDropColumn() {
		return "drop column ";
	}

	public String getAlterColumn() {
		return "alter column ";
	}
}
