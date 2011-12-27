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
package com.l2jserver.service.database.ddl.template;

import com.l2jserver.service.database.ddl.QueryTemplate;
import com.l2jserver.service.database.ddl.struct.Column.ColumnType;
import com.mysema.query.types.Ops;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class H2Template extends QueryTemplate {
	/**
	 * Creates a new instance
	 */
	public H2Template() {
		super("\"", '\\', true);
		setNativeMerge(true);
		add(Ops.MathOps.ROUND, "round({0},0)");
		add(Ops.TRIM, "trim(both from {0})");
		add(Ops.CONCAT, "concat({0},{1})");
	}

	@Override
	public String getDatabaseType(ColumnType type) {
		switch (type) {
		case ENUM:
			return "varchar";
		case DOUBLE:
			return "double";
		case INTEGER:
			return "integer";
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
		case DOUBLE:
		case TIMESTAMP:
			return false;
		case ENUM:
		case INTEGER:
		case STRING:
			return true;
		default:
			return true;
		}
	}

	@Override
	public boolean supportsEnum() {
		return false;
	}

	@Override
	public boolean supportsAutoIncrement() {
		return true;
	}

	@Override
	public boolean supportsForeignKeys() {
		return false;
	}

	@Override
	public boolean supportsColumnChangeTypes() {
		return true;
	}

	@Override
	public boolean supportsColumnRename() {
		return false;
	}

	@Override
	public boolean supportsAlterTable() {
		return false;
	}
}
