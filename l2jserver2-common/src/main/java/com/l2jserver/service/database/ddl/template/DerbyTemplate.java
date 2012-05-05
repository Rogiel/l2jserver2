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
import com.mysema.query.QueryMetadata;
import com.mysema.query.QueryModifiers;
import com.mysema.query.sql.SerializationContext;
import com.mysema.query.types.Ops;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DerbyTemplate extends QueryTemplate {
	/**
	 * The limit offset template string
	 */
	private String limitOffsetTemplate = "\noffset {1s} rows fetch next {0s} rows only";

	/**
	 * The limit template string
	 */
	private String limitTemplate = "\nfetch first {0s} rows only";

	/**
	 * The offset template string
	 */
	private String offsetTemplate = "\noffset {0s} rows";

	/**
	 * Creates a new instance
	 */
	public DerbyTemplate() {
		super("\"", '\\', true);
		addClass2TypeMappings("smallint", Byte.class);
		setAutoIncrement(" generated always as identity");

		add(Ops.CONCAT, "varchar({0} || {1})");
		add(Ops.MathOps.ROUND, "floor({0})");
		add(Ops.DateTimeOps.DAY_OF_MONTH, "day({0})");

		add(NEXTVAL, "next value for {0s}");

		// case for eq
		add(Ops.CASE_EQ, "case {1} end");
		add(Ops.CASE_EQ_WHEN, "when {0} = {1} then {2} {3}");
		add(Ops.CASE_EQ_ELSE, "else {0}");
	}

	protected void serializeModifiers(QueryMetadata metadata,
			SerializationContext context) {
		QueryModifiers mod = metadata.getModifiers();
		if (mod.getLimit() == null) {
			context.handle(offsetTemplate, mod.getOffset());
		} else if (mod.getOffset() == null) {
			context.handle(limitTemplate, mod.getLimit());
		} else {
			context.handle(limitOffsetTemplate, mod.getLimit(), mod.getOffset());
		}
	}

	@Override
	public String getDatabaseType(ColumnType type) {
		switch (type) {
		case ENUM:
			return "varchar";
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
		case DOUBLE:
		case TIMESTAMP:
		case INTEGER:
			return false;
		case ENUM:
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
		return false;
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
