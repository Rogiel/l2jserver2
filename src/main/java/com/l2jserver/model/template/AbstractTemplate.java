package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;

/**
 * An abstract {@link Template}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the type of object created by this template
 */
public abstract class AbstractTemplate<T> implements Template<T> {
	/**
	 * The {@link TemplateID}F
	 */
	private final TemplateID<?> id;

	/**
	 * Creates a new instance
	 * 
	 * @param id
	 */
	protected AbstractTemplate(TemplateID<?> id) {
		this.id = id;
	}

	@Override
	public TemplateID<?> getID() {
		return id;
	}
}
