/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.id;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

import com.l2jserver.model.template.Template;

/**
 * Templates IDs, different from {@link ObjectID}s, can be repeated and are
 * defined in the template class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class TemplateID<T extends Template<?>, I> extends ID<I> {
	private Reference<T> cached;

	/**
	 * Creates a new instance
	 * 
	 * @param id
	 *            the raw id
	 */
	public TemplateID(I id) {
		super(id);
	}

	/**
	 * Returns the {@link Template} associated with this {@link ID}
	 * 
	 * @return the {@link Template} if existent, <tt>null</tt> otherwise
	 */
	public T getTemplate() {
		if (cached == null || cached.get() == null)
			cached = new SoftReference<T>(loadTemplate());
		return cached.get();
	}

	/**
	 * Returns the {@link Template} associated with this {@link ID}
	 * 
	 * @return the {@link Template} if existent, <tt>null</tt> otherwise
	 */
	protected abstract T loadTemplate();
}
