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
package com.l2jserver.service.database.sql.ddl.template;

import java.math.BigDecimal;

import com.l2jserver.service.database.sql.ddl.QueryTemplate;
import com.l2jserver.service.database.sql.ddl.struct.Column.ColumnType;
import com.mysema.query.types.Ops;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MySQLTemplate extends QueryTemplate {
	public MySQLTemplate() {
		super("`", '\\', true);
		addClass2TypeMappings("bool", Boolean.class);
		addClass2TypeMappings("int", Integer.class);

		addClass2TypeMappings("decimal", Double.class, Float.class,
				BigDecimal.class);
		// addClass2TypeMappings("text", String.class);

		add(Ops.CONCAT, "concat({0}, {1})", 0);
		add(Ops.MATCHES, "{0} regexp {1}");
		add(Ops.DateTimeOps.YEAR_MONTH, "extract(year_month from {0})");

		// like without escape
		add(Ops.LIKE, "{0} like {1}");
		add(Ops.ENDS_WITH, "{0} like {%1}");
		add(Ops.ENDS_WITH_IC, "{0l} like {%%1}");
		add(Ops.STARTS_WITH, "{0} like {1%}");
		add(Ops.STARTS_WITH_IC, "{0l} like {1%%}");
		add(Ops.STRING_CONTAINS, "{0} like {%1%}");
		add(Ops.STRING_CONTAINS_IC, "{0l} like {%%1%%}");
	}

	// @Override
	// public String quoteTableName(String name) {
	// return new StringBuilder().append("`").append(name).append("`")
	// .toString();
	// }
	//
	// @Override
	// public String quoteColumnName(String name) {
	// return new StringBuilder().append("`").append(name).append("`")
	// .toString();
	// }
	//
	// @Override
	// public String quoteValue(String value) {
	// return new StringBuilder().append("'").append(value).append("'")
	// .toString();
	// }
	//
	// @Override
	// public String getCreateTable(String tableName) {
	// return new StringBuilder().append("CREATE TABLE `").append(tableName)
	// .append("` (").toString();
	// }
	//
	// @Override
	// public String getColumnDefinition(String name, ColumnType type, int size,
	// boolean nullable) {
	// final StringBuilder builder = new StringBuilder();
	// builder.append("`").append(name).append("` ")
	// .append(getDatabaseType(type));
	// if (getTypeSizeRequirement(type) && size > 0)
	// builder.append("(").append(size).append(")");
	// if (nullable) {
	// builder.append(" NOT NULL");
	// }
	// return builder.toString();
	// }

	@Override
	public String getDatabaseType(ColumnType type) {
		switch (type) {
		case ENUM:
			return "enum";
		case DOUBLE:
			return "double";
		case INTEGER:
			return "int";
		case STRING:
			return "varchar";
		case TIMESTAMP:
			return "timestamp";
		default:
			return "varchar";
		}
	}

	@Override
	public boolean getTypeSizeRequirement(ColumnType type) {
		switch (type) {
		case ENUM:
		case DOUBLE:
		case TIMESTAMP:
			return false;
		case INTEGER:
		case STRING:
			return true;
		default:
			return true;
		}
	}

	@Override
	public String getAddColumn() {
		return "add ";
	}

	@Override
	public String getDropColumn() {
		return "drop ";
	}

	@Override
	public String getAlterColumn() {
		return "change ";
	}

	@Override
	public boolean supportsEnum() {
		return true;
	}

	@Override
	public boolean supportsAutoIncrement() {
		return true;
	}

	@Override
	public boolean supportsForeignKeys() {
		return true;
	}

	@Override
	public boolean supportsColumnChangeTypes() {
		return true;
	}

	@Override
	public boolean supportsColumnRename() {
		return true;
	}

	@Override
	public boolean supportsAlterTable() {
		return true;
	}
}
