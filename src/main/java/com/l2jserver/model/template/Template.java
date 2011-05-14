package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.world.WorldObject;

/**
 * An template is like a base for an {@link WorldObject}. Normally, instead of
 * manually creating the object this can be done through templates, that easy
 * the need of setting the new objects parameters.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the {@link WorldObject} type
 */
public interface Template<T> {
	/**
	 * Create a new {@link WorldObject}
	 * 
	 * @return the created object
	 */
	T create();

	/**
	 * Return this {@link TemplateID}
	 * 
	 * @return the template id
	 */
	TemplateID<?> getID();
}
