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
package com.l2jserver.service.database;

import java.util.Map;

import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.util.transformer.Transformer;
import com.l2jserver.util.transformer.TransformerFactory;
import com.mysema.query.types.Path;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class StaticDatabaseRow implements DatabaseRow {
	/**
	 * The static data
	 */
	private final Map<String, String> data;

	/**
	 * @param data
	 *            the data
	 */
	public StaticDatabaseRow(Map<String, String> data) {
		this.data = data;
	}

	@Override
	public <T> T get(Path<T> path) {
		final String value = data.get(path.getMetadata().getExpression()
				.toString());
		@SuppressWarnings("unchecked")
		Transformer<T> transformer = (Transformer<T>) TransformerFactory
				.getTransfromer(path.getType());
		return transformer.untransform(path.getType(), value);
	}

	@Override
	public <T> boolean isNull(Path<T> path) {
		// TODO Auto-generated method stub
		return false;
	}
}
