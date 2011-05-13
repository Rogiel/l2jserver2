package com.l2jserver.model.id;

import com.l2jserver.model.template.Template;

/**
 * Templates IDs, different from {@link ObjectID}s, can be repeated and are
 * defined in the template class.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public abstract class TemplateID<T extends Template<?>> extends ID {
	public TemplateID(int id) {
		super(id);
	}

	public abstract T getTemplate();
}
