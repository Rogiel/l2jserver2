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
package com.l2jserver.model.id;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

import com.l2jserver.model.id.provider.IDProvider;
import com.l2jserver.model.template.Template;
import com.l2jserver.service.game.template.TemplateService;

/**
 * Templates IDs, different from {@link ObjectID}s, can be repeated and are
 * defined in the template class.
 * <p>
 * Please, do not directly instantiate this class, use an {@link IDProvider}
 * instead.
 * 
 * @param <T>
 *            the template type provided by this {@link TemplateID}
 * @param <I>
 *            the raw ID type
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class TemplateID<T extends Template, I> extends ID<I> {
	/**
	 * The cached template {@link Reference}
	 * <p>
	 * Please, avoid using it directly. Instead use {@link #getTemplate()} to
	 * retrieve the template object, since it will check if the reference is
	 * still valid and if not, will request the template with
	 * {@link TemplateService}.
	 */
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
			cached = createReference(loadTemplate());
		return cached.get();
	}

	/**
	 * Returns the {@link Template} associated with this {@link ID}
	 * 
	 * @return the {@link Template} if existent, <tt>null</tt> otherwise
	 */
	protected abstract T loadTemplate();

	/**
	 * Creates a new reference for the given template. TemplateID
	 * implementations can override this method if desired another reference.
	 * 
	 * @param template
	 *            the template object
	 * @return the newly created {@link Reference}
	 */
	protected Reference<T> createReference(T template) {
		return new SoftReference<T>(template);
	}
}
