package com.l2jserver.model.id;

import com.l2jserver.model.template.Template;

/**
 * Templates IDs, different from {@link ObjectID}s, can be repeated and are
 * defined in the template class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class TemplateID<T extends Template<?>> extends ID {
	/**
	 * Creates a new instance
	 * 
	 * @param id
	 *            the raw id
	 */
	public TemplateID(int id) {
		super(id);
	}

	/**
	 * Returns the {@link Template} associated with this {@link ID}
	 * 
	 * @return the {@link Template} if existent, <tt>null</tt> otherwise
	 */
	public abstract T getTemplate();
}
