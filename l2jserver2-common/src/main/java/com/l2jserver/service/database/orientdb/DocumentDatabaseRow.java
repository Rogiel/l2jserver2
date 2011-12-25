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
package com.l2jserver.service.database.orientdb;

import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.WritableDatabaseRow;
import com.mysema.query.types.Path;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class DocumentDatabaseRow implements DatabaseRow, WritableDatabaseRow {
	private ODocument document;

	/**
	 * @param document
	 *            the orientdb document
	 */
	public DocumentDatabaseRow(ODocument document) {
		this.document = document;
	}

	public DocumentDatabaseRow() {
	}

	@Override
	public <T> T get(Path<T> path) {
		return document.field(path.getMetadata().getExpression().toString(),
				path.getType());
	}

	@Override
	public <T> boolean isNull(Path<T> path) {
		return get(path) == null;
	}

	@Override
	public <T> WritableDatabaseRow set(Path<T> path, T value) {
		document.field(path.getMetadata().getExpression().toString(), value);
		return this;
	}

	@Override
	public <T> WritableDatabaseRow setNull(Path<T> path) {
		return set(path, null);
	}

	/**
	 * @return the orientdb document
	 */
	public ODocument getDocument() {
		return document;
	}

	/**
	 * @param document
	 *            the orientdb document to set
	 */
	public void setDocument(ODocument document) {
		this.document = document;
	}
}
