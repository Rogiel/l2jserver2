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
package com.l2jserver.service.database.sql.ddl.struct;

import java.util.List;

import com.l2jserver.util.factory.CollectionFactory;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Column {
	private final String name;
	private final ColumnType type;

	public enum ColumnType {
		STRING, ENUM, INTEGER, DOUBLE, TIMESTAMP;
	}

	private boolean nullable = true;
	private int size = 0;
	private boolean hasDefaultValue = false;
	private String defaultValue = null;
	private List<String> enumValues = CollectionFactory.newList();
	private boolean autoIncrement;

	/**
	 * @param name
	 *            the column name
	 * @param type
	 *            the column type
	 */
	public Column(String name, ColumnType type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * @param name
	 *            the column name
	 * @param size
	 *            the column maximum value size
	 * @param autoIncrement
	 *            if the column is auto incrementable
	 */
	public Column(String name, Integer size, boolean autoIncrement) {
		this.name = name;
		this.type = ColumnType.INTEGER;
		this.nullable = false;
		this.size = size;
		this.hasDefaultValue = false;
		this.autoIncrement = autoIncrement;
	}

	/**
	 * @param name
	 *            the column name
	 * @param type
	 *            the column type
	 * @param nullable
	 *            if the column can be nulled
	 * @param size
	 *            the column maximum value size
	 * @param hasDefaultValue
	 *            if the column has an default value
	 * @param defaultValue
	 *            the columns default value
	 */
	public Column(String name, ColumnType type, Boolean nullable, Integer size,
			boolean hasDefaultValue, String defaultValue) {
		super();
		this.name = name;
		this.type = type;
		this.nullable = nullable;
		this.size = size;
		this.hasDefaultValue = hasDefaultValue;
		this.defaultValue = defaultValue;
	}

	/**
	 * @param name
	 *            the column name
	 * @param nullable
	 *            if the column can have null values
	 * @param enumValues
	 *            the enum values
	 * @param hasDefaultValue
	 *            if the column has an default value
	 * @param defaultValue
	 *            the column default value
	 */
	public Column(String name, Boolean nullable, List<String> enumValues,
			boolean hasDefaultValue, String defaultValue) {
		super();
		this.name = name;
		this.type = ColumnType.ENUM;
		this.nullable = nullable;
		this.hasDefaultValue = hasDefaultValue;
		this.defaultValue = defaultValue;
		this.enumValues.addAll(enumValues);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the nullable
	 */
	public Boolean isNullable() {
		return nullable;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @return the type
	 */
	public ColumnType getType() {
		return type;
	}

	/**
	 * @return the hasDefaultValue
	 */
	public boolean hasDefaultValue() {
		return hasDefaultValue;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @return the enumValues
	 */
	public List<String> getEnumValues() {
		return enumValues;
	}

	/**
	 * @return the autoIncrement
	 */
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append(name).append(" ")
				.append(type.name());
		if (size > 0)
			builder.append("(").append(size).append(")");
		if (nullable == false)
			builder.append(" NOT NULL");
		return builder.toString();
	}
}
